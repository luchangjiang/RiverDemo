package com.river.bean.bean;

import javax.annotation.PreDestroy;

/**
 * @program: RiverDemo
 * @description: 测试 preDestroy
 * @author: River
 * @create: 2021-01-17 16:59
 **/
public class TeminateBean {
    @PreDestroy
    public void preDestroy() {
        System.out.println("TeminateBean is destroyed");
    }
}
