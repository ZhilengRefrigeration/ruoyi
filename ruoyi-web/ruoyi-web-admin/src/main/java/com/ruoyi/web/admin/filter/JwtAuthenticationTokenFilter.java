package com.ruoyi.web.admin.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.context.SecurityContextHolder;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.common.security.auth.AuthUtil;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.model.LoginUser;
import com.ruoyi.web.admin.config.CustomHttpServletRequest;
import com.ruoyi.web.admin.config.properties.CaptchaProperties;
import com.ruoyi.web.admin.config.properties.IgnoreWhiteProperties;
import com.ruoyi.web.admin.service.ValidateCodeService;

import io.jsonwebtoken.Claims;

/**
 * token过滤器 验证token有效性
 *
 * @author ruoyi
 */
@Component
@Order(-200)
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private CaptchaProperties captchaProperties;

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    private final static String[] VALIDATE_URL = new String[] {"/auth/login", "/auth/register"};

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {

        String source = request.getHeader(SecurityConstants.FROM_SOURCE);
        // 内部请求验证 不拦截
        if (StringUtils.equals(SecurityConstants.INNER, source)) {
            chain.doFilter(request, response);
            return;
        }

        String requestURI = request.getRequestURI();

        // 非登录/注册请求或验证码关闭，不处理
        if (StringUtils.equalsAnyIgnoreCase(requestURI, VALIDATE_URL) && captchaProperties.getEnabled()) {

            //,HttpServletRequst中的body内容只会读取一次,但是可能某些情境下可能会读取多次,替换request
            ContentCachingRequestWrapperNew wrappedRequest = new ContentCachingRequestWrapperNew(request);
            try {
                String rspStr = resolveBodyFromRequest(wrappedRequest);
                JSONObject obj = JSON.parseObject(rspStr);
                validateCodeService.checkCaptcha(obj.getString(CODE), obj.getString(UUID));
            } catch (Exception e) {
                // throw new ServiceException(e.getMessage());
                 resolver.resolveException(request, response, null, e);
                return;

            }


            request = wrappedRequest;
        }

        // 跳过不需要验证的路径
        if (!StringUtils.matches(requestURI, ignoreWhite.getWhites())) {

            String token = getToken(request);
            if (StringUtils.isEmpty(token)) {
                throw new ServiceException("令牌不能为空");

            }
            Claims claims = JwtUtils.parseToken(token);
            if (claims == null) {
                throw new ServiceException("令牌已过期或验证不正确");

            }
            String userkey = JwtUtils.getUserKey(claims);
            boolean islogin = redisService.hasKey(getTokenKey(userkey));
            if (!islogin) {
                throw new ServiceException("登录状态已过期");
            }
            String userid = JwtUtils.getUserId(claims);
            String username = JwtUtils.getUserName(claims);
            if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username)) {
                throw new ServiceException("令牌验证失败");

            }

            SecurityContextHolder.setUserId(userid);
            SecurityContextHolder.setUserName(username);
            SecurityContextHolder.setUserKey(userkey);

            CustomHttpServletRequest customHttpServletRequest = new CustomHttpServletRequest(request);
            // 设置用户信息到请求
            addHeader(customHttpServletRequest, SecurityConstants.USER_KEY, userkey);
            addHeader(customHttpServletRequest, SecurityConstants.DETAILS_USER_ID, userid);
            addHeader(customHttpServletRequest, SecurityConstants.DETAILS_USERNAME, username);
            //转换为自定义的request
            request = customHttpServletRequest;

            if (StringUtils.isNotEmpty(token)) {
                LoginUser loginUser = AuthUtil.getLoginUser(token);
                if (StringUtils.isNotNull(loginUser)) {
                    AuthUtil.verifyLoginUserExpire(loginUser);
                    SecurityContextHolder.set(SecurityConstants.LOGIN_USER, loginUser);
                }
            }

        }

        chain.doFilter(request, response);
    }

    /**
     * 获取缓存key
     */
    private String getTokenKey(String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    private void addHeader(CustomHttpServletRequest customHttpServletRequest, String name, Object value) {
        if (value == null) {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtils.urlEncode(valueStr);
        customHttpServletRequest.addHeader(name, valueEncode);
    }

    private String resolveBodyFromRequest(HttpServletRequest request) throws IOException {

        // 直接从HttpServletRequest的Reader流中获取RequestBody
        BufferedReader reader = request.getReader();
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            line = reader.readLine();
        }
        reader.close();
        String reqBody = builder.toString();
        return reqBody;

        // String requestBody = new String(request.getContentAsByteArray());
        // return requestBody;

    }


}
