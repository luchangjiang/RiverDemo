package com.river.design.BuilderPattern.item.burger;

import com.river.design.BuilderPattern.item.Item;
import com.river.design.BuilderPattern.packing.Packing;
import com.river.design.BuilderPattern.packing.Wrapper;

public abstract class Burger implements Item {
 
   @Override
   public Packing packing() {
      return new Wrapper();
   }
}