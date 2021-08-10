package com.ruoyi.file.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.extra.spring.SpringUtil;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.ruoyi.file.config.CephConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * @author dazer
 * @date 2020-4-4
 * ceph实现s3文件上传
 * 上传文件相关
 * 参考网址：https://blog.csdn.net/qq_32524177/article/details/76226257
 * ceph储存的S3接口实现 或者支持文件挂载的方式（NFS NAS挂载）
 * https://docs.aws.amazon.com/zh_cn/AmazonS3/latest/dev/RetrievingObjectUsingJava.html
 * 参考：Amazonaws S3 java SDK连接初探  https://www.cnblogs.com/zmdd/p/9342510.html
 * =========================================================================================================================
 * 部署：
 * 1:https://ceph.com/planet/%E5%9F%BA%E4%BA%8Edocker%E9%83%A8%E7%BD%B2ceph%E4%BB%A5%E5%8F%8A%E4%BF%AE%E6%94%B9docker-image/
 * 基于docker部署ceph以及修改docker image
 * 2:ceph存储，使用docker部署 https://www.cnblogs.com/bladeyul/p/10649049.html
 * 3:使用docker 搭建 ceph 开发环境，使用aws sdk 存储数据 https://blog.csdn.net/freewebsys/article/details/79553386
 */
@Service()
public class CephDfsServiceImpl implements IDfsService {
    private static final Logger log = LoggerFactory.getLogger(CephDfsServiceImpl.class);
    @Autowired
    private CephConfig cephConfig;

    protected static AmazonS3 amazonS3 = null;

    /**
     * ceph配置初始化
     */
    @PostConstruct
    void init() {
        log.info("开始初始化ceph配置");
        AWSCredentials credentials = new BasicAWSCredentials(cephConfig.getAccessKey(), cephConfig.getSecretKey());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);
        amazonS3 = new AmazonS3Client(credentials, clientConfiguration);
        amazonS3.setEndpoint(cephConfig.getEndpoint());
        log.info("ceph配置初始化成功");
    }

    /**
     * 使用s3 api 创建 ceph bucket
     *
     * @param bucketname
     * @return
     */
    public Bucket createBucket(String bucketname) {
        Bucket bucket = amazonS3.createBucket(bucketname);
        log.info("bucket name is {}", bucket);
        return bucket;
    }

    /**
     * 根据 bucketName 和 filename 获取指定文件返回输入流
     */
    public S3ObjectInputStream getObject(String bucketName, String filename) {
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucketName, filename));
        S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
        return s3ObjectInputStream;
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return this.uploadFile(file, null);
    }

    @Override
    public String uploadFile(MultipartFile file, String modules) throws Exception {
        //key: 这里不能以/开头
        validateModule(file, modules);
        String newName = extractFileNameSimple(file);
        //key: 这里不能以/开头
        String requestKey = "upload/" + StringUtils.defaultString(modules, "default") + "/" +  newName;
        //这里增加一个前缀区分一下是测试环境还是正式环境
        boolean isProd = "prod".equalsIgnoreCase(SpringUtil.getActiveProfile());
        if (!isProd) {
            requestKey = SpringUtil.getActiveProfile() + "/" + requestKey;
        }

        // long mb5 = 5 * 1024 * 1024L;
        //大于5mb,我们就分片上传
        PutObjectResult result = amazonS3.putObject(cephConfig.getBucketName(), requestKey, file.getInputStream(), new ObjectMetadata());
        // 上传成功
        if (result.isRequesterCharged()) {
            // 解析结果
            // 注意，这里可能 需要 replace
            String accessPath;
            if (StringUtils.isNotBlank(cephConfig.getDomain())) {
                accessPath = cephConfig.getDomain() + "/" + requestKey;
            } else {
                accessPath = "https://" + cephConfig.getBucketName() + "." + cephConfig.getEndpoint() + "/" + requestKey;
            }
            return accessPath;
        }
        return null;
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return false;
        }
        String storePath = getStorePath(fileUrl);
        amazonS3.deleteObject(cephConfig.getBucketName(), storePath);
        return true;
    }

    @Override
    public String objectsCapacityStr() {
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.set(0);
        String result;

        amazonS3.listObjects(cephConfig.getBucketName()).getObjectSummaries().forEach(s3ObjectSummary -> {
            try {
                atomicLong.addAndGet(s3ObjectSummary.getSize() / 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        long size = atomicLong.get();
        if (size > (1024 * 1024)) {
            result = (new BigDecimal((double) size / 1024 / 1024)).setScale(2, BigDecimal.ROUND_HALF_UP) + "GB";
        } else if (size > 1024) {
            result = (new BigDecimal((double) size / 1024).setScale(2, BigDecimal.ROUND_HALF_UP)) + "MB";
        } else {
            result = size + "KB";
        }

        return result;
    }

    @Override
    public String presignedUrl(String fileUrl) {
        String storePath = getStorePath(fileUrl);
        if (cephConfig.getExpiryDuration() == -1) {
            return fileUrl;
        }
        Date expiration = new DateTime(System.currentTimeMillis() + cephConfig.getExpiryDuration());
        URL url = amazonS3.generatePresignedUrl(cephConfig.getBucketName(), storePath, expiration);
        return url.toString();
    }

    /**
     * 转换url
     *
     * @param filePath https://hiber2019.oss-cn-shanghai.aliyuncs.com/upload/default/20190806202208849_jvs5g.png
     * @return upload/default/20190806202208849_jvs5g.png
     */
    private String getStorePath(String filePath) {
        String publicPath1 = "https://" + cephConfig.getBucketName() + "/";
        String publicPath2 = "http://" + cephConfig.getBucketName() + "/";
        String publicPath3 = "https://" + cephConfig.getDomain() + "/";
        String publicPath4 = "http://" + cephConfig.getDomain() + "/";

        filePath = filePath.replace(publicPath1, "");
        filePath = filePath.replace(publicPath2, "");
        filePath = filePath.replace(publicPath3, "");
        filePath = filePath.replace(publicPath4, "");
        return filePath;
    }
}
