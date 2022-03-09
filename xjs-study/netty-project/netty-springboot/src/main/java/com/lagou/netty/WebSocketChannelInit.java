package com.lagou.netty;

import com.lagou.config.NettyConfig;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiejs
 * @since 2022-03-09
 */
@Component
public class WebSocketChannelInit extends ChannelInitializer {

    @Autowired
    private NettyConfig nettyConfig;

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //对http协议支持
        pipeline.addLast(new HttpServerCodec());

        //对大数据流支持
        pipeline.addLast(new ChunkedWriteHandler());

        //post请求分三部分、request line / request header / message body
        pipeline.addLast(new HttpObjectAggregator(8000));

        //将http协议升级为ws协议  websocket支持
        pipeline.addLast(new WebSocketServerProtocolHandler(nettyConfig.getPath()));

        pipeline.addLast(webSocketHandler);



    }
}
