package com.river.LockDemo;

import java.util.concurrent.TimeUnit;

public class ThreadExit {
    public volatile static boolean exit = false;

    public static class T extends Thread{
        @Override
        public void run(){
            while (true){
                System.out.println(getName());
                if(exit){
                    System.out.println("线程退出喽");
                    break;
                }
            }
        }
    }

    public static void setExit(){
        exit = true;
    }

    public static void main(String[] args) throws InterruptedException{
        T t = new T();

        t.setName("exit thread demo");
        t.start();

        TimeUnit.SECONDS.sleep(1L);
        setExit();
    }
}
