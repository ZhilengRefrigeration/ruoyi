package com.xjs;

import com.xjs.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiejs
 * @since 2022-06-01
 */
@SpringBootApplication
@RestController
@RequestMapping("app")
public class NacosSentinelDubboConsumerApp {

    public static void main(String[] args) {
        SpringApplication.run(NacosSentinelDubboConsumerApp.class, args);
    }


    @DubboReference
    private HelloService helloService;

    @GetMapping
    public String test() {
        return helloService.sayHello("傻逼");
    }

}
