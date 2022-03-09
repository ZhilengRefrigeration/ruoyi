package com.xjs.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 6辆车，3停车位
 * @author xiejs
 * @since 2022-03-07
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //创建Semaphore，设置许可数量

        Semaphore semaphore = new Semaphore(10);

        for (int i = 1; i <=60; i++) {
            new Thread(()->{
                //抢占车位
                try {
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName()+"抢到了车位");

                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName()+"---离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }

            },String.valueOf(i)).start();

        }


    }

}
