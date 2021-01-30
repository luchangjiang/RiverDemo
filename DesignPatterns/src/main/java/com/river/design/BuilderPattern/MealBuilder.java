package com.river.design.BuilderPattern;

import com.river.design.BuilderPattern.item.codedrink.Pepsi;
import com.river.design.BuilderPattern.item.codedrink.Coco;
import com.river.design.BuilderPattern.item.burger.*;

public class MealBuilder {
 
   public Meal prepareVegMeal (){
      Meal meal = new Meal();
      meal.addItem(new VegBurger());
      meal.addItem(new Coco());
      return meal;
   }   
 
   public Meal prepareNonVegMeal (){
      Meal meal = new Meal();
      meal.addItem(new ChickenBurger());
      meal.addItem(new Pepsi());
      return meal;
   }
}