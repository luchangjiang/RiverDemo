package com.river.design.InterpreterPattern;

import com.river.design.InterpreterPattern.expression.AndExpression;
import com.river.design.InterpreterPattern.expression.Expression;
import com.river.design.InterpreterPattern.expression.OrExpression;
import com.river.design.InterpreterPattern.expression.TerminalExpression;

/**
 * @program: RiverDemo
 * @description: 解释器模式
 * @author: River
 * @create: 2021-01-30 10:28
 **/
public class InterpreterPatternDemo {
    //规则：Robert 和 John 是男性
    public static Expression getMaleExpression(){
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie 是一个已婚的女性
    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpret("John"));
        System.out.println("Julie is a married women? "
                + isMarriedWoman.interpret("Married Julie"));
    }
}
