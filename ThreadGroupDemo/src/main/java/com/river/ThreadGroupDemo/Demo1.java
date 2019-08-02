package com.river.ThreadGroupDemo;

import java.util.concurrent.TimeUnit;

/**
 * <b>description</b>： <br>
 * <b>time</b>：2019/7/13 17:53 <br>
 * <b>author</b>：微信公众号：路人甲Java，专注于java技术分享（带你玩转 爬虫、分布式事务、异步消息服务、任务调度、分库分表、大数据等），喜欢请关注！
 */
public class Demo1 {
    public static class R1 implements Runnable {
        @Override
        public void run() {
            System.out.println("threadName:" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("thread-group-1");
        Thread t1 = new Thread(threadGroup, new R1(), "t1");
        Thread t2 = new Thread(threadGroup, new R1(), "t2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("活动线程数:" + threadGroup.activeCount());
        System.out.println("活动线程组:" + threadGroup.activeGroupCount());
        System.out.println("线程组名称:" + threadGroup.getName());
    }
}
