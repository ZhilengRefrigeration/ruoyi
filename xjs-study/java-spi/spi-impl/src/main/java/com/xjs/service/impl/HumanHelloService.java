package com.xjs.service.impl;

import com.xjs.service.HelloService;

/**
 * @author xiejs
 * @since 2022-05-23
 */
public class HumanHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "Hello 你好！";
    }
}
