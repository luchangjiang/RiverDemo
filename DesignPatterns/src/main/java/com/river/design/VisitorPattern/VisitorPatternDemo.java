package com.river.design.VisitorPattern;

import com.river.design.VisitorPattern.part.Computer;
import com.river.design.VisitorPattern.part.ComputerPart;
import com.river.design.VisitorPattern.part.ComputerPartDisplayVisitor;

/**
 * @program: RiverDemo
 * @description: 访问者模式
 * @author: River
 * @create: 2021-01-30 11:49
 **/
public class VisitorPatternDemo {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
