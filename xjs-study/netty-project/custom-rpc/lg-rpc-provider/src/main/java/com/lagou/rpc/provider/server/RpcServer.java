package com.lagou.rpc.provider.server;

import com.lagou.rpc.provider.handler.RpcServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * netty启动类
 *
 * @author xiejs
 * @since 2022-03-09
 */
@Component
public class RpcServer implements DisposableBean {

    @Autowired
    private RpcServerHandler serverHandler;

    private NioEventLoopGroup bossGroup;

    private NioEventLoopGroup workerGroup;


    public void startServer(String ip, Integer port) {

        try {
            //1、创建线程组
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();

            //2、创建服务端启动助手
            ServerBootstrap bootstrap = new ServerBootstrap();

            //3、设置参数
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            //添加String的编解码器
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());

                            //添加业务处理类
                            pipeline.addLast(serverHandler);

                        }
                    });

            //4、绑定端口
            ChannelFuture future = bootstrap.bind(ip, port).sync();

            System.out.println("--------服务端启动成功----------");

            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 在容器销毁执行
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }

        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }

}
