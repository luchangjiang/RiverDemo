package com.river.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintNumber extends Thread {
    private static AtomicInteger cnt = new AtomicInteger();
    private int id;
    public PrintNumber(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (cnt.get() <= 100) {
            while (cnt.get()%3 == id) {
                System.out.println("thread_" + id + " num:" + cnt.get());
                cnt.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread0 = new PrintNumber(0);
        Thread thread1 = new PrintNumber(1);
        Thread thread2 = new PrintNumber(2);
        thread0.start();
        thread1.start();
        thread2.start();
    }
}