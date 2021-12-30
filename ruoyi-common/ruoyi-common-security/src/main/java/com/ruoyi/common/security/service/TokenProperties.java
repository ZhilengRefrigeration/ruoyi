package com.ruoyi.common.security.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author xiejs
 * @desc  token相关配置
 * @create 2021-12-30
 */
@Service
@ConfigurationProperties(prefix = "jwt")
public class TokenProperties {


    private Long expireTime;

    private String loginTokenKey;

    private Long refreshTime;

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getLoginTokenKey() {
        return loginTokenKey;
    }

    public void setLoginTokenKey(String loginTokenKey) {
        this.loginTokenKey = loginTokenKey;
    }

    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
    }
}
