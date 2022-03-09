package com.xjs.nio;

import java.nio.ByteBuffer;

/**
 * @author xiejs
 * @since 2022-03-08
 */
public class CreateBufferDemo {

    public static void main(String[] args) {
        //创建指定长度的缓冲区

        ByteBuffer allocate = ByteBuffer.allocate(5);

        for (int i = 0; i < 5; i++) {
            System.out.println(allocate.get());     //从缓冲区中拿取数据
        }

        //System.out.println(allocate.get());

        //创建有内容的缓冲区
        ByteBuffer wrap = ByteBuffer.wrap("lagou".getBytes());
        for (int i = 0; i < 5; i++) {
            System.out.println(wrap.get());
        }

    }

}
