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
public class StreamProducerApp8600 {

    public static void main(String[] args) {
        SpringApplication.run(StreamProducerApp8600.class, args);
    }
}
