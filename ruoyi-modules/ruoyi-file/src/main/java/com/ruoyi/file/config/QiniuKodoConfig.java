package com.ruoyi.file.config;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.ruoyi.file.service.QiniuDfsServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛 文件存储产品叫做：kodo
 * 对象存储：https://developer.qiniu.com/kodo/1239/java
 * @author dazer
 * @see UploadManager 上传核心类
 * @see Region 区域 or endpoint 【可选】
 * @see com.qiniu.storage.Configuration 配置类，可以配置 上传区域； 【可选】
 * @see QiniuDfsServiceImpl 实现
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = QiniuKodoConfig.PREFIX)
public class QiniuKodoConfig {
    public static final String PREFIX = "qiniu";
    /**
     * AccessKey 简称ak
     * 申请地址=>登录七牛云：https://portal.qiniu.com/user/key
     * ak 形如：pRYrSe_EW4sJHsQ6JyuiRYShA2JCLKtMhT-N4TQD
     * ===============================================
     * SecretKey 简称 sk
     * 形如：CwTEh1kSLBdxBhIWfFz6h1GgDSokx97CYEV0cC1O
     * ===============================================
     * bucketName 获取地址：https://portal.qiniu.com/kodo/bucket
     * ===============================================
     * domain： 域名，一般都另外单独设置有一个访问的域名
     * ===============================================
     * Region or endpoint: 七牛云会自动选择合适的
     */
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String domain;

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
}
