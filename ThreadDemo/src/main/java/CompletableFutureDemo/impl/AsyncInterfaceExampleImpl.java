package CompletableFutureDemo.impl;

import CompletableFutureDemo.AsyncInterfaceExample;

import java.util.concurrent.CompletableFuture;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-08-12 23:17
 **/
public class AsyncInterfaceExampleImpl implements AsyncInterfaceExample {
    @Override
    public String computeSomeThing(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello world!";
    }

    @Override
    public CompletableFuture<String> computeSomeThingAsync(){
        return CompletableFuture.supplyAsync(this::computeSomeThing);
    }
}
