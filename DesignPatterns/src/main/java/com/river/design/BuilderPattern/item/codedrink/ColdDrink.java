package com.river.design.BuilderPattern.item.codedrink;

import com.river.design.BuilderPattern.packing.Bottle;
import com.river.design.BuilderPattern.item.Item;
import com.river.design.BuilderPattern.packing.Packing;

public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
       return new Bottle();
    }
}