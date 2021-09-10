package com.ruoyi.file;

import com.ruoyi.file.config.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.ruoyi.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 文件服务
 *
 * @author ruoyi
 */
@EnableConfigurationProperties(value = {
        ResourcesConfig.class,
        FtpConfig.class,
        FastDfsConfig.class,
        AliyunOssConfig.class,
        CephConfig.class,
        MinioConfig.class,
        QiniuKodoConfig.class,
        ServerProperties.class
})
@EnableCustomSwagger2
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RuoYiFileApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RuoYiFileApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  文件服务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
