package com.xjs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 百度平台密钥
 * @author xiejs
 * @since  2021-12-25
 */
@Data
@ConfigurationProperties(prefix = "baidu.open")
@Component
public class BaiduProperties {

    /**
     * APP ID
     */
    private String appId;

    /**
     * 密钥
     */
    private String key;

    /**
     * 盐值
     */
    private String salt;


}
