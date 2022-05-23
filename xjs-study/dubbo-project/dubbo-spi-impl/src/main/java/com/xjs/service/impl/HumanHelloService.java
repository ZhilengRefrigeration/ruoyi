package com.xjs.service.impl;

import com.xjs.service.HelloService;
import org.apache.dubbo.common.URL;

/**
 * @author xiejs
 * @since 2022-05-23
 */
public class HumanHelloService implements HelloService {
    @Override
    public String sayHello() {
        return "傻逼你好";
    }

    @Override
    public String sayHello(URL url) {
        return "hello url";
    }
}
