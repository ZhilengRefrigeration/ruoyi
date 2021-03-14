package com.ruoyi.producer;


import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableCustomConfig
@EnableRyFeignClients
@SpringCloudApplication
public class ProducerApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(ProducerApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ProducerApplication-生产者服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
