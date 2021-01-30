package com.river.design.StatePattern.state;

import com.river.design.StatePattern.model.Context;

public class StartState implements State {
 
   public void doAction(Context context) {
      System.out.println("Player is in start state");
      context.setState(this); 
   }
 
   public String toString(){
      return "Start State";
   }
}