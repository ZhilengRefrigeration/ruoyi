package com.xjs.juc;

/**
 * @author xiejs
 * @since 2022-03-03
 */
public class Share {

    //初始值
    private int number = 0;

    public synchronized void incr() throws InterruptedException {

        if (number != 0) {
            this.wait();
        }

        //如果number是0，则+1
        number++;

        System.out.println(Thread.currentThread().getName() + ":" + number);

        //通知其他线程
        this.notify();

    }

    //-1
    public synchronized void decr() throws InterruptedException {

        if (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + ":" + number);

        //通知其他线程
        this.notify();
    }

    public static void main(String[] args) {
        Share share = new Share();

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
    }


}
