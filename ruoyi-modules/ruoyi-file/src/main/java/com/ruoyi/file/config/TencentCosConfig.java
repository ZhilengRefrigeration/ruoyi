package com.ruoyi.file.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import io.minio.MinioClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯云文件存储 cos 配置
 * 对象存储文档api地址：https://cloud.tencent.com/document/product/436/6474
 * 控制台地址：https://console.cloud.tencent.com/cos5
 * API密钥获取地址：https://console.cloud.tencent.com/cam/capi
 * Region | endpoint, bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
 * 历史版本地域列表 https://cloud.tencent.com/document/product/436/7777
 *
 * @author yabo
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = TencentCosConfig.PREFIX)
public class TencentCosConfig {
    public static final String PREFIX = "tencent-cos";

    @Bean
    public COSClient getCosClient() {
        //初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台进行查看和管理
        String secretId = this.getAccessKey();
        String secretKey = this.getSecretKey();
        String endpoint = this.getEndpoint();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(endpoint);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 设置最大重试次数为 1 次
        clientConfig.setMaxErrorRetry(1);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    /**
     * ak，同其他 平台
     *
     * @see AliyunOssConfig#getAccessKey()
     * @see QiniuKodoConfig#getAccessKey()
     * @see MinioConfig#getAccessKey()
     * eg: AKIDX9hNAzpdUI0XyRpASj098xa7uYzOekmh
     * 别名：SecretId
     */
    private String accessKey = "";
    /**
     * sk, 同其他 平台
     *
     * @see AliyunOssConfig#getSecretKey()
     * @see QiniuKodoConfig#getSecretKey()
     * @see MinioConfig#getSecretKey()
     * eg: sW5VgkdHlDYqy01xiGbkjV5TghUEvYEw
     * 别名：SecretKey
     */
    private String secretKey = "";
    /**
     * bucket，同其他平台 桶概念
     *
     * @see AliyunOssConfig#getBucketName()
     * @see QiniuKodoConfig#getBucketName()
     * @see MinioConfig#getBucketName()
     */
    private String bucketName = "";
    /**
     * endpoint，同其他平台 概念；
     * 别名：Region
     *
     * @see AliyunOssConfig#getEndpoint()
     * @see QiniuKodoConfig 有，Region 目前使用的是自动获取存储区域；暂不启用
     * @see MinioConfig 目前私有部署，不存在endpoint
     * eg: ap-chengdu 地域和访问域名 https://cloud.tencent.com/document/product/436/6224
     */
    private String endpoint = "";
    /**
     * <p>
     * 上传地址和访问地址经常是不一样的
     * 1、上传地址是内网；访问地址是外网；
     * 2、上传地址是ip； 访问地址域名；
     * 3、上传地址是http; 访问地址是https
     * 4: eg: https://image.jl-media.cn
     */
    private String domain = null;
    /**
     * 过期时间，单位秒；
     * 如：1小时就写：3600L
     * 如：9小时就写：32400L
     * 如：12小时就写：43200L, 【不支持】，最大是 32400（9小时）， 最小 1（1秒钟）
     * 如：-1： 就永不过期，原样返回url
     * 签名URL的默认过期时间为3600秒，最大值为32400秒
     * 文档：对象存储 授权访问 https://cloud.tencent.com/document/product/436/10199
     * <p>
     * 注意！！：tencent cos 设置Bucket ACL
     * 1、私有 【必须要加签之后才能访问】
     * 2、公共读
     * 3、公共读写
     */
    private Long expiryDuration = -1L;

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

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getExpiryDuration() {
        if (expiryDuration != -1 && expiryDuration < 0) {
            //  最小是1秒
            expiryDuration = 1L;
        }
        return expiryDuration;
    }

    public void setExpiryDuration(Long expiryDuration) {
        this.expiryDuration = expiryDuration;
    }
}
