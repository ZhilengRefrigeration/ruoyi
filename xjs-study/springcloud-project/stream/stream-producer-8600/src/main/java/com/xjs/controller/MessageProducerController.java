package com.xjs.controller;

import com.xjs.service.IMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejs
 * @since 2022-05-26
 */
@RestController
@RequestMapping("message")
public class MessageProducerController {
    @Autowired
    private IMessageProducer iMessageProducer;


    @GetMapping
    public String message() {
        iMessageProducer.sendMessage();

        return "xaxa";
    }
}
