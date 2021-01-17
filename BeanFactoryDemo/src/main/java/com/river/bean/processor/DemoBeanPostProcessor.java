package com.river.bean.processor;

import com.river.bean.entity.OrderEntity;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DemoBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof OrderEntity) {
            OrderEntity entity = (OrderEntity)bean;
            System.err.println("before");
            System.err.println("orderId " + entity.getOrderId());
            entity.setOrderId("id-0002");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof OrderEntity) {
            OrderEntity entity = (OrderEntity)bean;
            System.err.println("after");
            System.err.println("orderId " + entity.getOrderId());
        }
        return bean;
    }
}