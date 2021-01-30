package com.river.design.ServiceLocatorPattern;

import com.river.design.ServiceLocatorPattern.service.Service;

public class ServiceLocator {
   private static Cache cache;
 
   static {
      cache = new Cache();    
   }
 
   public static Service getService(String jndiName){
 
      Service service = cache.getService(jndiName);
 
      if(service != null){
         return service;
      }
 
      ServiceFactory context = new ServiceFactory();
      Service service1 = (Service)context.build(jndiName);
      cache.addService(service1);
      return service1;
   }
}