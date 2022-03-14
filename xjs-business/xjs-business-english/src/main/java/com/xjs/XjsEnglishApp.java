package com.xjs;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 英语模块启动器
 * @author xiejs
 * @since  2021-12-29
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
public class XjsEnglishApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsEnglishApp.class, args);
    }
}
