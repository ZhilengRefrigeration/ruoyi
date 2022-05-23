package com.xjs;

import com.xjs.service.HelloService;

import java.util.ServiceLoader;

/**
 * @author xiejs
 * @since 2022-05-23
 */
public class JavaSpiMain {

    public static void main(String[] args) {
        final ServiceLoader<HelloService> helloServices = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : helloServices) {
            System.out.println(helloService.getClass().getName()+":"+helloService.sayHello());
        }
    }

}
