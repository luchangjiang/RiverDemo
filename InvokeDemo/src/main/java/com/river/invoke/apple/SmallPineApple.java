package com.river.invoke.apple;

import java.beans.Transient;

public class SmallPineApple extends PineApple {
    public String name;
    public int age;
    private double weight = 45; // 体重只有自己知道
   	
    public SmallPineApple() {}
    
    public SmallPineApple(String name, int age) {
        this.name = name;
        this.age = age;
        this.weight = 58;
    }

    @Transient
    @Override
    public void getInfo() {
        System.out.println("["+ name + " 的年龄是：" + age + "]");
    }

}