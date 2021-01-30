package com.river.design.BuilderPattern.item;

import com.river.design.BuilderPattern.packing.Packing;

public interface Item {
   String name();
   Packing packing();
   float price();
}