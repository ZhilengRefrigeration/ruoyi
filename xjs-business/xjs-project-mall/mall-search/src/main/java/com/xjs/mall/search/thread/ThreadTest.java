package com.xjs.mall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiejs
 * @since 2022-05-12
 */
public class ThreadTest {

    public static ExecutorService executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1启动了");
            int i = 10 / 5;
            System.out.println(i);
            return i;
        }, executors).thenAcceptAsync(result -> {
            System.out.println("任务2启动了-----任务1的值：" + result);
        }, executors);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "hello";
        }, executors);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "hello2";
        }, executors);

        CompletableFuture<Void> future = future2.thenAcceptBothAsync(future3, ((f1, f2) -> {
            System.out.println(f1);
            System.out.println(f2);
        }), executors);
    }
}
