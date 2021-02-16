package com.river.bean.controller;

import com.river.bean.util.SpringContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2021-01-17 16:51
 **/
@RestController
@RequestMapping
public class ShutdownController implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @GetMapping("/shutdown")
    public String shutDownContext() {
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) applicationContext;
        ctx.close();
        return "context is shut down";
    }

    @PostMapping("/removeBean")
    public String removeBean(String beanId) {
        System.out.println(beanId + " - " + (SpringContextHolder.containsBean(beanId)? "found" : "not found"));
        SpringContextHolder.unregisterBean(beanId);
        return beanId + " - " + (SpringContextHolder.containsBean(beanId)? "found" : "not found");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
