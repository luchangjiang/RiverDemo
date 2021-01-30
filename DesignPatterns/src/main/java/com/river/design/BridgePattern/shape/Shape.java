package com.river.design.BridgePattern.shape;

import com.river.design.BridgePattern.drawapi.DrawAPI;

public abstract class Shape {
   protected DrawAPI drawAPI;
   protected Shape(DrawAPI drawAPI){
      this.drawAPI = drawAPI;
   }
   public abstract void draw();  
}