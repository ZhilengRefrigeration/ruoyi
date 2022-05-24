package com.xjs;

import com.xjs.service.HelloService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author xiejs
 * @since 2022-05-24
 */
public class XmlConsumerApplication {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");

        HelloService helloService = context.getBean(HelloService.class);

        while (true) {
            System.in.read();

            String word = helloService.sayHello("word", 500
            );

            //利用Future 模式来获取
            Future<Object> future = RpcContext.getContext().getFuture();
            System.out.println("future result:"+future.get());

            System.out.println(word);

        }
    }
}
