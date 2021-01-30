package com.river.design.StatePattern;

import com.river.design.StatePattern.model.Context;
import com.river.design.StatePattern.state.StartState;
import com.river.design.StatePattern.state.StopState;

/**
 * @program: RiverDemo
 * @description: 状态模式
 * @author: River
 * @create: 2021-01-30 11:31
 **/
public class StatePatternDemo {
    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
