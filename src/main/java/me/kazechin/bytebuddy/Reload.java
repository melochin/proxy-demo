package me.kazechin.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Reload {

	public static void main(String[] args) {
		ByteBuddyAgent.install();
		Foo foo = new Foo();

		DynamicType.Unloaded<Foo> unloaded = new ByteBuddy()
				.redefine(Foo.class)
				.method(named("bar"))
				.intercept(FixedValue.value("test"))
				.make();

		unloaded.load(Reload.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

		System.out.println(foo.bar());
	}
}
