package com.xjs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * roll平台密钥
 * @author xiejs
 * @since 2022-01-07
 */
@Component
@ConfigurationProperties("roll.open")
@Data
public class RollProperties {

    /**
     * 应用id
     */
    private String app_id;


    /**
     * 应用密钥
     */
    private String app_secret;
}


