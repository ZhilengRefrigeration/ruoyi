package com.xjs;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 日志服务启动器
 * @author xiejs
 *  2021-12-31
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
public class XjsLogApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsLogApp.class, args);
    }
}
