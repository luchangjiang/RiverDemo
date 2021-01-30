package com.river.cl;

import com.river.cl.abc.Say;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.ClassLoader.getSystemClassLoader;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-12-06 21:03
 **/
public class PathClassLoaderTest {
    private static final String classPath ="file:C:/River/java/RiverDemo/ClassLoaderDemo/target";
    @Test
    public void test3() throws MalformedURLException, ClassNotFoundException, SecurityException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        URL[] urls = {new URL(classPath)};
        URLPathClassLoader urlPathClassLoader = new URLPathClassLoader(urls, getSystemClassLoader());
        Class say = urlPathClassLoader.findClass("com.river.cl.abc.Say");
        Method method = say.getMethod("say");
        Object instance = say.newInstance();
        method.invoke(instance);

        Say say2 = (Say)instance;
        say2.say();
    }
}
