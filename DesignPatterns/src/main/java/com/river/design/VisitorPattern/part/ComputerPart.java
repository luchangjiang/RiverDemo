package com.river.design.VisitorPattern.part;

public interface ComputerPart {
   public void accept(ComputerPartVisitor computerPartVisitor);
}