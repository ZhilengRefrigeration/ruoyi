package com.lagou.rpc.consumer.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * 客户端处理类
 * <ol>
 *     <li>发送消息</li>
 *     <li>接收消息</li>
 * </ol>
 * @author xiejs
 * @since 2022-03-10
 */
@Data
@Component
public class RpcClientHandler extends SimpleChannelInboundHandler<String> implements Callable {

    private ChannelHandlerContext ctx;

    /**
     * 发送的消息
     */
    String requestMsg;


    String responseMsg;





    /**
     * 通道连接就绪事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    /**
     * 通道读取就绪事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected synchronized void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        responseMsg = msg;

        //唤醒等待的线程
        notify();

    }

    @Override
    public synchronized Object call() throws Exception {
        //消息发送
        ctx.writeAndFlush(requestMsg);

        //线程等待
        wait();


        return responseMsg;
    }
}
