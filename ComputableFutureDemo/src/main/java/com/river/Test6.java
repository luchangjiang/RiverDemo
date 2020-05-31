package com.river;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-31 22:13
 **/
public class Test6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });
        CompletableFuture<String> f =  future.thenCombine(future2, (x,y) -> y + "-" + x);
        System.out.println(f.get());
    }
}
