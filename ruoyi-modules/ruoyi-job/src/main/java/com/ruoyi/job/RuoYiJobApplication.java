package com.ruoyi.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * 定时任务
 * 
 * @author ruoyi
 */
@ConditionalOnProperty(name = "spring.cloud.nacos.config.group", havingValue = "DEFAULT_GROUP", matchIfMissing = true)
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients   
@SpringBootApplication
public class RuoYiJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
