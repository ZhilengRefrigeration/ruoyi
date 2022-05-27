package com.xjs.service.impl;

import com.xjs.service.IMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 消息生产者实现
 *
 * @author xiejs
 * @since 2022-05-26
 */
@Service
public class MessageProducerImpl implements IMessageProducer {

    @Autowired
    private StreamBridge streamBridge;


    @Override
    public void sendMessage() {
        //向mq中发送消息(并不是直接操作mq，而是操作Spring Cloud Stream)
        String message = UUID.randomUUID().toString();
        streamBridge.send("myChannel-out-0", MessageBuilder.withPayload(message).build());

    }
}
