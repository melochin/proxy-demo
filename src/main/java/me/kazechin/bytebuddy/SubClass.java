package me.kazechin.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

import java.io.File;
import java.io.IOException;

public class SubClass {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		createSubClass();
		createSubClassWitNameAndLoad();
		createSubClassWithCustomNameStrategy();
	}

	private static void createSubClassWithCustomNameStrategy() throws ClassNotFoundException {
		new ByteBuddy()
				.with(new NamingStrategy.AbstractBase() {

					@Override
					protected String name(TypeDescription typeDescription) {
						return "me.kazechin." + typeDescription.getSimpleName();
					}
				})
				.subclass(Object.class)
				.make()
				.load(SubClass.class.getClassLoader());

		Class.forName("me.kazechin.Object");
	}

	private static void createSubClassWitNameAndLoad() throws ClassNotFoundException {
		new ByteBuddy()
				.subclass(Object.class)
				.name("me.kazechin.Type")
				.make()
				.load(SubClass.class.getClassLoader());

		Class clazz = Class.forName("me.kazechin.Type");
	}

	private static void createSubClass() throws IOException {
		// 默认名字约定
		// 1. 相同的package全名称$ByteBuddy$随机串
		// 2. java.lang下的类，package按net.bytebuddy.renamed.java.lang
		DynamicType.Unloaded<Object> unloaded = new ByteBuddy()
				.subclass(Object.class)
				.make();
		unloaded.saveIn(new File("target"));
	}
}
