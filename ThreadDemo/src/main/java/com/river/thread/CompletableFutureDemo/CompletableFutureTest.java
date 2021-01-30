package com.river.thread.CompletableFutureDemo;

import com.river.thread.CompletableFutureDemo.impl.AsyncInterfaceExampleImpl;

import java.util.concurrent.CompletableFuture;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-08-12 23:20
 **/
public class CompletableFutureTest {
    private static String getOtherSomething(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "other world!";
    }

    public static void main(String[] args) throws InterruptedException {
        AsyncInterfaceExample asyncInterfaceExample = new AsyncInterfaceExampleImpl();

        //同步调用
        long start = System.currentTimeMillis();
        String someThine = asyncInterfaceExample.computeSomeThing();
        String otherThine = getOtherSomething();
        System.out.println("cost: " + (System.currentTimeMillis() - start) + "ms  result: "+ someThine + otherThine);

        //异步调用
        long start2 = System.currentTimeMillis();
        CompletableFuture<String> someThineFuture = asyncInterfaceExample.computeSomeThingAsync();
        String otherThine2 = getOtherSomething();

        someThineFuture.whenComplete((returnValue, exception)->{
           if(exception ==null){
               System.out.println("cost2: " + (System.currentTimeMillis() - start2) + "ms  result2: "+ returnValue + otherThine2);
           }else {
               exception.printStackTrace();
           }
        });
        Thread.sleep(4000);
    }
}
