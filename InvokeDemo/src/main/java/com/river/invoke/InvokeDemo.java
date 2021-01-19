package com.river.invoke;

import com.river.invoke.apple.SmallPineApple;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-11-02 14:01
 **/
public class InvokeDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class clazz1 = Class.forName("com.river.invoke.apple.SmallPineApple");
        Class clazz2 = SmallPineApple.class;
        SmallPineApple instance = new SmallPineApple();
        Class clazz3 = instance.getClass();
        System.out.println("Class.forName() == SmallPineapple.class:" + (clazz1 == clazz2));
        System.out.println("Class.forName() == instance.getClass():" + (clazz1 == clazz3));
        System.out.println("instance.getClass() == SmallPineapple.class:" + (clazz2 == clazz3));

        instance.getInfo();

        System.out.println("++++++++++++++++++++++++++++++++++");
        Constructor constructor = clazz1.getConstructor(String.class, int.class);
        constructor.setAccessible(true);
        SmallPineApple smallPineapple2 = (SmallPineApple) constructor.newInstance("小菠萝", 21);
        smallPineapple2.getInfo();
        System.out.println("++++++++++++++fields+++++++++++++++++++++");
        getAllFields(clazz2);
        System.out.println("+++++++++++++annotation++++++++++++++++++");
        getAnnotations(clazz3);
        System.out.println("++++++++++++++invoke+++++++++++++++++++++");
        invode(clazz1);
        System.out.println("+++++++++++++accessible++++++++++++++++++");
        accessible(clazz1);
    }

    public static void getAllFields(Class clazz)  {
        // 获取 public 属性，包括继承
        Field[] fields1 = clazz.getFields();
        // 获取所有属性，不包括继承
        Field[] fields2 = clazz.getDeclaredFields();
        // 将所有属性汇总到 set
        Set<Field> allFields = new HashSet<>();
        allFields.addAll(Arrays.asList(fields1));
        allFields.addAll(Arrays.asList(fields2));

        allFields.forEach(field -> {
            System.out.println(field.getName());
        });
    }

    /**
     * 根据运行时传入的全类名路径判断具体的类对象
     */
    public static void getAnnotations(Class clazz) throws NoSuchMethodException {
        Method method = clazz.getMethod("getInfo");
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }
    }

    public static void invode(Class clazz) throws NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        constructor.setAccessible(true);
        SmallPineApple sp = (SmallPineApple) constructor.newInstance("小菠萝", 21);
        Method method = clazz.getMethod("getInfo");
        if (method != null) {
            method.invoke(sp, null);
        }
    }

    public static void accessible(Class clazz) throws NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException, NoSuchFieldException {
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        constructor.setAccessible(true);
        SmallPineApple sp = (SmallPineApple) constructor.newInstance("小菠萝", 21);
        Field weight = clazz.getDeclaredField("weight");
        weight.setAccessible(true);
        System.out.println("窥觑到小菠萝的体重是：" + weight.get(sp));
    }
}
