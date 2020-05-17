package com.river.starterDemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-05-17 21:18
 **/
@ConfigurationProperties(prefix = "spring.phone")
public class PhoneProperties {
    private String name = "小米";
    private Integer storage = 1024;
    private String money = "1299rmb";
    //get set 方法
}
