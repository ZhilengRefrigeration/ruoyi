package com.xjs.juc.poll;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xiejs
 * @since 2022-03-08
 */
public class ThreadPoolDemo2 {

    public static void main(String[] args) {


        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2,                                      //保持线程数
                5,                                                 //最大线程数量
                2L,                                                //线程保持时间
                TimeUnit.SECONDS,                                  //保持时间单位
                new ArrayBlockingQueue<>(3),               //阻塞队列
                Executors.defaultThreadFactory(),                  //线程工厂
                new ThreadPoolExecutor.AbortPolicy()               //拒绝策略
        );
    }


}
