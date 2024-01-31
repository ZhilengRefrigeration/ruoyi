package com.ruoyi.system;

import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 *
 * @author ruoyi
 */
//@EnableCustomConfig
//@EnableCustomSwagger2
//@EnableRyFeignClients
//@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class RuoYiSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuoYiSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块(常规模式)启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
