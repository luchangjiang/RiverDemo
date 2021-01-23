package com.river.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumber3 extends Thread {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private int id;
    private static int cnt = 0;
    public PrintNumber3(int id) {
        this.id = id;
    }

    private static void print(int id) {

    }

    @Override
    public void run() {
        while (cnt <= 100) {
            lock.lock();
            System.out.println("thread_" + id + " num:" + cnt);
            cnt++;
            condition.signal();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread thread0 = new PrintNumber3(0);
        Thread thread1 = new PrintNumber3(1);
        Thread thread2 = new PrintNumber3(2);
        thread0.start();
        thread1.start();
        thread2.start();
    }
}