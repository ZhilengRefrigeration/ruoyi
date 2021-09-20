package cn.lynntech.magicapi.filter;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.model.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * token过滤器 验证token有效性
 *
 * @author wanfeng
 */
@Component
public class AuthFilter extends OncePerRequestFilter
{
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        log.info("ACCESS URL => {}", request.getRequestURI());
        Object trust = request.getAttribute("TrustHostFiltered");
        if (trust != null && (Boolean)trust) {
            chain.doFilter(request, response);
            return;
        }
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNull(loginUser) || StringUtils.isNull(loginUser.getToken()))
        {
            if(request.getRequestURI().startsWith("/api")){
                //异常处理，无权限
                //throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentLength(-1);
                response.setContentType(MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8");
                OutputStream stream = response.getOutputStream();
                stream.write("用户未登录".getBytes(StandardCharsets.UTF_8));
                stream.flush();
                stream.close();
            }
        }
        chain.doFilter(request, response);
    }
}