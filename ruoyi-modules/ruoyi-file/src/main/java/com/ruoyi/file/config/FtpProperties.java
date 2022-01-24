package com.ruoyi.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ftp配置信息
 * @author xiejs
 * @since 2022-01-24
 */
@Component
@ConfigurationProperties(prefix = "ftp")
@Data
public class FtpProperties {

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String password;


    /**
     * ftp服务ip
     */
    private String serverIp;

    /**
     * ftp文件路径
     */
    private String path;

    /**
     * 项目地址
     */
    private String domain;

    /**
     * 前缀
     */
    private String prefix;
}
