package com.river.design.SingletonPattern.methods;

/**
 * 双检锁/双重校验锁
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * 实现难度：较复杂
 */
public class DblLockSingleton {
    private volatile static DblLockSingleton singleton;

    private DblLockSingleton() {
    }

    public static DblLockSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DblLockSingleton.class) {
                if (singleton == null) {
                    singleton = new DblLockSingleton();
                }
            }
        }
        return singleton;
    }
}