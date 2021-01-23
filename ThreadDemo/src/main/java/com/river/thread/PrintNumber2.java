package com.river.thread;

public class PrintNumber2 extends Thread {
    private static int cnt = 0;
    private int id;  // 线程编号

    public PrintNumber2(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (cnt <= 100) {
            while (cnt % 3 == id) {
                synchronized (PrintNumber2.class) {
                    System.out.println("thread_" + id + " num:" + cnt);
                    cnt++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread0 = new PrintNumber2(0);
        Thread thread1 = new PrintNumber2(1);
        Thread thread2 = new PrintNumber2(2);
        thread0.start();
        thread1.start();
        thread2.start();
    }
}