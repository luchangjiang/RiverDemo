package com.river.ThreadGroupDemo;

import java.util.concurrent.TimeUnit;

/**
 * <b>description</b>： <br>
 * <b>time</b>：2019/7/13 17:53 <br>
 * <b>author</b>：微信公众号：路人甲Java，专注于java技术分享（带你玩转 爬虫、分布式事务、异步消息服务、任务调度、分库分表、大数据等），喜欢请关注！
 */
public class Demo4 {
    public static class R1 implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println("所属线程组:" + thread.getThreadGroup().getName() + ",线程名称:" + thread.getName());
            while (!thread.isInterrupted()) {
                ;
            }
            System.out.println("线程:" + thread.getName() + "停止了！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup1 = new ThreadGroup("thread-group-1");
        Thread t1 = new Thread(threadGroup1, new R1(), "t1");
        Thread t2 = new Thread(threadGroup1, new R1(), "t2");
        t1.start();
        t2.start();

        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1, "thread-group-2");
        Thread t3 = new Thread(threadGroup2, new R1(), "t3");
        Thread t4 = new Thread(threadGroup2, new R1(), "t4");
        t3.start();
        t4.start();
        TimeUnit.SECONDS.sleep(1);

        System.out.println("-----------threadGroup1信息-----------");
        threadGroup1.list();

        System.out.println("----------------------");
        System.out.println("停止线程组：" + threadGroup1.getName() + "中的所有子孙线程");
        threadGroup1.interrupt();
        TimeUnit.SECONDS.sleep(2);

        System.out.println("----------threadGroup1停止后，输出信息------------");
        threadGroup1.list();
    }
}