package com.river;

import java.util.concurrent.CompletableFuture;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-31 22:58
 **/
public class thenCombine {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s-> s+ " World")
                .thenApply(s->s.toUpperCase())
                .thenCombine(CompletableFuture.completedFuture("Java"), (x,y)-> (x + "," + y))
                .thenAccept(System.out::println);

        Thread.sleep(2000);
    }
}
