package com.river.invoke;

import com.river.invoke.apple.SmallPineApple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-11-02 14:38
 **/
public class IocDemo {
    public static void main(String[] args) {
        ApplicationContext ac =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        SmallPineApple smallPineapple = (SmallPineApple) ac.getBean("smallpineapple");
        smallPineapple.getInfo(); // [小菠萝的年龄是：21]
    }
}
