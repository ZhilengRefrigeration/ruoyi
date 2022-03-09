package com.xjs.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * 聊天室的客户端
 *
 * @author xiejs
 * @since 2022-03-09
 */
public class NettyChatClient {

    private static final String ip = "127.0.0.1";

    private final static Integer port = 9997;

    public static void run() {
        //1、创建线程组
        NioEventLoopGroup group = null;
        try {
            group = new NioEventLoopGroup();

            //2、创建客户端启动助手
            Bootstrap bootstrap = new Bootstrap();

            //3、设置线程组
            bootstrap.group(group)
                    //4、设置通道实现为NIO
                    .channel(NioSocketChannel.class)
                    //5、创建一个通道初始化对象
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //添加解码器
                            ch.pipeline().addLast( new StringDecoder());
                            ch.pipeline().addLast( new StringEncoder());

                            //6、向pipeline中添加自定义业务处理handler
                            ch.pipeline().addLast(new NettyChatClientHandler());
                        }
                    });

            //7、启动客户端，等待连接服务端，同时将异步改为同步
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            Channel channel = future.channel();
            System.out.println("------------"+channel.localAddress().toString().substring(1)+"------------");

            Scanner scanner = new Scanner(System.in);
            while ((scanner.hasNextLine())) {
                String msg = scanner.nextLine();

                //向服务端发送消息
                channel.writeAndFlush(msg);
            }

            //8、关闭通道和关闭连接池
            channel.closeFuture().sync();
        } catch (Exception e) {

        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        run();
    }

}
