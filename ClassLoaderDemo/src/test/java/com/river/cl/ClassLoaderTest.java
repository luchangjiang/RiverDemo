package com.river.cl;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-12-06 17:23
 **/
public class ClassLoaderTest {

    private static final String classPath ="C:/River/java/RiverDemo/ClassLoaderDemo/target/classes";

    /**
     * 使用同一个ClassLoader重复加载一个类会报错
      * @author：涂有
      * @date 2017年3月3日 下午1:38:05
     */
    @Test
    public void test1() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {

        PathClassLoader loader = new PathClassLoader(classPath + "/com/river/cl/abc");

        //第一次加载
        Class say = loader.findClass("Say");
        Method method = say.getMethod("say");
        Object instance = say.newInstance();
        method.invoke(instance);

        //第二次加载，报错
        //java.lang.LinkageError: loader (instance of  PathClassLoader): attempted  duplicate class definition for name: "Say"
        Class say2 = loader.findClass("Say");
        Method method2 = say2.getMethod("say");
        Object instance2 = say2.newInstance();
        method2.invoke(instance2);
    }

    /**
     * 重新new一个ClassLoader加载同一个类不会报错
      * @author：涂有
      * @date 2017年3月3日 下午1:38:53
     */
    @Test
    public void test2() throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //第一次加载
        PathClassLoader loader = new PathClassLoader(classPath);
        Class say = loader.findClass("com.river.cl.abc.Say");
        Method method = say.getMethod("say");
        Object instance = say.newInstance();
        method.invoke(instance);

        //第二次加载，不会报错
        PathClassLoader loader2 = new PathClassLoader(classPath);
        Class say2 = loader2.findClass("com.river.cl.abc.Say");
        Method method2 = say2.getMethod("say");
        Object instance2 = say2.newInstance();
        method2.invoke(instance2);

        System.out.println(instance == instance2);
    }
}
