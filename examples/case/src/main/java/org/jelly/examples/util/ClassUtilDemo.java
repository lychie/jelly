package org.jelly.examples.util;

import java.util.List;

import org.jelly.examples.util.model.Animal;
import org.jelly.examples.util.model.Parrot;
import org.jelly.examples.util.model.Tiger;
import org.jelly.helper.Testing;
import org.jelly.util.ClassUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class ClassUtilDemo {

	/**
	 * 实例
	 */
	@Test
	public void getInstance(){
		Animal animal = ClassUtil.getInstance(Animal.class);
		Testing.printlnObject(animal);
	}

	/**
	 * Class
	 */
	@Test
	public void forName(){
		String name = "org.jelly.examples.util.model.Animal";
		Class<?> animalClass = ClassUtil.forName(name);
		Testing.printlnObject(animalClass);
	}

	/**
	 * 测试实例
	 */
	@Test
	public void isInstanceOfObjectClass(){
		Animal animal = new Animal();
		Tiger tiger = new Tiger();
		Testing.printlnObject(ClassUtil.isInstanceOf(tiger, Tiger.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(tiger, Animal.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(animal, Animal.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(animal, Tiger.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(10086, int.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(10086, Integer.class));
	}

	/**
	 * 测试Class
	 */
	@Test
	public void isInstanceOfClassClass(){
		Testing.printlnObject(ClassUtil.isInstanceOf(Tiger.class, Tiger.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(Tiger.class, Animal.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(Animal.class, Tiger.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(String.class, Object.class));
		Testing.printlnObject(ClassUtil.isInstanceOf(Object.class, String.class));
	}

	/**
	 * 泛型
	 */
	@Test
	public void getSuperclassGenericType(){
		new Parrot();
	}

	/**
	 * 超类
	 */
	@Test
	public void getSuperclasses(){
		List<Class<?>> superClasses = ClassUtil.getSuperclasses(Tiger.class);
		Testing.printlnObject(superClasses);
	}

	/**
	 * 数据类型
	 */
	@Test
	public void getObjectTypes(){
		Object o1 = "";
		Object o2 = 2;
		Object o3 = false;
		Object o4 = "".getBytes();
		Testing.printlnObject(ClassUtil.getObjectTypes(o1, o2, o3, o4));
	}

	/**
	 * 类简单名称
	 */
	@Test
	public void getSimpleClassName(){
		Testing.printlnObject(ClassUtil.getSimpleClassName(Animal.class));
		Testing.printlnObject(ClassUtil.getSimpleClassName(new Animal()));
	}
	
}