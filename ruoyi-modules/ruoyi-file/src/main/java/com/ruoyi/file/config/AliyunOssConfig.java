package com.ruoyi.file.config;

import com.ruoyi.file.service.AliyunOssDsfServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun oss  https://help.aliyun.com/learn/learningpath/oss.html ,需要购买
 *
 * @author dazer
 *
 * @see AliyunOssDsfServiceImpl
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "aliyunoss")
public class AliyunOssConfig {
    /**
     * aliyun oss相关配置
     * ACCESS_KEY_SECRET
     * AccessKeyId eg:LTAI4GFov2QymkmPf9cXdH5z
     * AccessKeySecret eg:ap8nmIvD1TctcCLsADS4JbkOoXOluW
     * BucketName eg:yuebaoxiao
     * Endpoint eg:oss-cn-shenzhen.aliyuncs.com
     *
     * ak 获取地址：https://ak-console.aliyun.com/#/accesskey
     *
     * 具体项目里面必须替换掉
     */
    private String accessKeyId = "";
    private String accessKeySecret = "";
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
}
