package com.river.design.BridgePattern;

import com.river.design.BridgePattern.drawapi.GreenCircle;
import com.river.design.BridgePattern.drawapi.RedCircle;
import com.river.design.BridgePattern.shape.Circle;
import com.river.design.BridgePattern.shape.Shape;

/**
 * @program: RiverDemo
 * @description: 桥接模式
 * @author: River
 * @create: 2021-01-30 00:56
 **/
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
