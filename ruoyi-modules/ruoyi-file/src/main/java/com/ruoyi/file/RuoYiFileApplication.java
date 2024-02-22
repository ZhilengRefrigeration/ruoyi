package com.ruoyi.file;

import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 文件服务
 *
 * @author ruoyi
 */
@ConditionalOnProperty(name = "spring.cloud.nacos.config.group", havingValue = "DEFAULT_GROUP", matchIfMissing = true)
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RuoYiFileApplication {
    public static void main(String[] args) {
        System.setProperty("pagehelper.banner", "false"); //关闭pagehelper的banner
        SpringApplication.run(RuoYiFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
