package com.ruoyi.file.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.file.config.AliyunOssProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 获取ossClient
 * @author xiejs
 * @since 2022-03-15
 */
@Component
public class OssClient implements BeanPostProcessor {

    public static final String HTTPS = "https://";

    public static final String DOT = ".";

    public static final String SLASH = "/";

    public static String endpoint;
    public static String keyId;
    public static String keySecret;
    public static String bucketName;

    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        OssClient.endpoint = aliyunOssProperties.getEndpoint();
        OssClient.keyId = aliyunOssProperties.getKeyId();
        OssClient.keySecret = aliyunOssProperties.getKeySecret();
        OssClient.bucketName = aliyunOssProperties.getBucketName();
        return bean;
    }


    /**
     * 获取oss实例
     *
     * @return OSS
     */
    public OSS getOssClient() {
        String endpoint = aliyunOssProperties.getEndpoint();
        String keyId = aliyunOssProperties.getKeyId();
        String keySecret = aliyunOssProperties.getKeySecret();
        return new OSSClientBuilder().build(endpoint,
                keyId, keySecret);
    }


}
