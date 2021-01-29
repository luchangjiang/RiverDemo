package com.river.design.AbstractFactoryPattern;

import com.river.design.AbstractFactoryPattern.color.Color;
import com.river.design.AbstractFactoryPattern.shape.Circle;
import com.river.design.AbstractFactoryPattern.shape.Rectangle;
import com.river.design.AbstractFactoryPattern.shape.Shape;
import com.river.design.AbstractFactoryPattern.shape.Square;

public class ShapeFactory extends AbstractFactory {
    
   @Override
   public Shape getShape(String shapeType){
      if(shapeType == null){
         return null;
      }        
      if(shapeType.equalsIgnoreCase("CIRCLE")){
         return new Circle();
      } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
         return new Rectangle();
      } else if(shapeType.equalsIgnoreCase("SQUARE")){
         return new Square();
      }
      return null;
   }
   
   @Override
   public Color getColor(String color) {
      return null;
   }
}