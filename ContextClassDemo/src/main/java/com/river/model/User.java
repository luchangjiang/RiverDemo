package com.river.model;
  
public class User {  
  
    private String name;  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public void sayName() {  
        System.out.println(getName());  
    }  
  
}  