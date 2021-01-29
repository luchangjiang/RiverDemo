package com.river.design.AbstractFactoryPattern;

import com.river.design.AbstractFactoryPattern.color.Color;
import com.river.design.AbstractFactoryPattern.shape.Shape;

public abstract class AbstractFactory {
   public abstract Color getColor(String color);
   public abstract Shape getShape(String shape) ;
}