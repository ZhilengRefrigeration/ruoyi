package com.lagou.netty;

import com.lagou.config.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * Netty服务器
 *
 * @author xiejs
 * @since 2022-03-09
 */
@Component
public class NettyWebSocketServer implements Runnable {

    @Autowired
    private NettyConfig nettyProperties;

    @Autowired
    private WebSocketChannelInit webSocketChannelInit;

    private EventLoopGroup boosGroup = new NioEventLoopGroup(1);

    private EventLoopGroup workerGroup = new NioEventLoopGroup();


    /**
     * 资源关闭---在容器销毁时关闭
     */
    @PreDestroy
    public void close() {
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }


    @Override
    public void run() {

        try {
            //1、创建服务端启动助手
            ServerBootstrap bootstrap = new ServerBootstrap();

            //2、设置线程组
            bootstrap.group(boosGroup, workerGroup);

            //3、设置参数
            bootstrap.channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(webSocketChannelInit);

            //4、启动
            ChannelFuture future = bootstrap.bind(nettyProperties.getPort()).sync();

            System.out.println("---Netty服务端启动成功---");

            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
