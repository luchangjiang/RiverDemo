package com.river.cl;

import java.net.URL;
import java.net.URLClassLoader;

public class URLPathClassLoader extends URLClassLoader{

	private String packageName = "com.river.";
	
	public URLPathClassLoader(URL[] classPath, ClassLoader parent){
		
		super(classPath, parent);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		Class<?> aClass = findLoadedClass(name);
		if(aClass != null){
			
			return aClass;
		}
		if(!packageName.startsWith(name)){
			
			return super.loadClass(name);
		}
		return null;
	}
}
