package com.river.design.AbstractFactoryPattern.color;

import com.river.design.AbstractFactoryPattern.color.Color;

public class Red implements Color {
 
   @Override
   public void fill() {
      System.out.println("Inside Red::fill() method.");
   }
}