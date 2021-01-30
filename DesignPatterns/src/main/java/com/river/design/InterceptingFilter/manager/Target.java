package com.river.design.InterceptingFilter.manager;

public class Target {
   public void execute(String request){
      System.out.println("Executing request: " + request);
   }
}