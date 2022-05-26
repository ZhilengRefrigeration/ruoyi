package com.ruoyi.gateway.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 黑名单过滤器
 *
 * @author xiejs
 * @since 2022-05-26
 */
@Component
@Log4j2
public class BlackListFilter implements GlobalFilter, Ordered {

    private static final List<String> blackList = new CopyOnWriteArrayList<>();


    static {
        blackList.add("192.168.2.20");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取客户端ip，判断是否在黑名单中，在的话拒绝访问，不在就放行

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest.Builder mutate = request.mutate();

        //从request对象中获取客户端ip
        String clientIp = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        if (blackList.contains(clientIp)) {
            //拒绝访问，返回
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            log.debug("====>IP:"+clientIp+"在黑名单中，被拒绝访问!");
            String data = "Request be denied!";
            DataBuffer dataBuffer = response.bufferFactory().wrap(data.getBytes(StandardCharsets.UTF_8));

            return response.writeWith(Mono.just(dataBuffer));
        } else {
            //合法请求，放行，执行后续的过滤器
            return chain.filter(exchange.mutate().request(mutate.build()).build());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
