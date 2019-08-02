package com.river.LockDemo.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static Integer num = 0;

    private static ReentrantLock lock = new ReentrantLock();

    private static void add(){
        lock.lock();
        lock.lock();
        try{
            num++;
        }
        finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public static class T extends Thread{
        @Override
        public void run(){
            System.out.println("当前线程：" + getName());
            ReentrantLockDemo.add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1= new T();
        t1.setName("t1");

        T t2= new T();
        t2.setName("t2");

        T t3= new T();
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();

        //保证线程的执行顺序
        /*t1.join();
        t2.join();
        t3.join();*/

        TimeUnit.MILLISECONDS.sleep(5);
        System.out.println(num);
    }
}
