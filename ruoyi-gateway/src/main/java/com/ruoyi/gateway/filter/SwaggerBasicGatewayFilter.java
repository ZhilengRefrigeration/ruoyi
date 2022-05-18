package com.ruoyi.gateway.filter;

import com.ruoyi.gateway.config.properties.SwaggerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * swagger 开启basic 认证
 *
 * @author edgar
 * @date 2022/5/8
 */
@Component
@ConditionalOnProperty(name = "swagger.basic.enable", matchIfMissing = true)
public class SwaggerBasicGatewayFilter implements GlobalFilter, Ordered {

	private static final Logger log = LoggerFactory.getLogger(SwaggerBasicGatewayFilter.class);
	private static final String API_URI = "/v2/api-docs";

	private static final String BASIC_PREFIX = "Basic ";

	@Autowired
	private SwaggerProperties swaggerProperties;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();

		if (!request.getURI().getPath().contains(API_URI)) {
			return chain.filter(exchange);
		}

		if (hasAuth(exchange)) {
			return chain.filter(exchange);
		}
		else {
			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			response.getHeaders().add(HttpHeaders.WWW_AUTHENTICATE, "Basic Realm=\"ry\"");
			return response.setComplete();
		}
	}

	/**
	 * 简单的basic认证
	 * @param exchange 上下文
	 * @return 是否有权限
	 */
	private boolean hasAuth(ServerWebExchange exchange) {
		ServerHttpRequest request = exchange.getRequest();
		String auth = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (!StringUtils.hasText(auth) || !auth.startsWith(BASIC_PREFIX)) {
			return Boolean.FALSE;
		}
		log.info("Basic认证信息为：{}", auth);
		String username = swaggerProperties.getBasic().getUsername();
		String password = swaggerProperties.getBasic().getPassword();

		String encodeToString = Base64Utils
				.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));

		return auth.equals(BASIC_PREFIX + encodeToString);
	}

	@Override
	public int getOrder() {
		return -300;
	}
}
