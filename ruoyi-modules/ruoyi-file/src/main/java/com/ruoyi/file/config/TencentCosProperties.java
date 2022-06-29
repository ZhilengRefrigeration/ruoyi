package com.ruoyi.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 腾讯对象存储属性
 * @author xiejs
 * @since 2022-06-29
 */
@Data
@Component
@ConfigurationProperties(prefix = "tencent.cos.file")
public class TencentCosProperties {

    /**
     * 地域节点
     */
    private String region;


    private String secretId;


    private String secretKey;

    /**
     * 存储桶名称
     */
    private String bucketName;


    private String endpoint;


}
