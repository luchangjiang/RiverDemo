package com.river.thread;

import com.river.thread.base.MyTask;

import java.util.concurrent.*;

/**
 * @author ：River
 * @date ：Created in 7/10/2019 9:01 AM
 * @description：
 * @modified By：
 * @version: $
 */
public class FutureDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        MyTask task = new MyTask();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
}




