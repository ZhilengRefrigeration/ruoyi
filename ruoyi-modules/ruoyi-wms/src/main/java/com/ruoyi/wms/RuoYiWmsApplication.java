package com.ruoyi.wms;

import com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WMS模块
 *
 * @author Alan Scipio
 * created on 2024/2/1
 */
@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class RuoYiWmsApplication {
    public static void main(String[] args) {
        System.setProperty("pagehelper.banner", "false"); //关闭pagehelper的banner
        SpringApplication.run(RuoYiWmsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  WMS模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
