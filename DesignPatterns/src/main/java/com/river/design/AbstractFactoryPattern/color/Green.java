package com.river.design.AbstractFactoryPattern.color;

import com.river.design.AbstractFactoryPattern.color.Color;

public class Green implements Color {
 
   @Override
   public void fill() {
      System.out.println("Inside Green::fill() method.");
   }
}