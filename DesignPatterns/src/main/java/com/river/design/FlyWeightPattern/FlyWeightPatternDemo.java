package com.river.design.FlyWeightPattern;

import com.river.design.FlyWeightPattern.shape.Circle;

/**
 * @program: RiverDemo
 * @description: 享元模式
 * @author: River
 * @create: 2021-01-30 10:00
 **/
public class FlyWeightPatternDemo {
    private static final String colors[] =
            {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {

        for (int i = 0; i < 20; ++i) {
            Circle circle =
                    (Circle) ShapeFactory.getCircle(getRandomColor(), getRandomX(), getRandomY(), 100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}
