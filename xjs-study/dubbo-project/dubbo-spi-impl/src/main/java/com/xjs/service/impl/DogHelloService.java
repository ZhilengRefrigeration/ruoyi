package com.xjs.service.impl;

import com.xjs.service.HelloService;
import org.apache.dubbo.common.URL;

/**
 * @author xiejs
 * @since 2022-05-23
 */
public class DogHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "你在狗叫什么！";
    }


    @Override
    public String sayHello(URL url) {
        return "wa wa url";
    }
}
