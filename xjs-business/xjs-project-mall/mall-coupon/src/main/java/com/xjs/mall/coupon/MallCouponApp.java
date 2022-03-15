package com.xjs.mall.coupon;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 商城Coupon启动器
 * @author xiejs
 * @since 2022-03-15
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
public class MallCouponApp {
    public static void main(String[] args) {
        SpringApplication.run(MallCouponApp.class, args);
    }
}
