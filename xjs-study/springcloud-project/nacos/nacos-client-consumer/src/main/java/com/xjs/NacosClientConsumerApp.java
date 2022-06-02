package com.xjs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejs
 * @since 2022-05-31
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("app")
@RefreshScope
public class NacosClientConsumerApp {

    @Value("${xjs}")
    private Long num;

    @Value("${yyy}")
    private Long yyy;

    public static void main(String[] args) {
        SpringApplication.run(NacosClientConsumerApp.class, args);
    }

    @GetMapping
    public String test() {
        return num.toString() + yyy.toString();
    }


}
