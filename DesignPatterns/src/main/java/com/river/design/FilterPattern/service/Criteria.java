package com.river.design.FilterPattern.service;

import com.river.design.FilterPattern.model.Person;

import java.util.List;
 
public interface Criteria {
   public List<Person> meetCriteria(List<Person> persons);
}