package com.xjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Eureka客户端启动器
 * @author xiejs
 * @since 2022-05-24
 */
@SpringBootApplication
//@EnableEurekaClient     //开启Eureka client
@EnableDiscoveryClient  //开启注册中心客户端
//从springCloud的Edgware版本开始，不加[@EnableDiscoveryClient]注解也行，建议加上
@EnableFeignClients
public class EurekaClientConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConsumerApp.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
