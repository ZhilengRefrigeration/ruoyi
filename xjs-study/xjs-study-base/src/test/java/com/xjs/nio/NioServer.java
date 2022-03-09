package com.xjs.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author xiejs
 * @since 2022-03-08
 */
public class NioServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        //打开一个服务端通道
        ServerSocketChannel open = ServerSocketChannel.open();
        //绑定对应的端口
        open.bind(new InetSocketAddress(9998));

        //通道默认阻塞，开启非阻塞
        open.configureBlocking(false);
        System.out.println("服务端启动成功");

        while (true) {
            //检查是否有客户端连接，有客户端连接会返回对应的通道
            SocketChannel channel = open.accept();
            if (channel == null) {
                System.out.println("没有客户端连接，休息2秒");
                Thread.sleep(2000);
                continue;
            }

            //获取客户端传递过来的数据，并且把数据放在byteBuffer这个缓冲区中
            ByteBuffer allocate = ByteBuffer.allocate(1024);

            //正数：本次读到的有效的字节数    0：本次没有读到数据  -1：读到末尾
            int read = channel.read(allocate);
            System.out.println("客户端消息:"+new String(allocate.array(),
                    0,
                    read,
                    StandardCharsets.UTF_8));

            //给客户端回写数据
            channel.write(ByteBuffer.wrap("没钱".getBytes(StandardCharsets.UTF_8)));

            //释放资源
            channel.close();


        }


    }
}
