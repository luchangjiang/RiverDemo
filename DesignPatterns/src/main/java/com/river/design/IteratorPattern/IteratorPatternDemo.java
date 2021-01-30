package com.river.design.IteratorPattern;

/**
 * @program: RiverDemo
 * @description: 迭代器模式
 * @author: River
 * @create: 2021-01-30 10:38
 **/
public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        String[] names = {"Zhangsan" , "Lisi" ,"Wangwu" , "Jialiu"};
        namesRepository.setNames(names);

        for (Iterator iter = namesRepository.getIterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            System.out.println("Name : " + name);
        }
    }
}
