package com.xjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiejs
 * @since 2022-05-26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamConsumerApp8601 {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerApp8601.class, args);
    }
}
