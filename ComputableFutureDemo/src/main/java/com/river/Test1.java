package com.river;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-31 22:23
 **/
public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1/0;
            return 100;
        });
        System.out.println(future.get());
    }
}