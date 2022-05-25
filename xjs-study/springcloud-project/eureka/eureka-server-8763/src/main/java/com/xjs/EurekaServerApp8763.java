package com.xjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka服务端启动器
 * @author xiejs
 * @since 2022-05-24
 */
@SpringBootApplication
//声明当前项目为为Eureka服务
@EnableEurekaServer
public class EurekaServerApp8763 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApp8763.class, args);
        System.out.println("localhost:8763");
    }
}
