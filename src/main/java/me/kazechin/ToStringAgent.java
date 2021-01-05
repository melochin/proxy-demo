package me.kazechin;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class ToStringAgent {

	static class Person {

		@Override
		public String toString() {
			return super.toString();
		}

	}

	public static void premain(String arguments, Instrumentation instrumentation) {
		new AgentBuilder.Default()
				.type(isAnnotatedWith(ToString.class))
				.transform(new AgentBuilder.Transformer() {
					@Override
					public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
						return builder.method(named("toString"))
								.intercept(FixedValue.value("toString"));
					}
				}).installOn(instrumentation);

	}

	public static void main(String[] args) {
		Person person = new Person();
		System.out.println(person.toString());
	}

}
