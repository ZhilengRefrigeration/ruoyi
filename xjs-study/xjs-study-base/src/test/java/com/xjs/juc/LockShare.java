package com.xjs.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiejs
 * @since 2022-03-03
 */
public class LockShare {


    //初始值
    private int number = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition=lock.newCondition();



    public void incr() throws InterruptedException {

        lock.lock();

        try {
            while (number != 0) {
                condition.await();
            }
            //如果number是0，则+1
            number++;

            System.out.println(Thread.currentThread().getName() + ":" + number);

            //通知其他线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }


    public void decr() throws InterruptedException {

        lock.lock();

        try {

            //使用while判断防止wait虚假唤醒问题
            while (number != 1) {
                condition.await();
            }
            //如果number是0，则+1
            number--;

            System.out.println(Thread.currentThread().getName() + ":" + number);

            //通知其他线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        LockShare share = new LockShare();

        new Thread(() -> {
            while (true){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "A").start();

        new Thread(() -> {
            while (true) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "B").start();

        new Thread(() -> {
            while (true) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "C").start();

        new Thread(() -> {
            while (true) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "D").start();
    }
}
