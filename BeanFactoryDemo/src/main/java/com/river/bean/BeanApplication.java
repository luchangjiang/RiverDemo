package com.river.bean;

import com.river.bean.entity.GoodEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2021-01-17 13:52
 **/
@SpringBootApplication
public class BeanApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BeanApplication.class, args);
        System.err.println(run.getBean(GoodEntity.class).getName());
    }
}
