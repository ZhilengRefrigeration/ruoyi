package com.lagou.rpc.consumer.client;

import com.lagou.rpc.consumer.handler.RpcClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 客户端
 * <ol>
 *     <li>连接Netty服务端</li>
 *     <li>提供个调用者主动关闭资源的方法</li>
 *     <li>提供消息发送的方法</li>
 * </ol>
 *
 * @author xiejs
 * @since 2022-03-10
 */
@Data
@NoArgsConstructor
public class RpcClient {

    private String ip;

    private Integer port;

    private NioEventLoopGroup group;

    private Channel channel;

    private RpcClientHandler clientHandler = new RpcClientHandler();

    private ExecutorService executor = Executors.newCachedThreadPool();


    public RpcClient(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
        this.initClient();
    }

    /**
     * 初始化方法-连接Netty服务端
     */
    public void initClient() {


        try {
            //1、创建线程组
            group = new NioEventLoopGroup();

            //2、创建启动助手
            Bootstrap bootstrap = new Bootstrap();

            //3、设置参数
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            //String类型编解码器
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new StringDecoder());

                            //添加客户端处理类
                            pipeline.addLast(clientHandler);
                        }
                    });

            //连接netty服务端
            channel = bootstrap.connect(ip, port).sync().channel();
        } catch (Exception e) {
            e.printStackTrace();
            if (channel != null) {
                channel.close();
            }
            if (group != null) {
                group.shutdownGracefully();
            }
        }
    }


    /**
     * 提供资源关闭的方法
     */
    public void close() {
        if (channel != null) {
            channel.close();
        }
        if (group != null) {
            group.shutdownGracefully();
        }
    }

    /**
     * 提供消息发送的方法
     *
     * @return
     */
    public Object send(String msg) throws ExecutionException, InterruptedException {
        clientHandler.setRequestMsg(msg);

        Future future = executor.submit(clientHandler);
        return future.get();

    }

}
