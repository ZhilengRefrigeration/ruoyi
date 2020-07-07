package com.ruoyi.common.security.feign;

import com.ruoyi.common.core.constant.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.RequestInterceptor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * Feign配置注册
 *
 * @author ruoyi
 **/
@Configuration
public class OAuth2FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return (requestTemplate) -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails dateils = (OAuth2AuthenticationDetails) authentication.getDetails();
                requestTemplate.header(HttpHeaders.AUTHORIZATION,
                        String.format("%s %s", SecurityConstants.BEARER_TOKEN_TYPE, dateils.getTokenValue()));
            }
        };
    }
}
