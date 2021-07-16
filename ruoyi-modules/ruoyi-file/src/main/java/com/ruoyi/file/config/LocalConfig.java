package com.ruoyi.file.config;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 本地文件配置
 * 通用映射配置
 * 
 * @author ruoyi
 */
@RefreshScope
@Configuration
public class LocalConfig implements WebMvcConfigurer
{
    /**
     * 资源映射路径 前缀
     * eg: eg: /statics
     */
    @Value("${file.prefix}")
    private String localFilePrefix;

    /**
     * 域名或本机访问地址
     * eg: http://127.0.0.1:9300
     */
    @Value("${file.domain}")
    private String domain;

    /**
     * 上传文件存储在本地的根路径
     * eg: D:/ruoyi/uploadPath
     */
    @Value("${file.path}")
    private String localFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(localFilePrefix + "/**")
                .addResourceLocations("file:" + localFilePath + File.separator);
    }

    public String getLocalFilePrefix() {
        return localFilePrefix;
    }

    public void setLocalFilePrefix(String localFilePrefix) {
        this.localFilePrefix = localFilePrefix;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }
}