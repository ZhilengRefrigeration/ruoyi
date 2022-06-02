package com.xjs.service.impl;

import com.xjs.service.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author xiejs
 * @since 2022-06-01
 */
@DubboService
public class HelloServiceImpl implements HelloService {


    @Override
    public String sayHello(String name) {
        return "hello";
    }

    @Override
    public String sayHello(String name, int timeTowait) {
        return null;
    }
}
