package com.river.design.ObserverPattern;

import com.river.design.ObserverPattern.observer.BinaryObserver;
import com.river.design.ObserverPattern.observer.HexaObserver;
import com.river.design.ObserverPattern.observer.OctalObserver;

/**
 * @program: RiverDemo
 * @description: 观察者模式
 * @author: River
 * @create: 2021-01-30 11:03
 **/
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
