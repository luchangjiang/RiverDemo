package com.river.design.FlyWeightPattern;

import com.river.design.FlyWeightPattern.shape.Circle;
import com.river.design.FlyWeightPattern.shape.Shape;

import java.util.HashMap;
 
public class ShapeFactory {
   private static final HashMap<String, Shape> circleMap = new HashMap<>();
 
   public static Shape getCircle(String color, int x, int y, int radius) {
      Circle circle = (Circle)circleMap.get(color);
 
      if(circle == null) {
         circle = new Circle(color, x, y, radius);
         circleMap.put(color, circle);
         System.out.println("Creating circle of color : " + color);
      }
      return circle;
   }
}