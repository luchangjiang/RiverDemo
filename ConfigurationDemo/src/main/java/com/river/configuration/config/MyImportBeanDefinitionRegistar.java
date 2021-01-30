package com.river.configuration.config;

import com.river.configuration.pojo.Food;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-10-21 22:58
 **/
public class MyImportBeanDefinitionRegistar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //可以从importingClassMetadata和registry对象获取一些上下文信息进行其他业务逻辑的判断
        boolean b1 = registry.containsBeanDefinition("com.river.configuration.pojo.Student");
        boolean b2 = registry.containsBeanDefinition("com.river.configuration.pojo.Lecturer");

        if(b1 && b2){
            registry.registerBeanDefinition("Food", new RootBeanDefinition(Food.class));
        }
    }
}

