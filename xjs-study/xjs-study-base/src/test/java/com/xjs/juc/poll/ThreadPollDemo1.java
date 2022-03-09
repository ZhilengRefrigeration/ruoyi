package com.xjs.juc.poll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiejs
 * @since 2022-03-07
 */
public class ThreadPollDemo1 {

    public static void main(String[] args) {
        //一池多线程

        ExecutorService pool = Executors.newFixedThreadPool(100);

        try {
            //10个请求

            for (int i = 1; i <= 10000000; i++) {

                //执行
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });

            }


        }catch (Exception e){
            e.printStackTrace();

        }finally {
            pool.shutdown();
        }



    }

}
