package com.xjs.common.client.factory;

import com.xjs.common.client.api.lq.LqDogDiaryFeignClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @since 2022-02-14
 */
@Component
@Log4j2
public class LqDogDiaryFeignFactory implements FallbackFactory<LqDogDiaryFeignClient> {
    @Override
    public LqDogDiaryFeignClient create(Throwable cause) {
        log.error("api模块零七舔狗日记服务调用失败:{},执行降级处理", cause.getMessage());
        return () -> {
            return "";
        };
    }
}
