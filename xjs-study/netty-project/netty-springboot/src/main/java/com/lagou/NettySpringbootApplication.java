package com.lagou;

import com.lagou.netty.NettyWebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettySpringbootApplication implements CommandLineRunner {

    @Autowired
    private NettyWebSocketServer webSocketServer;

    public static void main(String[] args) {
        SpringApplication.run(NettySpringbootApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //启动Netty服务端
        new Thread(webSocketServer).start();

    }
}
