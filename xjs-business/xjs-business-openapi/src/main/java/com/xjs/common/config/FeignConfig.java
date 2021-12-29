package com.xjs.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@Configuration
public class FeignConfig{
    /**
     * 配置日志输出
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
