package com.xjs.mall.search;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 商城Search启动器
 * @author xiejs
 * @since 2022-03-25
 */
@SpringBootApplication
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
public class MallSearchApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MallSearchApp.class, args);
        //String[] beanDefinitionNames = run.getBeanDefinitionNames();
        //for (String beanDefinitionName : beanDefinitionNames) {
        //    System.out.println("beanDefinitionName:"+beanDefinitionName);
        //}
    }
}
