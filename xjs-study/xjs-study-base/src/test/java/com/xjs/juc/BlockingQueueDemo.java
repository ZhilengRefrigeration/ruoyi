package com.xjs.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xiejs
 * @since 2022-03-07
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //第一组
        System.out.println(blockingQueue.add("A"));

        System.out.println(blockingQueue.add("B"));

        System.out.println(blockingQueue.add("C"));

        //System.out.println(blockingQueue.add("D"));


        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());


    }
}
