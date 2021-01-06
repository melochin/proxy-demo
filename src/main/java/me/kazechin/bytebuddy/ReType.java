package me.kazechin.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ReType {

	public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
		redefine();
		// rebase 结果不是预期
		// TODO call origin method
		rebase();
	}

	private static void rebase() throws IOException {
		new ByteBuddy()
				.rebase(Foo.class)
				.name("me.kazechin.RebaseFoo")
				.method(named("bar"))
				.intercept(FixedValue.value("rebase"))
				.make()
				.saveIn(new File("target/classes"));
	}

	private static void redefine() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

		System.out.println(new Foo().bar());
		DynamicType.Unloaded<Foo> unloaded = new ByteBuddy()
				.redefine(Foo.class)
				.method(named("bar"))
				.intercept(FixedValue.value("test"))
				.make();
//		unloaded.saveIn(new File("target/classes"));
		// Cannot inject already loaded type: class me.kazechin.bytebuddy.Foo
		// change ClassLoadingStrategy to CHILD_FIRST
		Class<?> redefineFoo = unloaded.load(ReType.class.getClassLoader(), ClassLoadingStrategy.Default.CHILD_FIRST)
				.getLoaded();

		Class<?> clzz = ClassLoader.getSystemClassLoader().loadClass("me.kazechin.bytebuddy.Foo");
		System.out.println(((Foo)clzz.newInstance()).bar());
		//
		System.out.println(redefineFoo.getMethod("bar").invoke(redefineFoo.newInstance()));
		//Exception in thread "main" java.lang.ClassCastException: me.kazechin.bytebuddy.Foo cannot be cast to me.kazechin.bytebuddy.Foo
//		System.out.println(((Foo)(redefineFoo).newInstance()).bar());
	}


}
