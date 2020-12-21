package com.ruoyi.consumer;

import com.ruoyi.common.security.annotation.EnableCustomConfig;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableCustomConfig
@EnableRyFeignClients
@SpringCloudApplication
public class ConsumerApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(ConsumerApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ConsumerApplication-消费者服务启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
