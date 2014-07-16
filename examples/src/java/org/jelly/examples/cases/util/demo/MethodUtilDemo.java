package org.jelly.examples.cases.util.demo;

import java.lang.reflect.Method;
import java.util.List;
import org.jelly.examples.cases.util.demo.model.Bar;
import org.jelly.helper.Testing;
import org.jelly.util.MethodUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class MethodUtilDemo {

	/**
	 * 调用 setters
	 */
	@Test
	public void invokeSetterMethod(){
		MethodUtil.invokeSetterMethod(Bar.class, "var", 1.1);
		Bar bar = new Bar("Hi there");
		MethodUtil.invokeSetterMethod(bar, "baz", 1);
		Testing.printlnObject(bar);
	}

	/**
	 * 调用 getters
	 */
	@Test
	public void invokeGetterMethod(){
		double var = MethodUtil.invokeGetterMethod(Bar.class, "var");
		Bar bar = new Bar("Hi there");
		int baz = MethodUtil.invokeGetterMethod(bar, "baz");
		Testing.printlnObject(var);
		Testing.printlnObject(baz);
	}

	/**
	 * 演示错误的用法
	 * 调用构造方法
	 */
	@Test
	public void badInvokeConstructor(){
		
		// success
		Bar bar1 = MethodUtil.invokeConstructor(Bar.class, "Hi there");
		Testing.printlnObject(bar1);
		
		// fail
		// 参数类型列表不能有基本数据类型
		Bar bar2 = MethodUtil.invokeConstructor(Bar.class, 13800, "Hi there");
		Testing.printlnObject(bar2);
		
	}

	/**
	 * 正确的做法
	 * 调用构造方法
	 */
	@Test
	public void corretInvokeConstructor(){
		Object[] args = {13800, "Hi there"};
		// 含有基本数据类型要明确指明参数类型的列表
		Class<?>[] types = {int.class, String.class};
		Bar bar = MethodUtil.invokeConstructor(Bar.class, args, types);
		Testing.printlnObject(bar);
	}

	/**
	 * 演示错误的用法
	 * 调用方法
	 */
	@Test
	public void invokeMethod_object_type(){
		
		double var = MethodUtil.invokeMethod(Bar.class, "getVar");
		Testing.printlnObject(var);
		
		Bar bar = new Bar("Hi there");
		// success
		MethodUtil.invokeMethod(bar, "setQux", "one piece");
		Testing.printlnObject(bar);
		
		// fail, 同上, 不能含基本数据类型
		MethodUtil.invokeMethod(bar, "setBaz", 10086);
		Testing.printlnObject(bar);
	}

	/**
	 * 正确的做法
	 * 调用方法
	 */
	@Test
	public void invokeMethod_defined_type(){
		Bar bar = new Bar("Hi there");
		Object[] args = {10086};
		// 含有基本数据类型要明确指明参数类型的列表
		Class<?>[] types = {int.class};
		MethodUtil.invokeMethod(bar, "setBaz", args, types);
		Testing.printlnObject(bar);
	}

	/**
	 * 获取可访问的方法对象
	 */
	@Test
	public void getAccessibleMethod(){
		Method setVarMethod = MethodUtil.getAccessibleMethod(Bar.class, "setVar", double.class);
		Method setBazMethod2 = MethodUtil.getAccessibleMethod(Bar.class, "setBaz", int.class);
		Testing.printlnObject(setVarMethod);
		Testing.printlnObject(setBazMethod2);
	}

	/**
	 * 类声明的方法对象列表
	 */
	@Test
	public void getDeclaredMethods(){
		List<Method> methods = MethodUtil.getDeclaredMethods(Bar.class);
		Testing.printlnObject(methods);
	}

	/**
	 * 类声明的方法名称列表
	 */
	@Test
	public void getDeclaredMethodNames(){
		List<String> methodNames = MethodUtil.getDeclaredMethodNames(Bar.class);
		Testing.printlnObject(methodNames);
	}

	/**
	 * 类以及超类所有的方法对象列表
	 */
	@Test
	public void getAllMethods(){
		List<Method> methods = MethodUtil.getAllMethods(Bar.class);
		Testing.printlnObject(methods);
	}

	/**
	 * 类以及超类所有的方法名称列表
	 */
	@Test
	public void getAllMethodNames(){
		List<String> methodNames = MethodUtil.getAllMethodNames(Bar.class);
		Testing.printlnObject(methodNames);
	}
}