package com.xjs.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xiejs
 * @since 2022-03-08
 */
public class CompleableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> c =CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
        });
        c.get();


        CompletableFuture<String> cc =CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            return Thread.currentThread().getName();
        });

        cc.whenComplete((t,u)->{
            System.out.println(t);      //返回值
            System.out.println(u);      //异常
        }).get();

    }

}
