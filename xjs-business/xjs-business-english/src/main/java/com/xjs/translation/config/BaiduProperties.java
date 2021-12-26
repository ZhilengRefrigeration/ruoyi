package com.xjs.translation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
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


}
