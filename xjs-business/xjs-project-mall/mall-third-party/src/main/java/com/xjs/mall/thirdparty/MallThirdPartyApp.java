package com.xjs.mall.thirdparty;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import io.seata.spring.boot.autoconfigure.SeataAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 商城Product启动器
 * @author xiejs
 * @since 2022-03-15
 */
@SpringBootApplication(exclude ={DataSourceAutoConfiguration.class,
        SeataAutoConfiguration.class,
        MybatisPlusAutoConfiguration.class,
        DynamicDataSourceAutoConfiguration.class} )
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
public class MallThirdPartyApp {
    public static void main(String[] args) {
        SpringApplication.run(MallThirdPartyApp.class, args);
    }
}
