package me.kazechin.synthetic;

import java.lang.reflect.Method;

public class Foo {

	private Object baz = "test";

	private class Bar {

		public Object hi() {
			return baz;
		}

	}

	static Object access$000(Foo foo) {
		return foo.baz;
	}

	public static void main(String[] args) {
		for(Method method : Foo.class.getDeclaredMethods()) {
			System.out.println(method);
			System.out.println(method.isSynthetic());
		}
	}

}
