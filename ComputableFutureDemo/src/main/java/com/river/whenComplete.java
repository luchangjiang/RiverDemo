package com.river;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-31 23:21
 **/
public class whenComplete {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    //使用ForkJoinPool线程
                    System.out.println("F1  "+Thread.currentThread().getName());
                    return "F1";
                }
        );

        //主线程
        System.out.println(Thread.currentThread().getName());
        CompletableFuture<String> future2 = future.whenComplete((s, throwable) -> {
            System.out.println(s);
            System.out.println(throwable);
            //使用主线程
            System.out.println("F2  "+Thread.currentThread().getName());

        });


        future2.join();
        System.out.println(future2.get());
    }
}
