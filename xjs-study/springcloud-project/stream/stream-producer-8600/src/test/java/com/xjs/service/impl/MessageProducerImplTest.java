package com.xjs.service.impl;

import com.xjs.StreamProducerApp8600;
import com.xjs.service.IMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xiejs
 * @since 2022-05-26
 */
@SpringBootTest(classes = StreamProducerApp8600.class)
class MessageProducerImplTest {

    @Autowired
    private IMessageProducer iMessageProducer;

    @org.junit.jupiter.api.Test
    void sendMessage() {
        iMessageProducer.sendMessage();
    }
}
