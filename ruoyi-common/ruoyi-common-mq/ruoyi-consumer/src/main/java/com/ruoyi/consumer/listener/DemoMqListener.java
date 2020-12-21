package com.ruoyi.consumer.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "groupB", topic = "topicB",
        accessKey = "rocketmq2",secretKey = "12345678")
public class DemoMqListener implements RocketMQListener {

    @Override
    public void onMessage(Object o) {
        System.out.println(o);
    }

}
