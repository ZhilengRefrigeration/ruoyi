package com.xjs;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 爬虫服务启动器
 * @author xiejs
 * @since 2022-01-22
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@EnableScheduling
public class XjsWebmagicApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsWebmagicApp.class, args);
    }
}
