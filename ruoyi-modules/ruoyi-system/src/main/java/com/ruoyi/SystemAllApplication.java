package com.ruoyi;

import org.springframework.boot.SpringApplication;

/**
 * all-in-one架构模式下的启动入口类
 *
 * @author Alan Scipio
 * created on 2024/1/31
 */
//@EnableCustomConfig
//@EnableCustomSwagger2
//@EnableRyFeignClients
//@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class SystemAllApplication {
    public static void main(String[] args) {
        System.setProperty("pagehelper.banner", "false"); //关闭pagehelper的banner
        SpringApplication.run(SystemAllApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块(All-in-one模式)启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
