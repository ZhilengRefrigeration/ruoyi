package com.ruoyi.file.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.ruoyi.file.config.AliyunOssConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * aliyun oss 文件存储 实现类
 * https://help.aliyun.com/learn/learningpath/oss.html
 * 代码核心实现，来自aliyun oss 官方demo
 * https://github.com/aliyun/aliyun-oss-java-sdk
 * <p>
 * 对象存储可以对图片进行处理
 * 图片处理 https://help.aliyun.com/document_detail/47505.html
 * 图片样式 https://help.aliyun.com/document_detail/48884.html
 * </p>
 * 对象存储鉴权：阿里云临时安全令牌（Security Token Service，STS） https://help.aliyun.com/document_detail/28756.htm?spm=a2c4g.11186623.2.4.3fba6d13JdXEzJ#reference-ong-5nv-xdb
 * 对象存储oss docs 授权访问 使用STS进行临时授权 使用签名URL进行临时授权 https://help.aliyun.com/document_detail/32016.html?spm=a2c4g.11186623.6.992.7a943b4aPjkyTA#title-pu8-5o8-x7j
 * 搜索 【sts】
 *
 * @author yabo dazer
 * @date 2019/8/6 19:02
 * //@see AliyunMsgUtil
 */
@Primary
@Service
public class AliyunOssDsfServiceImpl implements IDfsService {
    private static final Logger log = LoggerFactory.getLogger(AliyunOssDsfServiceImpl.class);
    @Autowired
    private AliyunOssConfig aliyunOssConfig;
    /**
     * demo 地址 https://help.aliyun.com/learn/learningpath/oss.html
     * <p>
     * 简单demo https://github.com/aliyun/aliyun-oss-java-sdk/blob/master/src/samples/UploadSample.java
     * 分片上传demo(大文件) https://github.com/aliyun/aliyun-oss-java-sdk/blob/master/src/samples/MultipartUploadSample.java 分片上传，在oss上面能看到碎片记录
     *
     * @return eg: https://hiber2019.oss-cn-shanghai.aliyuncs.com/upload/default/20190806202208849_jvs5g.png
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return this.uploadFile(file, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        //key: 这里不能以/开头
        String newName = validateModule(file, null);
        //key: 这里不能以/开头
        String requestKey = "upload/" + StringUtils.defaultString(modules, "default") + "/" +  newName;
        //这里增加一个前缀区分一下是测试环境还是正式环境
        boolean isProd = "prod".equalsIgnoreCase(SpringUtil.getActiveProfile());
        if (!isProd) {
            requestKey = SpringUtil.getActiveProfile() + "/" + requestKey;
        }

        /**
         * https://oss.console.aliyun.com/
         * 这些阿里云 oss 参数都需要替换
         * <p>
         * 如果是内网的话，访问速度肯定更快，内网不限制速度。
         */
        String endpoint = aliyunOssConfig.getEndpoint();
        String endpointInternal = endpoint.replace(".aliyuncs.com", "-internal.aliyuncs.com");

        long mb5 = 5 * 1024 * 1024L;
        if (file.getSize() > mb5) {
            //大于5mb,我们就分片上传
            this.ossUploadFileBigMultiable(isProd ? endpointInternal : endpoint, requestKey, file);
        } else {
            //否则，我们常规上传
            this.ossUploadFileSmall(isProd ? endpointInternal : endpoint, requestKey, file);
        }

