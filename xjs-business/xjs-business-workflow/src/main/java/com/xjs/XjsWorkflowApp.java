package com.xjs;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 工作流服务启动器
 * @author xiejs
 * @since 2022-04-16
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
//排除security自动配置：解决activiti自带springSecurity依赖自动配置导致跨域问题
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class XjsWorkflowApp {
    public static void main(String[] args) {
        SpringApplication.run(XjsWorkflowApp.class, args);
    }
}
