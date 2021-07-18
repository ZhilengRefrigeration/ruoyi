package com.ruoyi.file.config;

import com.ruoyi.file.service.MinioDfsServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.minio.MinioClient;
import org.springframework.stereotype.Component;

/**
 * Minio 配置信息
 *
 * @author ruoyi
 * @see MinioDfsServiceImpl 实现
 */
@RefreshScope
@Component
@Configuration
@ConfigurationProperties(prefix = MinioConfig.PREFIX)
public class MinioConfig {
    public static final String PREFIX = "minio";
    /**
     * 服务地址
     * eg: http://192.168.254.100:9900
     */
    private String url;

    /**
     * 用户名
     * eg: D998GE6ZTQXSATTJWX35
     */
    private String accessKey;

    /**
     * 密码
     * eg: QZVQGnhIQQE734UYSUFlGOZViE6+ZlDEfUG3NjXJ
     */
    private String secretKey;

    /**
     * 存储桶名称
     * eg: mall
     */
    private String bucketName;

    /**
     * 访问域名; url经常是内网地址，外部访问用域名或者外网ip
     * eg: https://image.bj.gov.cn/appt-file
     */
    private String domain;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
    }
}
