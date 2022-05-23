package com.xjs.bean;

import com.xjs.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @since 2022-05-23
 */
@Component
public class ConsumerComponent {

    @Reference
    private HelloService helloService;

    public String sayHello(String name) {
        return helloService.sayHello(name);
    }

}
