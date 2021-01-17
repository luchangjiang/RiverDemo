package com.river.bean.bean;

import com.river.bean.entity.OrderEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoInitializingBean implements InitializingBean {

    @Autowired
    private OrderEntity orderEntity;

    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (orderEntity != null) {
            System.out.println("setted");
        }
        name = "already";
    }
}