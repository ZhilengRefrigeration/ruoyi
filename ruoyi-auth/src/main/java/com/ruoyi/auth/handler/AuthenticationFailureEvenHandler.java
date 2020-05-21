package com.ruoyi.auth.handler;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.security.handler.AbstractAuthenticationFailureEvenHandler;

/**
 * 认证失败处理
 * 
 * @author ruoyi
 */
@Component
public class AuthenticationFailureEvenHandler extends AbstractAuthenticationFailureEvenHandler
{
    private final Logger logger = LoggerFactory.getLogger(AuthenticationFailureEvenHandler.class);

    @Override
    public void handle(AuthenticationException authenticationException, Authentication authentication)
    {
        HttpServletRequest request = ServletUtils.getRequest();

        String url = request.getRequestURI();

        String username = (String) authentication.getPrincipal();

        logger.info("用户：{} 授权失败，url：{}", username, url);
    }
}
