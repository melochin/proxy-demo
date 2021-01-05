package me.kazechin.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ReType {

	public static void main(String[] args) throws IOException {
		// error
		// Cannot inject already loaded type: class me.kazechin.bytebuddy.Foo
		redefine();
		// TODO rebase 结果不是预期
		rebase();
	}

	private static void rebase() throws IOException {
		new ByteBuddy()
				.rebase(Foo.class)
				.method(named("bar"))
				.intercept(FixedValue.value("rebase"))
				.make()
				.saveIn(new File("target/classes"));
	}

	private static void redefine() throws IOException {
		new ByteBuddy()
				.redefine(Foo.class)
				.method(named("bar"))
				.intercept(FixedValue.value("test"))
				.make()
				.saveIn(new File("target/classes"));
	}



}
