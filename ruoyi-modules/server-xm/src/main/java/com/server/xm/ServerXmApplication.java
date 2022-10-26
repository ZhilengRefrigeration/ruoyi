package com.server.xm;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
@MapperScan("com.server.xm.mapper")
public class ServerXmApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerXmApplication.class, args);
        System.out.println("Server-XM模块启动成功");
    }
}
