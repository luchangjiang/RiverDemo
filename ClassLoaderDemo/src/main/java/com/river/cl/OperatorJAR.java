package com.river.cl;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

import java.util.jar.*;


public class OperatorJAR { // 操作JAR文件的类
    public static void readJARList(String fileName) throws IOException {// 显示JAR文件内容列表
        JarFile jarFile = new JarFile(fileName); // 创建JAR文件对象
        Enumeration en = jarFile.entries(); // 枚举获得JAR文件内的实体,即相对路径
        System.out.println("文件名\t文件大小\t压缩后的大小");
        while (en.hasMoreElements()) { // 遍历显示JAR文件中的内容信息
            process(en.nextElement()); // 调用方法显示内容
        }
    }

    private static void process(Object obj) {// 显示对象信息
        JarEntry entry = (JarEntry) obj;// 对象转化成Jar对象
        String name = entry.getName();// 文件名称
        long size = entry.getSize();// 文件大小
        long compressedSize = entry.getCompressedSize();// 压缩后的大小
        System.out.println(name + "\t" + size + "\t" + compressedSize);
    }

    public static void readJARFile(String jarFileName, String packageName)
            throws IOException {// 读取JAR文件中的单个文件信息
        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<>();
        // 是否循环迭代
        boolean recursive = true;
        try {
            // 获取jar
            JarFile jarFile = new JarFile(jarFileName);// 根据传入JAR文件创建JAR文件对象
            // 从此jar包 得到一个枚举类
            Enumeration<JarEntry> entries = jarFile.entries();
            // 同样的进行循环迭代
            while (entries.hasMoreElements()) {
                // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                // 如果是以/开头的
                if (name.charAt(0) == '/') {
                    // 获取后面的字符串
                    name = name.substring(1);
                }
                // 如果前半部分和定义的包名相同
                int idx = name.lastIndexOf('/');
                // 如果以"/"结尾 是一个包
                if (idx != -1) {
                    // 获取包名 把"/"替换成"."
                    packageName = name.substring(0, idx).replace('/', '.');
                }
                // 如果可以迭代下去 并且是一个包
                if ((idx != -1) || recursive) {
                    // 如果是一个.class文件 而且不是目录
                    if (name.endsWith(".class") && !entry.isDirectory()) {
                        // 去掉后面的".class" 获取真正的类名
                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                        try {
                            // 添加到classes
                            classes.add(Class.forName(packageName + '.' + className));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            // log.error("在扫描用户定义视图时从jar包获取文件出错");
            e.printStackTrace();
        }
    }

    public static void readFile(InputStream input) throws IOException {// 读出JAR文件中单个文件信息
        InputStreamReader in = new InputStreamReader(input);// 创建输入读流
        BufferedReader reader = new BufferedReader(in);// 创建缓冲读流
        String line;
        while ((line = reader.readLine()) != null) {// 循环显示文件内容
            System.out.println(line);
        }
        reader.close();// 关闭缓冲读流
    }

    public static void main(String args[]) throws IOException {// java程序主入口处

        String fileName = "C:\\tmp\\pigx-auth.jar";
        String basePackage = "com.pig4cloud";

        File file = new File(fileName);
        ClassLoader classLoader = new URLClassLoader(new URL[] {file.getCanonicalFile().toURI().toURL()},
                this.getClass().getClassLoader(), basePackage);
        Thread.currentThread().setContextClassLoader(classLoader);
        readJARFile(fileName, basePackage);// 获得键盘输入的值
    }

}