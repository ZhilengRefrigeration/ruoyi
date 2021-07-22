package com.ruoyi.file.config;

import com.ruoyi.file.service.AliyunOssDsfServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun oss  https://help.aliyun.com/learn/learningpath/oss.html ,需要购买
 * 使用STS临时访问凭证访问OSS https://help.aliyun.com/document_detail/100624.html?spm=5176.8466032.help.dexternal.5ded1450am61oS
 *
 * @author dazer
 *
 * @see AliyunOssDsfServiceImpl
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = AliyunOssConfig.PREFIX)
public class AliyunOssConfig {
    public static final String PREFIX = "aliyun-oss";
    /**
     * aliyun oss相关配置
     * ACCESS_KEY_SECRET
     * AccessKeyId 【secretKey】eg:LTAI4GFov2QymkmPf9cXdH5z
     * AccessKeySecret 【secretKey】 eg:ap8nmIvD1TctcCLsADS4JbkOoXOluW
     * BucketName eg:yuebaoxiao
     * Endpoint 对象存储服务的URL  eg:oss-cn-shenzhen.aliyuncs.com
     *
     * ak 获取地址：https://ak-console.aliyun.com/#/accesskey
     *
     * 具体项目里面必须替换掉
     */
    private String accessKey = "";
    private String secretKey = "";
    private String bucketName = "";
    private String endpoint = "";
    /**
     * hostHttps: 是否开启了https, 需要在控制台配置
     * https://oss.console.aliyun.com/bucket/oss-cn-shanghai/hiber2019/domain
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
     * 文档：对象存储 授权访问 https://help.aliyun.com/document_detail/32016.html?spm=a2c4g.11186623.6.996.335b6d13O5xgUH
     *
     * 注意！！：aliyun oss 设置Bucket ACL: bucket==>权限管理==>读写权限
     * 1、私有 【必须要加签之后才能访问】
     * 2、公共读
     * 3、公共读写
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
        if (expiryDuration == null) {
            // 默认一个小时, 3600秒
            expiryDuration = 3600L;
        }
        if (expiryDuration < 1L && expiryDuration != -1) {
            // 最小1秒
            // 如果要永不过期，就不要调用 -1； 直接原样返回
            expiryDuration = 1L;
        }
        return expiryDuration;
    }

    public void setExpiryDuration(Long expiryDuration) {
        this.expiryDuration = expiryDuration;
    }
}
