package com.ruoyi.common.security.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置注册
 *
 * @author ruoyi
 **/
@Configuration
public class FeignConfig
{
    @Bean
    public RequestInterceptor requestInterceptor()
    {
        return new OAuth2FeignRequestInterceptor();
    }
}
