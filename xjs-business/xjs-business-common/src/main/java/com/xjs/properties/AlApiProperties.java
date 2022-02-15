package com.xjs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ALAPI平台token参数
 * @author xiejs
 * @since 2022-02-15
 */
@Data
@ConfigurationProperties(prefix = "alapi.open")
@Component
public class AlApiProperties {

    private String token;

}
