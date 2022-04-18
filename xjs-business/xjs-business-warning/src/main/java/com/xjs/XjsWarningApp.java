package com.xjs;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xiejs
 * @desc  预警模块启动器
 * @create 2021-12-31
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@EnableScheduling
@EnableRetry    //启动重试机制
public class XjsWarningApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsWarningApp.class, args);
    }
}
