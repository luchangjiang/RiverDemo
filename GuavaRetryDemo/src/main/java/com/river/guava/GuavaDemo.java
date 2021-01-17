package com.river.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2021-01-17 17:32
 **/
@SpringBootApplication
@EnableScheduling
public class GuavaDemo {
    public static void main(String[] args) {
        SpringApplication.run(GuavaDemo.class, args);
    }
}
