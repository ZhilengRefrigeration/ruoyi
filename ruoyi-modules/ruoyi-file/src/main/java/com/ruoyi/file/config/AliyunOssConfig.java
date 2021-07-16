package com.ruoyi.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun oss  https://help.aliyun.com/learn/learningpath/oss.html ,需要购买
 *
 * @author dazer
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig {
    /**
     * aliyun oss相关配置
     * ACCESS_KEY_SECRET
     * AccessKeyId eg:LTAI4GFov2QymkmPf9cXdH5z
     * AccessKeySecret eg:ap8nmIvD1TctcCLsADS4JbkOoXOluW
     * BucketName eg:yuebaoxiao
     * Endpoint eg:oss-cn-shenzhen.aliyuncs.com
     */
    private String accessKeyId;
    private String accessKeySecret;
    private String ossBucketName;
    private String ossEndpoint;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getOssBucketName() {
        return ossBucketName;
    }

    public void setOssBucketName(String ossBucketName) {
        this.ossBucketName = ossBucketName;
    }

    public String getOssEndpoint() {
        return ossEndpoint;
    }

    public void setOssEndpoint(String ossEndpoint) {
        this.ossEndpoint = ossEndpoint;
    }
}
