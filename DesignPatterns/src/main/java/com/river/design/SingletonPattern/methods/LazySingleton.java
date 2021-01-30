package com.river.design.SingletonPattern.methods;

/**
 * 优点：第一次调用才初始化，避免内存浪费。
 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 */
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){}
    public static synchronized LazySingleton getInstance() {
    if (instance == null) {  
        instance = new LazySingleton();
    }  
    return instance;  
    }  
}