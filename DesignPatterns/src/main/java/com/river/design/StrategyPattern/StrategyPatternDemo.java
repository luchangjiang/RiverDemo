package com.river.design.StrategyPattern;

import com.river.design.StrategyPattern.strategy.OperationAdd;
import com.river.design.StrategyPattern.strategy.OperationMultiply;
import com.river.design.StrategyPattern.strategy.OperationSubtract;

/**
 * @program: RiverDemo
 * @description: 策略模式
 * @author: River
 * @create: 2021-01-30 11:41
 **/
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
