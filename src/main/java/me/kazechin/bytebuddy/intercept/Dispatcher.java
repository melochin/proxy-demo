package me.kazechin.bytebuddy.intercept;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Dispatcher {

	public static Object intercept(Method method, Callable callable) {
		System.out.println("intercept");
		try {
			return callable.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
