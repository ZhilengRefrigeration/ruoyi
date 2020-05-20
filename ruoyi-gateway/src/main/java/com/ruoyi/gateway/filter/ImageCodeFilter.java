package com.ruoyi.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.ValidateCodeException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.web.WebUtils;
import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.gateway.config.properties.IgnoreClientProperties;
import reactor.core.publisher.Mono;

/**
 * 验证码处理
 * 
 * @author ruoyi
 */
@SuppressWarnings("rawtypes")
@Component
public class ImageCodeFilter extends AbstractGatewayFilterFactory
{
    private static final Logger log = LoggerFactory.getLogger(ImageCodeFilter.class);

    @Autowired
    private IgnoreClientProperties ignoreClient;

    private final static String AUTH_URL = "/oauth/token";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public GatewayFilter apply(Object config)
    {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StringUtils.containsIgnoreCase(request.getURI().getPath(), AUTH_URL))
            {
                return chain.filter(exchange);
            }
            try
            {
                String[] clientInfos = WebUtils.getClientId(request);
                if (ignoreClient.getClients().contains(clientInfos[0]))
                {
                    return chain.filter(exchange);
                }
                checkCode(request.getQueryParams());
            }
            catch (Exception e)
            {
                ServerHttpResponse response = exchange.getResponse();
                try
                {
                    return exchange.getResponse().writeWith(Mono.just(response.bufferFactory()
                            .wrap(objectMapper.writeValueAsBytes(
                                R.failed(e.getMessage())))));
                }
                catch (JsonProcessingException e1)
                {
                    log.error("对象输出异常", e1);
                }
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 校验验证码
     */
    private void checkCode(MultiValueMap<String, String> getQueryParams) throws ValidateCodeException
    {
        String code = getQueryParams.getFirst("code");
        String uuid = getQueryParams.getFirst("uuid");
        if (StringUtils.isEmpty(code))
        {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (StringUtils.isEmpty(uuid))
        {
            throw new ValidateCodeException("验证码已失效");
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisService.getCacheObject(verifyKey);
        redisService.deleteObject(verifyKey);

        if (!code.equalsIgnoreCase(captcha))
        {
            throw new ValidateCodeException("验证码错误");
        }
    }
}
