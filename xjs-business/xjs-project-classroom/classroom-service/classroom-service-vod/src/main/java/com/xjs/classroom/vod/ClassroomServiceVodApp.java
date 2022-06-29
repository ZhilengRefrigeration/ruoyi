package com.xjs.classroom.vod;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 * @author xiejs
 * @since 2022-06-29
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
public class ClassroomServiceVodApp {
    public static void main(String[] args) {
        SpringApplication.run(ClassroomServiceVodApp.class, args);
    }
}
