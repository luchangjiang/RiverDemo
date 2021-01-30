package com.river.design.ProxyPattern;

import com.river.design.ProxyPattern.image.Image;

/**
 * @program: RiverDemo
 * @description: 代理模式
 * @author: River
 * @create: 2021-01-30 10:10
 **/
public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }
}
