package com.xjs.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * @author xiejs
 * @since 2022-05-26
 */

@Service
public class StreamConsumerService {

    @Bean
    public Consumer<String> myChannel() {
        return message -> System.out.println("消息："+message);
    }
}
