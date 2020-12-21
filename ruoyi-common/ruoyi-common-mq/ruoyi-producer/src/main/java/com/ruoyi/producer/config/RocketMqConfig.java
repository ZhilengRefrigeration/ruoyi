package com.ruoyi.producer.config;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.remoting.RPCHook;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class RocketMqConfig {
    @Autowired
   private RocketMQTemplate rocketMQTemplate;

    @Bean
    public RocketMQTemplate buildRocketMqTemplate(){
        DefaultMQProducer defaultMQProducer= new DefaultMQProducer(getAclRPCHook());
        rocketMQTemplate.setProducer(defaultMQProducer);
        return rocketMQTemplate;
    }

     RPCHook getAclRPCHook() {
        return new AclClientRPCHook(new SessionCredentials("rocketmq","12345678"));
    }
}
