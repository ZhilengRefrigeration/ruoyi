package com.ruoyi.web.admin.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ruoyi.common.core.exception.CaptchaException;
import com.ruoyi.common.core.web.domain.AjaxResult;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalAdminWebExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalAdminWebExceptionHandler.class);


    /**
     * 验证码异常
     */
    @ExceptionHandler(CaptchaException.class)
    public AjaxResult handleCaptchaException(CaptchaException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}'", requestURI, e.getMessage());
        return AjaxResult.error(e.getMessage());
    }

}
