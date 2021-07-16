package com.ruoyi.file.service;

import cn.hutool.extra.spring.SpringUtil;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.ruoyi.common.core.utils.SpringUtils;
import com.ruoyi.file.utils.FileUploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
public class CephSysFileServiceImpl implements ISysFileService {
    private static final Logger log = LoggerFactory.getLogger(AliyunOssFileServiceImpl.class);

    protected static AmazonS3 amazonS3 = null;
    /**
     * s3 提供的 accessKey secretKey
     * BUCKET_NAME： 概念和阿里云 oss 一模一样
     */
    private static String ACCESS_KEY = "XPVF8TESA1X4SFU*****";
    private static String SECRET_KEY = "hBBEFpV3qsyI7HAdCBzA2ZdAhuANJFRIUz****";
    private static String HOST = "127.0.0.1";
    private static String BUCKET_NAME = "dfwwbook";
    /**
     * 域名绑定
     * USER_DOMAIN_NAME: 域名名称， oss 访问路径绑定的用户自定义域名； 如果没有，就设置为null
     * hostHttps: 是否开启了https, 需要在控制台配置
     * https://oss.console.aliyun.com/bucket/oss-cn-shanghai/hiber2019/domain
     * <p>
     * private static final String USER_DOMAIN_NAME = "image.jl-media.cn";
     */
    private static final String USER_DOMAIN_NAME = null;
    private static final boolean HOST_HTTPS = true;

    /**
     * ceph配置初始化
     */
    static {
        log.info("开始初始化ceph配置");
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTP);
        amazonS3 = new AmazonS3Client(credentials, clientConfiguration);
        amazonS3.setEndpoint(HOST);
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
        String newName =  validateModule(file, null);
        //key: 这里不能以/开头
        String requestKey = "upload/" + newName;
        //这里增加一个前缀区分一下是测试环境还是正式环境
        boolean isProd = "prod".equalsIgnoreCase(SpringUtil.getActiveProfile());
        if (!isProd) {
            requestKey = SpringUtil.getActiveProfile() + "/" + requestKey;
        }

        // long mb5 = 5 * 1024 * 1024L;
        //大于5mb,我们就分片上传
        PutObjectResult result = amazonS3.putObject(BUCKET_NAME, requestKey, file.getInputStream(), new ObjectMetadata());
        // 上传成功
        if (result.isRequesterCharged()) {
            // 解析结果
            // 注意，这里可能 需要 replace
            String accessPath;
            if (StringUtils.isNotBlank(USER_DOMAIN_NAME)) {
                if (HOST_HTTPS) {
                    accessPath = "https://" + USER_DOMAIN_NAME + "/" + requestKey;
                } else {
                    accessPath = "http://" + USER_DOMAIN_NAME + "/" + requestKey;
                }
            } else {
                accessPath = "https://" + BUCKET_NAME + "/" + requestKey;
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
        amazonS3.deleteObject(BUCKET_NAME, storePath);
        return true;
    }

    @Override
    public String listObject() {
        return null;
    }

    /**
     * 转换url
     *
     * @param filePath https://hiber2019.oss-cn-shanghai.aliyuncs.com/upload/default/20190806202208849_jvs5g.png
     * @return upload/default/20190806202208849_jvs5g.png
     */
    private String getStorePath(String filePath) {
        String publicPath1 = "https://" + BUCKET_NAME + "/";
        String publicPath2 = "http://" + BUCKET_NAME + "/";
        String publicPath3 = "https://" + USER_DOMAIN_NAME + "/";
        String publicPath4 = "http://" + USER_DOMAIN_NAME + "/";
        //String publicPath5 = ServletCacheUtils.getInstance().getHttpRootPath();

        filePath = filePath.replace(publicPath1, "");
        filePath = filePath.replace(publicPath2, "");
        filePath = filePath.replace(publicPath3, "");
        filePath = filePath.replace(publicPath4, "");
        //filePath = filePath.replace(publicPath5, "");
        return filePath;
    }
}
