package com.river.LockDemo;

public class DaemonThread {
    public static class T1 extends Thread{
        public T1(String name){
            super(name);
        }

        @Override
        public void run(){
            System.out.println(this.getName() + "开始执行，" + (this.isDaemon()? "我是守护进程": "我是用户进程"));
            while (true){}
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1("Daemon");
        t1.setDaemon(true);
        t1.start();
        System.out.println("主线程结束");
    }
}
