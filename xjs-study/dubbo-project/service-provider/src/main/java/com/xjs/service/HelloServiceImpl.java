package com.xjs.service;

import org.apache.dubbo.config.annotation.Service;

import java.util.concurrent.TimeUnit;

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

    @Override
    public String sayHello(String name, int timeTowait) {
        try {
            TimeUnit.SECONDS.sleep(1);

            Thread.sleep(timeTowait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Hello:"+name;
    }


}
