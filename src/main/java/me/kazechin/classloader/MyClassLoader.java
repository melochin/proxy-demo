package me.kazechin.classloader;

import java.io.*;
import java.nio.ByteBuffer;

public class MyClassLoader extends ClassLoader{

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		try {
			FileInputStream inputStream = new FileInputStream(name);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			int read = -1;
			while ((read = inputStream.read()) != -1) {
				outputStream.write(read);
			}

			byte[] bytes = outputStream.toByteArray();

			return defineClass("me.kazechin.classloader.MyClassLoader", bytes, 0, bytes.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}



}
