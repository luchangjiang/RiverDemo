package com.river.LockDemo.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantInterrupt {
    private static ReentrantLock lock1 = new ReentrantLock(false);
    private static ReentrantLock lock2 = new ReentrantLock(false);

    public static class T extends Thread{
        int flag=1;
        public T(String name, Integer flag){
            super(name);
            this.flag = flag;
        }

        @Override
        public void run(){
            try{
                if(1==this.flag){
                    lock1.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lockInterruptibly();
                }
                else {
                    lock2.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lockInterruptibly();
                }
            }
            catch(InterruptedException ex){
                System.out.println("中断标识：" + this.isInterrupted());
                ex.printStackTrace();
            }
            finally {
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
            }
        }

        public static void main(String[] args) {
            T t1 = new T("t1", 1);
            T t2 = new T("t2", 2);

            t1.start();
            t2.start();
        }

    }
}
