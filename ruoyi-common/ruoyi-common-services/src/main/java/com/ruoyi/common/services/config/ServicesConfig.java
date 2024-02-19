package com.ruoyi.common.services.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 本模块全局配置
 *
 * @author Alan Scipio
 * created on 2024/2/19
 */
@MapperScan("com.ruoyi.common.services.mapper")
@ComponentScan(value = "com.ruoyi.common.services", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.ruoyi.common.services.mapper.*"),
})
@Configuration
public class ServicesConfig {

    public ServicesConfig() {
        System.out.println("Common Module ServicesConfig init");
    }

}
