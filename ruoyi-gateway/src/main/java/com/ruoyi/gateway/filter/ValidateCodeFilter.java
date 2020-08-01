package com.ruoyi.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.gateway.service.ValidateCodeService;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 验证码过滤器
 * 
 * @author ruoyi
 */
@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object>
{
    private final static String AUTH_URL = "/oauth/token";

    @Autowired
    private ValidateCodeService validateCodeService;

    private static final String BASIC_ = "Basic ";

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    @Override
    public GatewayFilter apply(Object config)
    {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            MultiValueMap<String, String> queryParams = request.getQueryParams();
            //            todo 下述常量写入SecurityConstants.java
            List<String> grant_typeS = queryParams.get("grant_type");

            // 非登录请求，不处理 刷新access_token，不处理
            boolean isLogin = StringUtils.containsIgnoreCase(request.getURI().getPath(),AUTH_URL);
            if (!isLogin || (isLogin && !ObjectUtils.isEmpty(queryParams) && !ObjectUtils.isEmpty(grant_typeS) && grant_typeS.contains("refresh_token")))
            {
                return chain.filter(exchange);
            }

            // 消息头存在内容，且不存在验证码参数，不处理
            String header = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotEmpty(header) && StringUtils.startsWith(header, BASIC_)
                    && !request.getQueryParams().containsKey(CODE) && !request.getQueryParams().containsKey(UUID))
            {
                return chain.filter(exchange);
            }
            try
            {
                validateCodeService.checkCapcha(request.getQueryParams().getFirst(CODE),
                        request.getQueryParams().getFirst(UUID));
            }
            catch (Exception e)
            {
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return exchange.getResponse().writeWith(
                        Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(AjaxResult.error(e.getMessage())))));
            }
            return chain.filter(exchange);
        };
    }
}
