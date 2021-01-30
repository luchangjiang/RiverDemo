package com.river.design.FacadePattern;

/**
 * @program: RiverDemo
 * @description: 外观模式
 * @author: River
 * @create: 2021-01-30 09:56
 **/
public class FacadePatternDemo {
    public static void main(String[] args) {
        ShapeMaker shapeMaker = new ShapeMaker();

        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
