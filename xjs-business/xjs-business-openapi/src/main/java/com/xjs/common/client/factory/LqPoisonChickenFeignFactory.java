package com.xjs.common.client.factory;

import com.xjs.common.client.api.lq.LqPoisonChickenFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 零七平台 毒鸡汤 feign降级
 * @author xiejs
 * @since 2022-02-14
 */
@Component
@Log4j2
public class LqPoisonChickenFeignFactory implements FallbackFactory<LqPoisonChickenFeignClient> {

    @Override
    public LqPoisonChickenFeignClient create(Throwable cause) {
        log.error("api模块零七-毒鸡汤服务调用失败:{},执行降级处理", cause.getMessage());
        return () -> {
            return "";
        };
    }
}
