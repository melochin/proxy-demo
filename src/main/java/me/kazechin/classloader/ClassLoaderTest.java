package me.kazechin.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class ClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {

		URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:F:\\junit-4.11.jar")});

		Class<?> aClass = Class.forName("junit.runner.Version", true, urlClassLoader);

		System.out.println(aClass.getClassLoader());

//		MyClassLoader myClassLoader = new MyClassLoader();
//		Class<?> classLoaderTest1 = myClassLoader.loadClass("E:\\workspace\\proxy-demo\\target\\classes\\me\\kazechin\\classloader\\MyClassLoader.class");
//		Class<?> classLoaderTest2 = myClassLoader.loadClass("E:\\workspace\\proxy-demo\\target\\classes\\me\\kazechin\\classloader\\MyClassLoader.class");
//
//		System.out.println(classLoaderTest1.equals(classLoaderTest2));
//		System.out.println(classLoaderTest1.getClassLoader());
//		System.out.println(classLoaderTest2.getClassLoader());
//
//		System.out.println(System.getProperty("sun.boot.class.path"));
//		System.out.println(System.getProperty("java.ext.dirs"));
//		System.out.println(System.getProperty("java.class.path"));

	}

}
