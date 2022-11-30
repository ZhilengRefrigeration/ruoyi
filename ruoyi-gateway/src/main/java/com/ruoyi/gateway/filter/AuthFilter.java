package com.ruoyi.gateway.filter;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.core.constant.CacheConstants;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.gateway.config.properties.IgnoreWhiteProperties;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 网关鉴权
 *
 * @author ruoyi
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered
{
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Resource
    private RedisService redisService;

    @Value("${security.gateway.enabled:false}")
    private boolean gatewayAuth;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites()))
        {
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StringUtils.isEmpty(token))
        {
            return unauthorizedResponse(exchange, "令牌不能为空");
        }
        Claims claims = JwtUtils.parseToken(token);
        if (claims == null)
        {
            return unauthorizedResponse(exchange, "令牌已过期或验证不正确！");
        }
        String userkey = JwtUtils.getUserKey(claims);
        boolean islogin = redisService.hasKey(getTokenKey(userkey));
        if (!islogin)
        {
            return unauthorizedResponse(exchange, "登录状态已过期");
        }
        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username))
        {
            return unauthorizedResponse(exchange, "令牌验证失败");
        }
        // 设置用户信息到请求
        addHeader(mutate, SecurityConstants.USER_KEY, userkey);
        addHeader(mutate, SecurityConstants.DETAILS_USER_ID, userid);
        addHeader(mutate, SecurityConstants.DETAILS_USERNAME, username);
        // 内部请求来源参数清除
        removeHeader(mutate, SecurityConstants.FROM_SOURCE);
        // 通过网关鉴权
        if (gatewayAuth)
        {
            // admin不需要鉴权
            if (isAdmin(userid))
            {
                return chain.filter(exchange.mutate().request(mutate.build()).build());
            }
            // 网关验证权限
            String api = url + "_" + request.getMethod().name();
            if (!hasPermission(api, userkey))
            {
                log.warn("无权访问：{}", api);
                return ServletUtils.webFluxResponseWriter(exchange.getResponse(), "无权访问", HttpStatus.FORBIDDEN);
            }
        }
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private boolean isAdmin(String userid)
    {
        return "1".equals(userid);
    }

    private boolean hasPermission(String api, String token)
    {
        // 使用JSONObject接收，避免导入依赖
        JSONObject loginUser = redisService.getCacheObject(CacheConstants.LOGIN_TOKEN_KEY + token);
        // 获取登录用户的资源列表
        Set<String> permissions = (Set<String>) loginUser.get("permissions");
        // 获取登录用的角色列表
        Set<String> roles = (Set<String>) loginUser.get("roles");
        // 获取系统所有控制器路径与权限对应的map
        Map<String, String> pathPermsMap = redisService.getCacheMap(SecurityConstants.PATH_PERMISSION_MAP);

        Set<String> matchedPerms = pathPermsMap.entrySet().stream()
                .filter(entry -> match(entry.getKey(), api))
                .map(entry -> entry.getValue())
                .collect(Collectors.toSet());
        if (!matchedPerms.isEmpty())
        {
            // 所有角色权限
            Set<String> rolePerms = matchedPerms.stream().filter(item -> item.startsWith("ROLE_")).collect(Collectors.toSet());
            // 所有资源权限
            matchedPerms.removeAll(rolePerms);

            if (!rolePerms.isEmpty())
            {
                if (rolePerms.contains(SecurityConstants.ROLE_ANON))
                {
                    log.debug("允许访问公共权限:{}，{}", api, rolePerms);
                    return true;
                }
                rolePerms = rolePerms.stream().map(item -> item.substring(SecurityConstants.ROLE_PREFIX.length())).collect(Collectors.toSet());
                // 求交集
                rolePerms.retainAll(roles);
                if (!rolePerms.isEmpty())
                {
                    log.debug("允许访问角色权限:{}， {}", api, rolePerms);
                    return true;
                }
            }
            // 求交集
            matchedPerms.retainAll(permissions);
            if (!matchedPerms.isEmpty())
            {
                log.debug("允许访问资源权限:{}，{}", api, matchedPerms);
                return true;
            }
        }
        log.info("没有找到匹配的权限:{}, {}", api, matchedPerms);
        return false;
    }

    private boolean match(String pattern, String api)
    {
        return antPathMatcher.match(pattern, api);
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value)
    {
        if (value == null)
        {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = ServletUtils.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name)
    {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg)
    {
        log.error("[鉴权异常处理]请求路径:{}, {}", exchange.getRequest().getPath(), msg);
        return ServletUtils.webFluxResponseWriter(exchange.getResponse(), msg, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 获取缓存key
     */
    private String getTokenKey(String token)
    {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request)
    {
        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    @Override
    public int getOrder()
    {
        return -200;
    }
}