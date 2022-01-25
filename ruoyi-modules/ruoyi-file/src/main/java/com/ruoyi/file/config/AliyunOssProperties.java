package com.ruoyi.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云oss配置属性
 *
 * @author xiejs
 * @since 2022-01-25
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class AliyunOssProperties {

    /**
     * 文件地址
     */
    private String endpoint;

    /**
     *keyId
     */
    private String keyId;

    /**
     * 密钥
     */
    private String keySecret;

    /**
     * 文件分类
     */
    private String bucketName;

}
