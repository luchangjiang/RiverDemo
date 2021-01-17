package com.river.bean.context;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
public class DemoAware
    implements BeanFactoryAware, BeanClassLoaderAware, BeanNameAware, ApplicationContextAware, EnvironmentAware {

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String name;
    private ApplicationContext applicationContext;
    private Environment environment;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
        log.info("classloader {}", classLoader.getClass());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        log.info("beanFactory {}", beanFactory.getClass());
    }

    @Override
    public void setBeanName(String name) {
        this.name = name;
        log.info("name {}", name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("applicationContext {}", applicationContext.getClass());
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        log.info("environment {}", environment.getClass());
    }

}