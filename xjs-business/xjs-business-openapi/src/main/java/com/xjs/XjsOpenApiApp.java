package com.xjs;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xiejs
 * @desc
 * @create 2021-12-25
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
//这个服务开启boot内置定时任务
@EnableScheduling
public class XjsOpenApiApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsOpenApiApp.class, args);
    }
}
