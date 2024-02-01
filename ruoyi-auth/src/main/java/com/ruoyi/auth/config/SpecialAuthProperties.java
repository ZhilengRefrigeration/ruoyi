package com.ruoyi.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alan Scipio
 * created on 2024/2/1
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "special.auth")
public class SpecialAuthProperties {

    private Boolean loginEnabled;

    public Boolean getLoginEnabled() {
        return loginEnabled;
    }

    public void setLoginEnabled(Boolean loginEnabled) {
        this.loginEnabled = loginEnabled;
    }
}
