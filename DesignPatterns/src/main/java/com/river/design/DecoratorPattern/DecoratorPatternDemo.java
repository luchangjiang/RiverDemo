package com.river.design.DecoratorPattern;

import com.river.design.DecoratorPattern.shape.Circle;
import com.river.design.DecoratorPattern.shape.Rectangle;
import com.river.design.DecoratorPattern.shape.Shape;

/**
 * @program: RiverDemo
 * @description: 装饰器模式
 * @author: River
 * @create: 2021-01-30 09:48
 **/
public class DecoratorPatternDemo {
    public static void main(String[] args) {

        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        //Shape redCircle = new RedShapeDecorator(new Circle());
        //Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
