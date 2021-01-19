package com.river;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
  
    public static void main(String[] args) {  
  
        try {  
  
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                    "spring-config.xml");  
  
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext
                    .getAutowireCapableBeanFactory();  
  
            String configurationFilePath = "jar:file:/C:/hehe.jar!/spring-plugin.xml";  
  
            URL url = new URL(configurationFilePath);
  
            UrlResource urlResource = new UrlResource(url);
  
            XmlBeanFactory xbf = new XmlBeanFactory(urlResource);
  
            String[] beanIds = xbf.getBeanDefinitionNames();  
  
            for (String beanId : beanIds) {  
                BeanDefinition bd = xbf.getMergedBeanDefinition(beanId);
                beanFactory.registerBeanDefinition(beanId, bd);  
            }  
  
            // 以下这行设置BeanFactory的ClassLoader，以加载外部类  
            setBeanClassLoader(beanFactory);  
  
            Object pluginBean = applicationContext.getBean("myUser");  
  
            tryInvoke(pluginBean);  
  
        } catch (Exception exc) {  
            exc.printStackTrace();  
        }  
  
    }  
  
    private static void setBeanClassLoader(  
            DefaultListableBeanFactory beanFactory)  
            throws MalformedURLException {
        String jarFilePath = "c://hehe.jar";  
        URL jarUrl = new File(jarFilePath).toURI().toURL();
        URL[] urls = new URL[] { jarUrl };  
        URLClassLoader cl = new URLClassLoader(urls);
        beanFactory.setBeanClassLoader(cl);  
    }  
  
    private static void tryInvoke(Object bean) throws SecurityException,  
            NoSuchMethodException, IllegalArgumentException,  
            IllegalAccessException, InvocationTargetException {
  
        Class<?> paramTypes[] = new Class[0];  
        Method method = bean.getClass()
                .getDeclaredMethod("sayName", paramTypes);  
        Object paramValues[] = new Object[0];  
        method.invoke(bean, paramValues);
    }
}  