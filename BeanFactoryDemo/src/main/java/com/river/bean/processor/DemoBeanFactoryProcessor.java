package com.river.bean.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2021-01-17 13:54
 **/
@Component
public class DemoBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition goodEntity = beanFactory.getBeanDefinition("goodEntity");
        goodEntity.getPropertyValues().addPropertyValue("name", "测试设置货品名称");
    }
}
