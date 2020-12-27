package com.river.JavaAssisantDemo;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

/**
 * @program: RiverDemo
 * @description: 代理方法
 * @author: River
 * @create: 2020-10-01 22:58
 **/
public class ProxyTest {
    public static void main(String[] args) throws Exception {
        proxy();
    }

    public static void proxy() throws Exception {
        //新建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        //设置代理类
        proxyFactory.setSuperclass(Person.class);
        //设置方法过滤，如这里设置只有方法名为hello的才走代理
        proxyFactory.setFilter((m)->m.getName().equals("hello"));

        //创建代理类
        Class<ProxyObject> proxyClass = proxyFactory.createClass();
        //生成代理对象
        ProxyObject proxyObj = proxyClass.newInstance();
        //设置代理处理
        proxyObj.setHandler(new ProxyHandle());
        Person p = (Person)proxyObj;
        p.hello();
    }

    //目标类
    static class Person{
        void hello(){
            System.out.println("hello");
        }
        void helloOne(){
            System.out.println("helloOne");
        }
    }

    //代理类
    public static class ProxyHandle implements MethodHandler {
        //代理实现
        @Override
        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            System.out.println("proxy code");
            return proceed.invoke(self, args);
        }
    }

}
