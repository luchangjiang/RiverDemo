package com.river.LockDemo.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {
    private static ReentrantLock lock1 = new ReentrantLock(false);

    public static class T extends Thread{
        public T(String name){
            super(name);
        }

        @Override
        public void run(){
            try{
                if(lock1.tryLock(3, TimeUnit.SECONDS)){
                    System.out.println(getName() + "得到锁");
                    TimeUnit.SECONDS.sleep(4);
                }
                else{
                    System.out.println(getName() + "未得到锁");
                }
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
            finally {
                if(lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        T t1 = new T("t1");
        T t2 = new T("t2");

        t1.start();
        t2.start();
    }
}
