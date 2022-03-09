package com.xjs.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * nio客户端
 * @author xiejs
 * @since 2022-03-08
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        //打开通道
        SocketChannel socketChannel = SocketChannel.open();

        //设置连接ip和端口
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9998));

        //写出数据
        socketChannel.write(ByteBuffer.wrap("还钱".getBytes(StandardCharsets.UTF_8)));

        //读取服务端返回的数据
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = socketChannel.read(allocate);
        System.out.println("服务端消息:"+new String(allocate.array(),0,read,StandardCharsets.UTF_8));

        //释放资源
        socketChannel.close();
    }
}
