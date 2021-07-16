package com.ruoyi.file.config;

import com.ruoyi.file.service.CephDfsServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @see CephDfsServiceImpl
 * @author dazer
 */
@RefreshScope
@Configuration
@ConfigurationProperties(
        prefix = "ceph"
)
public class CephConfig {
    /**
     * s3 提供的 accessKey secretKey
     * 示例：
     * AccessKey: XPVF8TESA1X4SFU*****
     * SecretKey: hBBEFpV3qsyI7HAdCBzA2ZdAhuANJFRIUz****
     * BUCKET_NAME： 概念和阿里云 oss 一模一样
     * endpoint: 127.0.0.1 or oss-cn-shenzhen.aliyuncs.com
     */
    private String accessKey = "";
    private String secretKey = "";
    private String endpoint = "127.0.0.1";
    private String bucketName = "";
    /**
     * 访问地址，访问地址经常和上传地址不一样；
     * 1：上传地址是内网； 访问地址外网；
     * 2：上传地址ip; 访问地址域名；
     * 3-1：eg: https://dfwwbook.oss-cn-zhangjiakou.aliyuncs.com/upload/product/20210625165727777_dzdpl.jpg
     * 3-2：eg: https://dfwwbook.dfww.com.cn/upload/product/20210625165727777_dzdpl.jpg
     * 4: 示例：domain： https://dfwwbook.dfww.com.cn
     * 最后带不带斜杠都可以
     */
    private String domain = null;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
