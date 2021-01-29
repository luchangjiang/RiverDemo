package com.river.design.AbstractFactoryPattern;

import com.river.design.AbstractFactoryPattern.color.Blue;
import com.river.design.AbstractFactoryPattern.color.Color;
import com.river.design.AbstractFactoryPattern.color.Green;
import com.river.design.AbstractFactoryPattern.color.Red;
import com.river.design.AbstractFactoryPattern.shape.Shape;

public class ColorFactory extends AbstractFactory {
    
   @Override
   public Shape getShape(String shapeType){
      return null;
   }
   
   @Override
   public Color getColor(String color) {
      if(color == null){
         return null;
      }        
      if(color.equalsIgnoreCase("RED")){
         return new Red();
      } else if(color.equalsIgnoreCase("GREEN")){
         return new Green();
      } else if(color.equalsIgnoreCase("BLUE")){
         return new Blue();
      }
      return null;
   }
}