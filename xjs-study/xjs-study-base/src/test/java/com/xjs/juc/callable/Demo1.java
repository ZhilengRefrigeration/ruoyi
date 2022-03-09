package com.xjs.juc.callable;

import java.util.concurrent.Callable;

/**
 * @author xiejs
 * @since 2022-03-07
 */
public class Demo1 {

    public static void main(String[] args) {
        new Thread(new MyThread1(),"AA").start();
    }


}


class MyThread1 implements Runnable {

    @Override
    public void run() {

    }
}


class MyThread2 implements Callable{

    @Override
    public Object call() throws Exception {
        return 200;
    }
}

