package com.xjs.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xiejs
 * @since 2022-03-08
 */
public class NioSelectorServer {

    public static void main(String[] args) throws IOException {
        //打开服务端通道
        ServerSocketChannel open = ServerSocketChannel.open();

        //绑定对应的端口号
        open.bind(new InetSocketAddress(9998));

        //通道默认阻塞，开启非阻塞
        open.configureBlocking(false);

        //创建选择器
        Selector selector = Selector.open();

        //将服务端通道注册到选择器上，并指定注册监听的事件为OP_ACCEPT
        open.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务器启动成功...");

        while (true) {

            //检查选择器是否有事件
            int select = selector.select(2000);

            if (select == 0) {
                System.out.println("没有事件发生");
                continue;
            }

            //获取事件集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //判断事件是否是客户端连接事件SelectionKey.isAcceptable()
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {

                    //得到客户端通道，并将通道注册到选择器上，并指定监听事件为OP_READ
                    SocketChannel socketChannel = open.accept();
                    System.out.println("有客户端连接...");

                    //将通道必须设置为非阻塞，因为selector需要轮询监听每个通道的事件
                    socketChannel.configureBlocking(false);

                    //指定监听事件为OP_READ 读就绪事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                //判断客户端是否读就绪事件SelectionKey.isReadable()
                if (key.isReadable()) {
                    //得到客户端通道，读取数据到缓冲区
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    int read = socketChannel.read(allocate);
                    if (read > 0) {
                        System.out.println("客户端消息：" + new String(allocate.array(), 0, read, StandardCharsets.UTF_8));

                        //给客户端写数据
                        socketChannel.write(ByteBuffer.wrap("没钱".getBytes(StandardCharsets.UTF_8)));
                        socketChannel.close();

                    }
                }

                //从集合中删除对应事件，防止二次处理
                iterator.remove();

            }


        }


    }

}
