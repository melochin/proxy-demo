package me.kazechin.bytebuddy.intercept;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ArrayListProxy extends ArrayList {

	private static Method addMethod;

	@Override
	public boolean add(Object o) {
		return (boolean) Dispatcher.intercept(addMethod, new AddCallable(this, o));
	}

	public boolean addSuper(Object object) {
		return super.add(object);
	}

	static {
		try {
			addMethod = ArrayList.class.getMethod("add", Object.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

}
