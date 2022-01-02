package com.xjs;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.security.config.ApplicationConfig;
import com.ruoyi.common.security.feign.FeignAutoConfiguration;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author xiejs
 * @desc  业务监控服务启动器
 * @create 2022-01-02
 */
//排除两个关于数据源的自动配置类
@SpringBootApplication(exclude = {DynamicDataSourceAutoConfiguration.class, DataSourceAutoConfiguration.class})
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 开启线程异步执行
@EnableAsync
// 自动加载类
@Import({ ApplicationConfig.class, FeignAutoConfiguration.class })
//自定义bean扫描，添加xjs路径下的bean
@ComponentScan(basePackages = {"com.ruoyi","com.xjs"})
@EnableCustomSwagger2
@EnableRyFeignClients
public class XjsMonitorApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsMonitorApp.class, args);
    }
}
