package com.ruoyi.file.service;

import cn.hutool.extra.spring.SpringUtil;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.file.config.MinioConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * Minio 文件存储
 *
 * @author ruoyi
 * 官网博客： http://docs.minio.org.cn/docs/master/java-client-quickstart-guidec
 * 在springboot中使用Minio8 https://springboot.io/t/topic/3109
 * 1、上传操作：putObject
 * 2、删除操作：removeObject
 * ==================
 * 3、预签(Presigned)操作：【设置访问过期时间】：【支持对url进行鉴权：【sts】【临时授权】】presignedGetObject
 * presignedGetObject(String bucketName, String objectName, Integer expires)
 * http://docs.minio.org.cn/docs/master/java-client-api-reference#presignedGetObject
 * MinIO STS快速入门指南 http://docs.minio.org.cn/docs/master/minio-sts-quickstart-guide
 */
//@Primary
@Service()
public class MinioSysFileServiceImpl implements ISysFileService
{
    @Autowired
    private MinioConfig minioConfig;
    @Autowired
    private MinioClient minioClient;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        return this.uploadFile(file, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        validateModule(file ,modules);
        String fileName = StringUtils.defaultString(modules, "default") + "/" +  extractFileName(file);
        boolean isProd = "prod".equalsIgnoreCase(SpringUtil.getActiveProfile());
        if (!isProd) {
            fileName = SpringUtil.getActiveProfile() + "/" + fileName;
        }

        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        minioClient.putObject(args);
        //return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
        //形如：https://yq666.bj.gov.cn/appt-file/dev/default/2021/07/18/f4243eb2-06a1-4304-bdfc-e2964b8721bb.jpeg
        return minioConfig.getDomain() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        RemoveObjectArgs args = RemoveObjectArgs.builder().
                bucket(minioConfig.getBucketName()).
                object(getStorePath(fileUrl)).
                build();
        try {
            minioClient.removeObject(args);
            return true;
        } catch (ErrorResponseException |
                InsufficientDataException |
                InternalException |
                InvalidKeyException |
                InvalidResponseException |
                IOException |
                NoSuchAlgorithmException |
                ServerException |
                XmlParserException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String objectsCapacityStr() {
        /*MinioClient minioClient = MinioClient.builder().endpoint(minioConfig.getUrl())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();*/

        AtomicLong atomicLong = new AtomicLong();
        atomicLong.set(0);
        String result = "";

        ListObjectsArgs args = ListObjectsArgs.builder().bucket(minioConfig.getBucketName()).build();
        minioClient.listObjects(args).forEach(new Consumer<Result<Item>>() {
            @Override
            public void accept(Result<Item> itemResult) {
                try {
                    atomicLong.addAndGet(itemResult.get().size() / 1024);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        long size = atomicLong.get();
        if (size > (1024 * 1024)) {
            result = (BigDecimal.valueOf((double) size / 1024 / 1024)).setScale(2, BigDecimal.ROUND_HALF_UP) + "GB";
        } else if (size > 1024) {
            result = (BigDecimal.valueOf((double) size / 1024).setScale(2, BigDecimal.ROUND_HALF_UP)) + "MB";
        } else {
            result = size + "KB";
        }

        return result;
    }

    /**
     * 给原始的URL add 过期时间
     * [STS 临时授权]
     * http://docs.minio.org.cn/docs/master/minio-sts-quickstart-guide
     * minio SDKS Java Client API参考文档 http://docs.minio.org.cn/docs/master/java-client-api-reference
     * Presigned presignedGetObject 预签】
     * 示例：https://xxxx.xxx.gov.cn/file/2021/08/06/cd9dfbaa-8563-423a-bc3d-d0b15e781931.pdf?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=D99KGE6ZTQXSATTJWU24%2F20210809%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210809T075702Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=198c76edc57998f4dde72124952b43f0066c762356e485dd44d21df9cc7dad78
     */
    @Override
    public String presignedUrl(String fileUrl) {
        if (minioConfig.getExpiryDuration() == -1) {
            return fileUrl;
        }
        String signKey = "?X-Amz-Algorithm=";
        if (fileUrl.contains(signKey)) {
            return fileUrl;
        }
        String objectName = this.getStorePath(fileUrl);
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder().
                bucket(minioConfig.getBucketName()).
                method(Method.GET).
                object(objectName).
                expiry(minioConfig.getExpiryDuration(), TimeUnit.SECONDS).build();

        String presignedObjectUrl = null;
        try {
            presignedObjectUrl = minioClient.getPresignedObjectUrl(args);
            String basePrivateUrl = minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/";
            presignedObjectUrl = presignedObjectUrl.replace(basePrivateUrl, "");
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException | ServerException e) {
            e.printStackTrace();
            presignedObjectUrl = fileUrl;
        }
        return minioConfig.getDomain() + "/" + minioConfig.getBucketName() + "/" + presignedObjectUrl;
    }

    /**
     * 转换url，为原始的key
     *
     * @param filePath https://yq666.bj.gov.cn/appt-file/dev/default/2021/07/18/f4243eb2-06a1-4304-bdfc-e2964b8721bb.jpeg
     * @return dev/default/2021/07/18/f4243eb2-06a1-4304-bdfc-e2964b8721bb.jpeg
     */
    private String getStorePath(String filePath) {
        String oldPath = filePath;
        // 处理方式1
        String publicPath3 = minioConfig.getDomain() + "/" + minioConfig.getBucketName() + "/";
        filePath = filePath.replace(publicPath3, "");

        if (oldPath.equals(filePath)) {
            // 处理方式2
            String publicPath4 = minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/";
            filePath = filePath.replace(publicPath4, "");
        }
        return filePath;
    }
}
