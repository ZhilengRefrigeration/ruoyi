package com.xjs.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * http服务器处理类
 * @author xiejs
 * @since 2022-03-09
 */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取就绪事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //1、判断请求是不是http请求
        if (msg instanceof HttpRequest) {

            DefaultHttpRequest request = (DefaultHttpRequest) msg;

            System.out.println("浏览器请求路径："+request.uri());

            if ("/favicon.ico".equals(request.uri())) {
                System.out.println("图标不响应");
                return;
            }

            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,I am Netty Server !", CharsetUtil.UTF_8);

            //2、给浏览器响应
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                   HttpVersion.HTTP_1_1 ,HttpResponseStatus.OK,byteBuf
            );

            //2.1、设置响应头
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,
                    byteBuf.readableBytes());

            ctx.writeAndFlush(response);

        }




    }
}
