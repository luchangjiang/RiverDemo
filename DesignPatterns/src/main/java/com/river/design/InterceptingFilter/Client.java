package com.river.design.InterceptingFilter;

import com.river.design.InterceptingFilter.manager.FilterManager;

public class Client {
   FilterManager filterManager;
 
   public void setFilterManager(FilterManager filterManager){
      this.filterManager = filterManager;
   }
 
   public void sendRequest(String request){
      filterManager.filterRequest(request);
   }
}