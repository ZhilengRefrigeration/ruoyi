package com.xjs.service;

import org.apache.dubbo.config.annotation.Service;

/**
 * @author xiejs
 * @since 2022-05-23
 */
@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        return "Hello:"+name;
    }
}
