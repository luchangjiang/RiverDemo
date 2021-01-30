package com.river.design.NullPattern;

import com.river.design.NullPattern.customer.AbstractCustomer;
import com.river.design.NullPattern.customer.NullCustomer;
import com.river.design.NullPattern.customer.RealCustomer;

public class CustomerFactory {
   
   public static final String[] names = {"Rob", "Joe", "Julie"};
 
   public static AbstractCustomer getCustomer(String name){
      for (int i = 0; i < names.length; i++) {
         if (names[i].equalsIgnoreCase(name)){
            return new RealCustomer(name);
         }
      }
      return new NullCustomer();
   }
}