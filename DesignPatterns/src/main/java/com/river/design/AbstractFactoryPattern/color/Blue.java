package com.river.design.AbstractFactoryPattern.color;

import com.river.design.AbstractFactoryPattern.color.Color;

public class Blue implements Color {
 
   @Override
   public void fill() {
      System.out.println("Inside Blue::fill() method.");
   }
}