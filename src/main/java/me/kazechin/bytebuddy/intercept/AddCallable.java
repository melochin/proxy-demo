package me.kazechin.bytebuddy.intercept;

import java.util.concurrent.Callable;

public class AddCallable implements Callable {

	private ArrayListProxy proxy;

	private Object argument1;

	public AddCallable(ArrayListProxy proxy, Object o) {
		this.proxy = proxy;
		this.argument1 = o;
	}

	@Override
	public Object call() throws Exception {
		return this.proxy.addSuper(argument1);
	}

}
