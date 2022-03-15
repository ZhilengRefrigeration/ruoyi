package com.xjs.mall.product;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 商城Product启动器
 * @author xiejs
 * @since 2022-03-15
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
public class MallProductApp {
    public static void main(String[] args) {
        SpringApplication.run(MallProductApp.class, args);
    }
}
