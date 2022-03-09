package com.xjs.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 聊天室服务端
 * @author xiejs
 * @since 2022-03-09
 */
public class NettyHttpServer {

    private  final static Integer port = 8089;

    public static void main(String[] args) {
        run();
    }


    public static void run()  {
        //1、创建bossGroup线程组，处理网络事件-连接事件
        NioEventLoopGroup boosGroup = null;

        //2、创建workerGroup线程组，处理网络事件-读写事件 2*处理器线程数
        NioEventLoopGroup workerGroup = null;


        try {
            boosGroup = new NioEventLoopGroup(1);

            workerGroup = new NioEventLoopGroup();

            //3、创建服务端启动助手
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //4、设置bossGroup线程组和workerGroup线程组
            serverBootstrap.group(workerGroup, workerGroup)
                    //5、设置服务端通道实现为NIO
                    .channel(NioServerSocketChannel.class)
                    //6、参数设置
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //6、参数设置
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    //7、创建一个通道初始化对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //添加解码器
                            ch.pipeline().addLast(new HttpServerCodec());

                            //8、向pipeline中添加自定义业务处理handler
                            ch.pipeline().addLast(new NettyHttpServerHandler());

                        }
                    });

            //9、启动服务端并绑定端口，同时将异步改为同步
            ChannelFuture future = serverBootstrap.bind(port).sync();

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("端口绑定成功");
                    } else {
                        System.out.println("端口绑定失败");
                    }
                }
            });

            System.out.println("HTTP服务端启动成功");

            //10、关闭通道（并不是真正意义上的关闭，而是监听关闭的状态）和关闭连接池
            future.channel().closeFuture().sync();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

}
