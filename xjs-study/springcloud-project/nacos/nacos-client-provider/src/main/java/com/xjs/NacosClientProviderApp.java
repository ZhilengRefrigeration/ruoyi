package com.xjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiejs
 * @since 2022-05-31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosClientProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosClientProviderApp.class, args);
    }

}
