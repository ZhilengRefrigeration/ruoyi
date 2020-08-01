package com.ruoyi.common.security.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义访问401返回值
 *
 * @author tanran
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final Logger logger = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException){

        logger.info("token已失效，跳转登录页面 {}", request.getRequestURI());

        String msg = authException.getMessage();
        ServletUtils.renderString(response, JSON.toJSONString(R.fail(HttpStatus.UNAUTHORIZED, msg)));
    }
}