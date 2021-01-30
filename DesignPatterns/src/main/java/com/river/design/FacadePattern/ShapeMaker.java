package com.river.design.FacadePattern;

import com.river.design.FacadePattern.shape.Circle;
import com.river.design.FacadePattern.shape.Rectangle;
import com.river.design.FacadePattern.shape.Shape;
import com.river.design.FacadePattern.shape.Square;

public class ShapeMaker {
   private Shape circle;
   private Shape rectangle;
   private Shape square;
 
   public ShapeMaker() {
      circle = new Circle();
      rectangle = new Rectangle();
      square = new Square();
   }
 
   public void drawCircle(){
      circle.draw();
   }
   public void drawRectangle(){
      rectangle.draw();
   }
   public void drawSquare(){
      square.draw();
   }
}