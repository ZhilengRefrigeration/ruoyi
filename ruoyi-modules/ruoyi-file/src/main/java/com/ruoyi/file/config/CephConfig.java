package com.ruoyi.file.config;

import com.ruoyi.file.service.CephSysFileServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @see CephSysFileServiceImpl
 * @author dazer
 */
@RefreshScope
@Configuration
@ConfigurationProperties(
        prefix = CephConfig.PREFIX
)
public class CephConfig {
    public static final String PREFIX = "ceph";
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
    /**
     * 过期时间，单位秒；
     * 如：1小时就写：3600L
     * 如：9小时就写：32400L
     * 如：12小时就写：43200L, 【不支持】，最大是 未知， 最小 1（1秒钟）
     * 如：-1： 就永不过期，原样返回url
     * 签名URL的默认过期时间为3600秒，最大值为32400秒
     *
     * 注意！！：qoniu oss 设置Bucket私有，必须要有凭证才能访问 https://developer.qiniu.com/kodo/1202/download-token
     * 下载凭证(如果Bucket设置成私有，必须要有 下载凭证)，路径：【对象存储==》使用指南===》安全机制===》 下载凭证】
     */
    private Long expiryDuration = 32400L;

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

    public void setExpiryDuration(Long expiryDuration) {
        this.expiryDuration = expiryDuration;
    }

    public Long getExpiryDuration() {
        if (expiryDuration != -1 && expiryDuration < 0) {
            //  最小是1秒
            expiryDuration = 1L;
        }
        return expiryDuration;
    }
}
