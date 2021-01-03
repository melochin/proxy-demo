package me.kazechin;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Interceptor {

	@RuntimeType
	public static Object intercept(@Origin Method method,
								   @SuperCall Callable call) throws Exception {
		System.out.println("this is byte buddy proxy");
		return call.call();
	}
}
