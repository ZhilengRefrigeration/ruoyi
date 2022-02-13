package com.xjs;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * openApi启动器
 * @author xiejs
 * @since  2021-12-25
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
//这个服务开启boot内置定时任务
@EnableScheduling
//开启springboot基于注解的缓存管理支持
@EnableCaching
public class XjsOpenApiApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsOpenApiApp.class, args);
    }
}
