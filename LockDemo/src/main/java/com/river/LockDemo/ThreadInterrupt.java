package com.river.LockDemo;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {
    public static class T extends Thread {
        @Override
        public void run(){
            while (true){
                System.out.println(getName());
                if(this.isInterrupted()){
                    System.out.println("线程中断喽");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.setName("Interrupt Demo线程");
        t.start();

        TimeUnit.SECONDS.sleep(2L);
        t.interrupt();
    }
}
