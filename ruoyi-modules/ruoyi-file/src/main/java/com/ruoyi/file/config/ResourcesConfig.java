package com.ruoyi.file.config;

import java.io.File;

import com.ruoyi.file.service.LocalSysFileServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 本地文件配置
 * 通用映射配置
 * local file config = ResourcesConfig
 *
 * @see LocalSysFileServiceImpl
 *
 * @author ruoyi
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = ResourcesConfig.PREFIX)
public class ResourcesConfig implements WebMvcConfigurer
{
    public static final String PREFIX = "file";
    /**
     * 文件对外访问地址，一般就是应用 或者 nginx对外地址
     * eg: http://127.0.0.1:9300
     * ///@Value("${file.domain}")
     */
    private String domain;

    /**
     * 资源映射路径 前缀
     * eg: eg: /statics
     * 一般访问：${domain}/${prefix}/key
     * ///@Value("${file.prefix}")
     *
     * 上传之后的路径，形如："url": "http://localhost:9300/statics/2021/07/16/25292b96-a107-4cf8-baca-e1cb693fd078.jpg"
     */
    private String prefix;

    /**
     * 上传文件存储在本地的根路径
     * eg: D:/ruoyi/uploadPath
     * ///@Value("${file.path}")
     */
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        registry.addResourceHandler(prefix + "/**")
                .addResourceLocations("file:" + path + File.separator);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
