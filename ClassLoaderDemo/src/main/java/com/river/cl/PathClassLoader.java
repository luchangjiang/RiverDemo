package com.river.cl;

import java.io.*;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2020-12-06 17:20
 **/
public class PathClassLoader extends ClassLoader {

    private String classPath;

    public PathClassLoader(String classPath) {

        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String arg0) throws ClassNotFoundException {

        byte[] data = getData(arg0);
        if (data == null) {

            throw new ClassNotFoundException();
        }
        return defineClass(arg0, data, 0, data.length);
    }

    private byte[] getData(String className) {

        String path = classPath + File.separatorChar + className.replace(".", File.separator) + ".class";
        try {
            InputStream is = new FileInputStream(path);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = is.read(buffer)) != -1) {

                out.write(buffer, 0, count);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
