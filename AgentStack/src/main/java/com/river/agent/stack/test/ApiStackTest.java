package com.river.agent.stack.test;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-01-11 18:17
 **/
public class ApiStackTest {
    public static void main(String[] args) throws InterruptedException {
        ApiStackTest apiTest = new ApiStackTest();
        apiTest.echoHi();

        //线程一
        new Thread(() -> new ApiStackTest().http_lt1()).start();

        //线程二
        new Thread(() -> {
            new ApiStackTest().http_lt1();
        }).start();
    }

    private void echoHi() throws InterruptedException{
        Thread.sleep(50L);
        System.out.println("hi agent");
    }

    public void http_lt1() {
        System.out.println("测试结果：hi1");
        http_lt2();
    }

    public void http_lt2() {
        System.out.println("测试结果：hi2");
        http_lt3();
    }

    public void http_lt3() {
        System.out.println("测试结果：hi3");
    }
}
