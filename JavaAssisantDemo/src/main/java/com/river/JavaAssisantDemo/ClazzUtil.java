package com.river.JavaAssisantDemo;

import javassist.*;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-10-01 22:53
 **/
public class ClazzUtil {
    public static void genClass() throws Exception {
        //生成按默认路径(jvm的类搜索路径)加载类的ClassPool实例
        ClassPool classPool = ClassPool.getDefault();

        //添加类加载路径一：本地路径直接添加
        classPool.insertClassPath("com.dragon.study");

        //添加类路径类别二：使用http地址的类加载路径
//        ClassPath classPath = new URLClassPath("www.test.host", 80, "directory", "packageName");
//        classPool.insertClassPath(classPath);

        //直接新建类(含包名)
        CtClass ctClass = classPool.makeClass("com.dragon.Stu");

        //添加实现接口
        ctClass.setInterfaces(new CtClass[]{classPool.makeInterface("java.io.Serializable")});

        //添加继承类
//        ctClass.setSuperclass(classPool.makeClass("java.lang.Object"));
//        ctClass.setSuperclass(classPool.makeClass("com.dragon.Person"));
        ctClass.setSuperclass(classPool.makeClass("com.dragon.study.Person"));

        //设置类修饰符
        ctClass.setModifiers(Modifier.PUBLIC);

        //添加字段
        CtField ctField = new CtField(CtClass.intType, "id", ctClass);
        ctField.setModifiers(Modifier.PUBLIC);
        ctClass.addField(ctField);

        //添加构造器
        CtConstructor oneCtConstructor = CtNewConstructor.make("public stu(){}", ctClass);
        CtConstructor ctConstructor = CtNewConstructor.make("public stu(int id){this.id=id;}", ctClass);
        ctClass.addConstructor(oneCtConstructor);
        ctClass.addConstructor(ctConstructor);

        //添加方法
        CtMethod ctMethod = CtNewMethod.make("public String hello(String name){return \"hello \"+name;}", ctClass);
        ctClass.addMethod(ctMethod);

        //writeFile()、toClass()、toBytecode()，CtClass将被冻结，不能在修改，但调用defrost()方法解冻，再次修改

        //类文件(Stu.class)写入指定目录中
        ctClass.writeFile("/xx/");

        //CtClass转为Class,使用当前线程的类加载器加载
        Class<?>  clazz = ctClass.toClass();
        //也可指定类加载器
        clazz = ctClass.toClass(Thread.currentThread().getContextClassLoader());

        //CtClass转为type数组
        byte[]  ctClassByte = ctClass.toBytecode();

        //生成对象
        Object obj = clazz.newInstance();

        //从ClassPool中删除类实例
        ctClass.detach();
    }

    //修改类字节码，包含方法和字段
    public static void modifyClass() throws Exception {
        //生成按默认路径(jvm的类搜索路径)加载类的ClassPool实例
        ClassPool classPool = ClassPool.getDefault();
        //Loader用于加载修改过的类
        Loader loader = new Loader(classPool);

        //获取存在的类
        CtClass ctClass = classPool.getCtClass("com.dragon.study.Teacher");

        //获取存在的方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("hello");
        //在方法开头插入代码
        ctMethod.insertBefore("System.out.println(\"insert code at begin\");");
        //在方法结尾插入代码
        ctMethod.insertAfter("System.out.println(\"insert code at end\");");
        //在方法指定行插入代码
        ctMethod.insertAt(2, "System.out.println(\"incode at 2 line\");");

        //添加字段
        CtField ctField = new CtField(CtClass.intType, "id", ctClass);
        ctField.setModifiers(Modifier.PUBLIC);
        ctClass.addField(ctField);

        //将class文件写入本地磁盘
        ctClass.writeFile("/xx");

        //加载修改过的类
        Class clazz = loader.loadClass("com.dragon.study.study20190618.proxy.javasistStudy.Teacher");
        //使用修改过的类创建对象，此时新对象里有修改过后的字段和方法
        Object obj = clazz.newInstance();
    }

}
