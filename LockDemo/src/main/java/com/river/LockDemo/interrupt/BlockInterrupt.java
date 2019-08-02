package com.river.LockDemo.interrupt;

import java.util.concurrent.TimeUnit;

public class BlockInterrupt {
    public static class T extends Thread{
        @Override
        public void run(){
            while (true){
                System.out.println(getName());
                try{
                    TimeUnit.SECONDS.sleep(100);
                }
                catch (InterruptedException ex){
                    ex.printStackTrace();
                    this.interrupt();
                }
                if(this.isInterrupted()){
                    System.out.println(getName() + "中断喽");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.setName("Block线程");
        t.start();

        TimeUnit.SECONDS.sleep(2L);
        t.interrupt();
    }
}
