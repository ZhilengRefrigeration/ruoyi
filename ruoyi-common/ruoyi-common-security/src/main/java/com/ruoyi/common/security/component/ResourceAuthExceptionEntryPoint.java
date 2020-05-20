package com.ruoyi.common.security.component;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.ServletUtils;

/**
 * 自定义认证失败的异常
 * 
 * @author ruoyi
 */
@Component
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint
{
    private final Logger logger = LoggerFactory.getLogger(ResourceAuthExceptionEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException
    {
        logger.info("令牌不合法，禁止访问 {}", request.getRequestURI());

        String msg = authException.getMessage();
        ServletUtils.renderString(response, JSON.toJSONString(R.failed(HttpStatus.UNAUTHORIZED, msg)));
    }
}
