package com.xjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiejs
 * @since 2022-05-30
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OAuthServerApplication9898 {

    public static void main(String[] args) {
        SpringApplication.run(OAuthServerApplication9898.class, args);
    }
}
