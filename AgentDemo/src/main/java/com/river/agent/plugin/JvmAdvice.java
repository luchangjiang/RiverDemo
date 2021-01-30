package com.river.agent.plugin;

import org.aopalliance.aop.Advice;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-01-11 22:53
 **/
public class JvmAdvice {
    @Advice.OnMethodExit()
    public static void exit() {
        JvmStack.printMemoryInfo();
        JvmStack.printGCInfo();
    }
}
