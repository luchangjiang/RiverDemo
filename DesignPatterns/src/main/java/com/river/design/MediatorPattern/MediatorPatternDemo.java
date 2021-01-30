package com.river.design.MediatorPattern;

/**
 * @program: RiverDemo
 * @description: 中介者模式
 * @author: River
 * @create: 2021-01-30 10:48
 **/
public class MediatorPatternDemo {
    public static void main(String[] args) {
        User robert = new User("Robert");
        User john = new User("John");

        robert.sendMessage("Hi! John!");
        john.sendMessage("Hello! Robert!");
    }
}
