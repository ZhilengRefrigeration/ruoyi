package com.xjs.srb.core;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 尚融宝core启动器
 * @author xiejs
 * @since  2022-02-28
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
//这个服务开启boot内置定时任务
@EnableScheduling
public class XjsSrbCoreApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsSrbCoreApp.class, args);
    }
}
