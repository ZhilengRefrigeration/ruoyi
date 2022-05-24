package com.xjs;

import com.xjs.bean.ConsumerComponent;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * @author xiejs
 * @since 2022-05-23
 */
public class AnnotationConsumerApplication {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();

        //获取消费者组件
        ConsumerComponent service = context.getBean(ConsumerComponent.class);

        while (true) {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(5);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String msg = service.sayHello("hello");
                            System.out.println(msg);
                        }
                    }).start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


        //String msg = service.sayHello("hello");
        //System.out.println(msg);

    }

    @Configuration
    @PropertySource("classpath:/dubbo-consumer.properties")
    @ComponentScan(basePackages = "com.xjs.bean")
    @EnableDubbo
    static class ConsumerConfiguration{

    }


}