        // 解析结果
        // 注意，这里可能 需要 replace
        String accessPath;
        if (StringUtils.isNotBlank(aliyunOssConfig.getDomain())) {
            accessPath = aliyunOssConfig.getDomain() + "/" + requestKey;
        } else {
            accessPath = "https://" + aliyunOssConfig.getBucketName() + "." + aliyunOssConfig.getEndpoint() + "/" + requestKey;
        }
        return accessPath;
    }

    /**
     * demo 地址 https://help.aliyun.com/learn/learningpath/oss.html
     * https://github.com/aliyun/aliyun-oss-java-sdk/blob/master/src/samples/DeleteObjectsSample.java
     */
    @Override
    public boolean deleteFile(String fileUrl) {
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSS client = new OSSClientBuilder().build(aliyunOssConfig.getEndpoint(), aliyunOssConfig.getAccessKeyId(), aliyunOssConfig.getAccessKeySecret());
        String storePath = getStorePath(fileUrl);
        List<String> keys = new ArrayList<>();
        keys.add(storePath);

        try {
            /*
             * Delete all objects uploaded recently under the bucket
             */
            log.info("\nDeleting all objects:");
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(
                    new DeleteObjectsRequest(aliyunOssConfig.getBucketName()).withKeys(keys));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for (String object : deletedObjects) {
                log.info("\t" + object);
            }
            return true;
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message: " + oe.getErrorCode());
            log.error("Error Code:       " + oe.getErrorCode());
            log.error("Request ID:      " + oe.getRequestId());
            log.error("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
        return false;
    }

    /**
     * 转换url
     *
     * @param filePath https://hiber2019.oss-cn-shanghai.aliyuncs.com/upload/default/20190806202208849_jvs5g.png
     *                 eg2: 上传之后的格式：https://react-yuebaoxiao-pro.oss-cn-shanghai.aliyuncs.com/dev//upload/default/20210717-a77f6bb0-7b0a-4ef1-a839-f8e8aca469b8.jpeg
     * @return upload/default/20190806202208849_jvs5g.png
     */
    private String getStorePath(String filePath) {
        String bucketName = aliyunOssConfig.getBucketName();
        String endPoint = aliyunOssConfig.getEndpoint();
        String domain = aliyunOssConfig.getDomain();
        String publicPath1 = "https://" + bucketName + "." + endPoint + "/";
        String publicPath2 = "http://" + bucketName + "." + endPoint + "/";
        String publicPath3 = domain + "/";

        String oldPath = filePath;
        filePath = filePath.replace(publicPath1, "");
        filePath = filePath.replace(publicPath2, "");
        filePath = filePath.replace(publicPath3, "");

        if (oldPath.equals(filePath)) {
            // 使用方式2
            String group = "upload";
            // 获取group起始位置
            int pathStartPos = filePath.indexOf(group) + 1;
            filePath = filePath.substring(pathStartPos, filePath.length());
        }
        return filePath;
    }

    /**
     * oss上传方式1：小文件
     *
     * @param picturePath 文件的访问路径，access url
     * @param file        等待上传的文件
     * @return picturePath 上传文件的相对路径
     * 官网：https://help.aliyun.com/document_detail/84781.html?spm=a2c4g.11186623.6.948.64b014a0QG5CsQ
     * demo https://github.com/aliyun/aliyun-oss-java-sdk/blob/master/src/samples/UploadSample.java
     */
    private String ossUploadFileSmall(String endpoint, String picturePath, MultipartFile file) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, aliyunOssConfig.getAccessKeyId(), aliyunOssConfig.getAccessKeySecret());
        try {
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(aliyunOssConfig.getBucketName(), picturePath, file.getInputStream());
        } catch (OSSException oe) {
            // 使用OSS的API接口或SDK时提示“SignatureDoesNotMatch”签名相关的报错
            // SignatureDoesNotMatch: The request signature we calculated does not match the signature you provided. Check your key and signing method
            // https://help.aliyun.com/knowledge_detail/39637.html?spm=5176.21213303.J_6028563670.7.74bc3edaCiPpyc&scm=20140722.S_help%40%40%E7%9F%A5%E8%AF%86%E7%82%B9%40%4039637.S_hot.ID_39637-OR_s%2Bhelpproduct-V_1-P0_0
            if ("SignatureDoesNotMatch".equalsIgnoreCase(oe.getErrorCode())) {
                log.error("SignatureDoesNotMatch access-key-secret错误； 或者 key 和 secret不匹配。");
            }
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message:  {}", oe.getErrorMessage());
            log.error("Error Code  {}:   ", oe.getErrorCode());
            log.error("Request ID  {}:   ", oe.getRequestId());
            log.error("Host ID  {}:      ", oe.getHostId());
            log.error("");

            log.error("图片上传失败(OSS)", oe);
            oe.printStackTrace();
            throw new IOException("图片上传失败(OSS)");
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
            log.error("");

            log.error("图片上传失败(Client)", ce);
            ce.printStackTrace();
            throw new IOException("图片上传失败(Client)");
        } catch (Throwable e) {
            log.error("图片上传失败(Throwable)", e);
            e.printStackTrace();

           /* try {
                //保存错误日志
                SysLogService sysLogService = SpringContextUtils.getBean(SysLogService.class);
                sysLogService.saveExceptionLog(e, "图片上传失败", SysLogEntity.OPERATION_WARN_LEVEL_1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }*/

            throw new IOException("图片上传失败(Throwable)");
        } finally {
            ossClient.shutdown();
        }
        return picturePath;
    }

    // ================================= 一下全是大文件上传的代码 ===============================================

    /**
     * oss上传方式2：大文件（分片上传）
     *
     * @param picturePath 文件的访问路径，access url, 也是 oss中的唯一存放地址
     * @param file        等待上传的文件
     * @return picturePath 上传文件的相对路径
     * demo https://github.com/aliyun/aliyun-oss-java-sdk/blob/master/src/samples/MultipartUploadSample.java
     * <p>
     * 除了通过PUT Object接口上传文件到OSS以外，OSS还提供了另外一种上传模式——Multipart Upload。 https://help.aliyun.com/document_detail/31991.html
     */
    private String ossUploadFileBigMultiable(String endpoint, String picturePath, MultipartFile file) throws IOException {
        String requestKey = String.valueOf(picturePath);
        List<PartETag> partETags = Collections.synchronizedList(new ArrayList<>());
        /**
         * 线程池
         * Executors.newFixedThreadPool(10);
         * 这里不限制大小，线程会按照最大能力，开启，限制了大小，比如：10， 就一共只开启这么多线程
         * <p>
         * private static ExecutorService executorService = Executors.newCachedThreadPool();
         */
        ExecutorService executorService = new ThreadPoolExecutor(50, 1000, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("aliyun-oss-upload(" + requestKey + ")-thread-pool-%d").build());

        /*
         * Constructs a client instance with your account for accessing OSS
         */
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        conf.setIdleConnectionTime(1000);
        OSS client = new OSSClientBuilder().build(endpoint, aliyunOssConfig.getAccessKeyId(), aliyunOssConfig.getAccessKeySecret(), conf);

        try {
            /*
             * Claim a upload id firstly
             */
            InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(aliyunOssConfig.getBucketName(), requestKey);
            InitiateMultipartUploadResult result = client.initiateMultipartUpload(request);
            String uploadId = result.getUploadId();

            /*
             * Calculate how many parts to be divided
             */
            final long partSize = 5 * 1024 * 1024L;   // 5MB
            long fileLength = file.getSize();
            int partCount = (int) (fileLength / partSize);
            if (fileLength % partSize != 0) {
                partCount++;
            }
            if (partCount > 10000) {
                throw new RuntimeException("Total parts count should not exceed 10000");
            }
            /*
             * Upload multiparts to your bucket
             */
            if (log.isInfoEnabled()) {
                log.info("Begin to upload multiparts to OSS from a file\n");
            }
            for (int i = 0; i < partCount; i++) {
                long startPos = i * partSize;
                long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                executorService.execute(new PartUploader(client, requestKey, partETags, file, startPos, curPartSize, i + 1, uploadId));
            }

            /*
             * Waiting for all parts finished
             */
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                try {
                    executorService.awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           /* try {
                //等待所有任务执行完毕， 这里 不限定20分钟必须执行完毕
                countDownLatch.await(60 * 20, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.error("所有线程都已经执行完毕...., 一共开启线程数量: {}", partCount);
            }*/

            /*
             * Verify whether all parts are finished
             */
            if (partETags.size() != partCount) {
                throw new RuntimeException("Upload multiparts fail due to some parts are not finished yet");
            } else {
                if (log.isInfoEnabled()) {
                    log.info("Succeed to complete multiparts into an object named {} \n", requestKey);
                }
            }

            /*
             * View all parts uploaded recently
             */
            listAllParts(client, requestKey, uploadId);

            /*
             * Complete to upload multiparts
             */
            completeMultipartUpload(client, partETags, requestKey, uploadId);

            /*
             * Fetch the object that newly created at the step below.
             */
            if (log.isInfoEnabled()) {
                log.info("Fetching an object");
            }
            OSSObject ossObject = client.getObject(new GetObjectRequest(aliyunOssConfig.getBucketName(), requestKey));
            if (log.isInfoEnabled()) {
                log.info(ossObject.getKey());
            }
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message:  {}", oe.getErrorMessage());
            log.error("Error Code  {}:   ", oe.getErrorCode());
            log.error("Request ID  {}:   ", oe.getRequestId());
            log.error("Host ID  {}:      ", oe.getHostId());
            log.error("");

            log.error("图片上传失败(OSS)", oe);
            oe.printStackTrace();
            throw new IOException("图片上传失败(OSS Multipart)");
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: " + ce.getMessage());
            log.error("");

            log.error("图片上传失败(Client)", ce);
            ce.printStackTrace();
            throw new IOException("图片上传失败(Client Multipart)");
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            if (client != null) {
                client.shutdown();
            }
        }

        return picturePath;
    }

    private class PartUploader implements Runnable {
        private MultipartFile file;
        private long startPos;

        private long partSize;
        private int partNumber;
        private String uploadId;

        private OSS client;
        private String requestKey;
        private final List<PartETag> partETags;

        private PartUploader(OSS client, String requestKey, List<PartETag> partETags, MultipartFile file, long startPos, long partSize, int partNumber, String uploadId) {
            this.client = client;
            this.requestKey = requestKey;
            this.partETags = partETags;

            this.file = file;
            this.startPos = startPos;
            this.partSize = partSize;
            this.partNumber = partNumber;
            this.uploadId = uploadId;
        }

        @Override
        public void run() {
            InputStream instream = null;
            try {
                instream = file.getInputStream();
                instream.skip(this.startPos);

                UploadPartRequest uploadPartRequest = new UploadPartRequest();
                uploadPartRequest.setBucketName(aliyunOssConfig.getBucketName());
                uploadPartRequest.setKey(this.requestKey);
                uploadPartRequest.setUploadId(this.uploadId);
                uploadPartRequest.setInputStream(instream);
                uploadPartRequest.setPartSize(this.partSize);
                uploadPartRequest.setPartNumber(this.partNumber);

                UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);
                if (log.isInfoEnabled()) {
                    log.info("Part {} done\n", this.partNumber);
                }

                synchronized (partETags) {
                    partETags.add(uploadPartResult.getPartETag());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (instream != null) {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void completeMultipartUpload(OSS client, List<PartETag> partETags, String requestKey, String uploadId) {
        // Make part numbers in ascending order
        partETags.sort(Comparator.comparingInt(PartETag::getPartNumber));

        if (log.isInfoEnabled()) {
            log.info("Completing to upload multiparts\n");
        }

        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(aliyunOssConfig.getBucketName(), requestKey, uploadId, partETags);
        client.completeMultipartUpload(completeMultipartUploadRequest);
    }

    private void listAllParts(OSS client, String requestKey, String uploadId) {
        if (log.isInfoEnabled()) {
            log.info("Listing all parts......");
        }
        ListPartsRequest listPartsRequest = new ListPartsRequest(aliyunOssConfig.getBucketName(), requestKey, uploadId);
        PartListing partListing = client.listParts(listPartsRequest);

        int partCount = partListing.getParts().size();
        for (int i = 0; i < partCount; i++) {
            PartSummary partSummary = partListing.getParts().get(i);
            if (log.isInfoEnabled()) {
                log.info("\tPart# {} , ETag={}", partSummary.getPartNumber(), partSummary.getETag());
            }
        }
        if (log.isInfoEnabled()) {
            log.info("\n");
        }
    }

    @Override
    public String objectsCapacityStr() {
        OSS client = new OSSClientBuilder().build(aliyunOssConfig.getEndpoint(), aliyunOssConfig.getAccessKeyId(), aliyunOssConfig.getAccessKeySecret());
        final int maxKeys = 200;
        String nextMarker = null;
        ObjectListing objectListing;

        long size = 0L;
        String result = "";

        do
        {
            objectListing = client.listObjects(new ListObjectsRequest(aliyunOssConfig.getBucketName()).withMarker(nextMarker).withMaxKeys(maxKeys));

            List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
            for (OSSObjectSummary s : sums) {
                size += s.getSize() / 1024;
            }
            nextMarker = objectListing.getNextMarker();

        } while (objectListing.isTruncated());
        client.shutdown();

        /*if (size > (1024 * 1024)) {
            result = (new BigDecimal((double) size / 1024 / 1024)).setScale(2, BigDecimal.ROUND_HALF_UP) + "GB";
        } else if (size > 1024) {
            result = (new BigDecimal((double) size / 1024).setScale(2, BigDecimal.ROUND_HALF_UP)) + "MB";
        } else {
            result = size + "KB";
        }*/
        result = FileUtil.readableFileSize(size);
        return result;
    }

    /**
     * 阿里云 对象存储 oss 使用签名URL进行临时授权
     * https://help.aliyun.com/document_detail/32016.html?spm=a2c4g.11186623.6.992.7a943b4aPjkyTA#title-pu8-5o8-x7j
     *
     * @param objectName 完成的url, filePath
     * @return 返回url签名之后的url
     */
    public String getStsURL(String objectName) {
        if (StringUtils.isBlank(objectName)) {
            return objectName;
        }
        try {
            objectName = new URL(objectName).getPath();
            if (StringUtils.isBlank(objectName)) {
                return objectName;
            }
            if (objectName.startsWith("/")) {
                objectName = objectName.replaceFirst("/", ""); // 不能以/ 开头。例如 /dev/upload/123.jpg，需要转为 dev/upload/123.jpg
            }
        } catch (MalformedURLException e) {
            // 忽略
        }
        OSS ossClient = new OSSClientBuilder().build(aliyunOssConfig.getEndpoint(), aliyunOssConfig.getAccessKeyId(), aliyunOssConfig.getAccessKeySecret());
        // 设置URL过期时间为12小时，最大值就是43200
        Date expiration = new Date(System.currentTimeMillis() + (43200 * 1000));
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(aliyunOssConfig.getBucketName(), objectName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }
}
