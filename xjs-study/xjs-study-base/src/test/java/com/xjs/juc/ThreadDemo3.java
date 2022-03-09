package com.xjs.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建资源类
 *
 * @author xiejs
 * @since 2022-03-07
 */

class ShareResource {

    //定义标志位
    private int flag = 1;   //1 AA  2 BB    3 CC

    //创建Lock锁
    private Lock lock = new ReentrantLock();

    //创建3个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            //干活
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+":轮数："+loop);

            }
            flag = 2;
            c1.signal();


        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                c1.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+":轮数："+loop);

            }
            flag = 3;
            c1.signal();


        } finally {
            lock.unlock();
        }
    }

    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                c1.await();
            }
            //干活
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i+":轮数："+loop);

            }
            flag = 1;
            c1.signal();


        } finally {
            lock.unlock();
        }
    }

}


public class ThreadDemo3 {

    public static void main(String[] args) throws InterruptedException {
        ShareResource shareResource = new ShareResource();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

    }

}
