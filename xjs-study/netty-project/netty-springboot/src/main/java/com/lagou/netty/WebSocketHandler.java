package com.lagou.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义处理类
 * @author xiejs
 * @since 2022-03-09
 */
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    private static List<Channel> channelList = new ArrayList<>();

    /**
     * 读就绪事件
     * @param ctx
     * @param msg websocket数据是帧的形式处理
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println("msg="+msg.text());

        Channel channel = ctx.channel();

        for (Channel channel1 : channelList) {
            //排除自身通道
            if (channel != channel1) {
                channel1.writeAndFlush(new TextWebSocketFrame(msg.text() ));
            }
        }

    }

    /**
     * 通道未就绪 --channel下线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        //当有客户端断开连接的时候，就移出对应的通道
        channelList.remove(channel);


    }

    /**
     * 通道就绪事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();

        //当有新的客户端连接的时候，将通道放入集合
        channelList.add(channel);
        System.out.println("[Server]:"+channel.remoteAddress().toString().substring(1)+"在线");

    }

    /**
     * 异常处理事件
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        System.out.println("[Server]:"+channel.remoteAddress().toString().substring(1)+"异常");
    }
}
