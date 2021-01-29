package com.river.design.BuilderPattern.item.codedrink;

public class Coco extends ColdDrink {
 
   @Override
   public float price() {
      return 30.0f;
   }
 
   @Override
   public String name() {
      return "Coke";
   }
}