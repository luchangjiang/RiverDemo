package com.river.LockDemo.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock {
    private static Integer num = 0;

    private static ReentrantLock fairLock = new ReentrantLock(true);

    public static class T extends Thread{

        public T(String name){
            super(name);
        }
        @Override
        public void run(){
            for(int i=0; i<5; i++){
                try{
                    fairLock.lock();
                    System.out.println("当前线程：" + getName());
                }
                finally {
                    fairLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1= new T("t1");
        T t2= new T("t2");
        T t3= new T("t3");

        t1.start();
        t2.start();
        t3.start();

        //保证线程的执行顺序
        t1.join();
        t2.join();
        t3.join();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(num);
    }
}
