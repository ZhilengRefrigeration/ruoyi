package com.xjs.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author xiejs
 * @since 2022-03-07
 */
public class DeadLock {

    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a) {
                System.out.println("A");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (b) {
                    System.out.println("B");
                }
            }
        }).start();

        new Thread(()->{
            synchronized (b) {
                System.out.println("a");

                synchronized (a) {
                    System.out.println("b");
                }
            }
        }).start();
    }

}
