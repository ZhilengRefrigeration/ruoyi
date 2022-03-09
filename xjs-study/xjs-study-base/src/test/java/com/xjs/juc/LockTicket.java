package com.xjs.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock关键字学习
 *
 * @author xiejs
 * @since 2022-03-02
 */
public class LockTicket {

    //票总数
    private int number = 3000;

    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock();


    //卖票方法
    public void sale() {
        //上锁
        lock.lock();

        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + ":卖出:" + (number--) + "剩下:" + number);
            }
        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) {
        LockTicket lockTicket = new LockTicket();

        new Thread(() -> {
            for (int i = 0; i < 9999; i++) {
                lockTicket.sale();
            }

        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 9999; i++) {
                lockTicket.sale();
            }

        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 9999; i++) {
                lockTicket.sale();
            }

        }, "C").start();

    }

}


