package com.river.design.FilterPattern.service.impl;

import com.river.design.FilterPattern.model.Person;
import com.river.design.FilterPattern.service.Criteria;

import java.util.ArrayList;
import java.util.List;
 
public class CriteriaFemale implements Criteria {
   @Override
   public List<Person> meetCriteria(List<Person> persons) {
      List<Person> femalePersons = new ArrayList<Person>(); 
      for (Person person : persons) {
         if(person.getGender().equalsIgnoreCase("FEMALE")){
            femalePersons.add(person);
         }
      }
      return femalePersons;
   }
}