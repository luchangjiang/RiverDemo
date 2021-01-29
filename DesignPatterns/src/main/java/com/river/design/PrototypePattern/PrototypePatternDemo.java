package com.river.design.PrototypePattern;

import com.river.design.PrototypePattern.shape.Shape;

/**
 * @program: RiverDemo
 * @description: 原型模式
 * @author: River
 * @create: 2021-01-30 00:37
 **/
public class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
