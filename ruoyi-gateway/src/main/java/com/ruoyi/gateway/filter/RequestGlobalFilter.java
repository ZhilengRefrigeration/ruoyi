package com.ruoyi.gateway.filter;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.addOriginalRequestUrl;
import java.util.Arrays;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局拦截器
 *
 * @author ruoyi
 */
@Component
public class RequestGlobalFilter implements GlobalFilter, Ordered
{
    private static final String[] whiteList = {"/auth/oauth/token"};
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        String url = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if (Arrays.asList(whiteList).contains(url))
        {
            return chain.filter(exchange);
        }
        
        // 清洗请求头中from 参数
        ServerHttpRequest request = exchange.getRequest().mutate()
            .headers(httpHeaders -> httpHeaders.remove("from"))
            .build();

        addOriginalRequestUrl(exchange, request.getURI());
        String rawPath = request.getURI().getRawPath();
        ServerHttpRequest newRequest = request.mutate()
            .path(rawPath)
            .build();
        exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, newRequest.getURI());

        return chain.filter(exchange.mutate()
            .request(newRequest.mutate()
                .build()).build());
    }

    @Override
    public int getOrder()
    {
        return -1000;
    }
}
