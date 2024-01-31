package com.ruoyi;

import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 多合一启动器
 *
 * @author Alan Scipio
 * created on 2024/1/31
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class SystemAllApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemAllApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块(All-in-one模式)启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
