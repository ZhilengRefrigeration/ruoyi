package com.xjs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 高德秘钥
 * @author xiejs
 * @since 2022-01-16
 */
@Data
@ConfigurationProperties(prefix = "gaode.open")
@Component
public class GaodeProperties {

    private String key;
}
