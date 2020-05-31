package com.river;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-31 22:19
 **/
public class Test4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f =  future.thenAccept(System.out::println);
        System.out.println(f.get());
    }
}