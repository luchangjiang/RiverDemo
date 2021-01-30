package com.river.design.DelegatePattern;

import com.river.design.DelegatePattern.service.BusinessLookUp;
import com.river.design.DelegatePattern.service.BusinessService;

public class BusinessDelegate {
   private BusinessLookUp lookupService = new BusinessLookUp();
   private BusinessService businessService;
   private String serviceType;
 
   public void setServiceType(String serviceType){
      this.serviceType = serviceType;
   }
 
   public void doTask(){
      businessService = lookupService.getBusinessService(serviceType);
      businessService.doProcessing();     
   }
}