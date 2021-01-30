package com.river.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2021-01-27 23:11
 **/
public class DownloadDemo {
    public static int userCount = 100;
    public static volatile int totalDownloads = 50000;
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(userCount);
        for (int i = 0; i < totalDownloads; i++) {
            executorService.execute(() -> {
//                try {
//                    semaphore.acquire();
                    count.getAndIncrement();
//                    semaphore.release();
//                } catch (InterruptedException e) {
//
//                }
            });

        }
        executorService.shutdown();
        Thread.sleep(3000);
        System.out.println(count);
    }
}
