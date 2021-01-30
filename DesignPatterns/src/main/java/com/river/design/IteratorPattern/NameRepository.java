package com.river.design.IteratorPattern;

public class NameRepository implements Container {
   public String[] names = {"Robert" , "John" ,"Julie" , "Lora"};

   public void setNames(String[] names) {
      this.names = names;
   }
 
   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }
 
   private class NameIterator implements Iterator {
 
      int index;
 
      @Override
      public boolean hasNext() {
         if(index < names.length){
            return true;
         }
         return false;
      }
 
      @Override
      public Object next() {
         if(this.hasNext()){
            return names[index++];
         }
         return null;
      }     
   }
}