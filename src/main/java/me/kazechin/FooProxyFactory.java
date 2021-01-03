package me.kazechin;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FooProxyFactory {

	public static Foo getJdkProxy() {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		final DefaultFoo defaultFoo = new DefaultFoo();
		Foo foo = (Foo) Proxy.newProxyInstance(FooProxyFactory.class.getClassLoader(), new Class[]{Foo.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("this is jdk proxy");
				return method.invoke(defaultFoo, args);
			}
		});

		return foo;
	}

	public static Foo getCglibProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setInterfaces(new Class[]{Foo.class});
		enhancer.setSuperclass(DefaultFoo.class);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
				System.out.println("this is cglib proxy");
				return methodProxy.invokeSuper(o, objects);
			}
		});

		return (Foo) enhancer.create();
	}

	public static Foo getByteBuddyProxy() throws IllegalAccessException, InstantiationException, IOException {
		DynamicType.Unloaded<DefaultFoo> make = new ByteBuddy().subclass(DefaultFoo.class)
				.method(ElementMatchers.any())
				.intercept(MethodDelegation.to(Interceptor.class))
				.make();
		make.saveIn(new File("target/classes"));
		return make.load(FooProxyFactory.class.getClassLoader())
		.getLoaded()
		.newInstance();
	}


	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
		getJdkProxy().bar();
		getCglibProxy().bar();
		getByteBuddyProxy().bar();
	}
}
