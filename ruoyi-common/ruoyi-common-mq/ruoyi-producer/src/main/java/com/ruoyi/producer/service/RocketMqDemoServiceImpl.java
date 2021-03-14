package com.ruoyi.producer.service;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RocketMqDemoServiceImpl implements RocketMqDemoService {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendMessage() {
        rocketMQTemplate.syncSend("topicB", "222");
    }
}
