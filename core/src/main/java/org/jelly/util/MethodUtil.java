package org.jelly.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jelly.exception.ExecutetimeException;
/**
 * 反射相关的方法常用的操作工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class MethodUtil {

	private MethodUtil(){}
	
	/**
	 * <des> 调用对象的 setter 成员方法(或类的类方法) </des>
	 * @param object 具体的对象(或类)
	 * @param fieldName 对象成员方法名称(或类的类方法名称)
	 * @param value 期望设置的值
	 * @since 1.0.0
	 */
	public static void invokeSetterMethod(Object object, String fieldName, Object value) {
		String method = "set" + StringUtil.toFirstLetterUpperCase(fieldName);
		Object[] args = {value};
		Class<?>[] types = {FieldUtil.getFieldType(object, fieldName)};
		invokeMethod(object, method, args, types);
	}
	
	/**
	 * <des> 调用对象的 getter 成员方法(或类的类方法) </des>
	 * @param object 具体的对象(或类)
	 * @param fieldName 对象的成员方法名称(或类的类方法名称)
	 * @return 获取得到的值
	 * @since 1.0.0
	 */
	public static <E> E invokeGetterMethod(Object object, String fieldName) {
		String method = "get" + StringUtil.toFirstLetterUpperCase(fieldName);
		return invokeMethod(object, method, null, null);
	}
	
	/**
	 * <des> 调用构造方法得到一个实例, 特别注意, 调该方法要求构造方法参数必须不能有基本数据类型存在 </des>
	 * @param clazz Class
	 * @param argValues 构造方法的参数值列表
	 * @return Class 的一个具体实例
	 * @see org.jelly.util.MethodUtil#invokeConstructor(Class, Object[], Class[])
	 * @since 1.0.0
	 */
	public static <E> E invokeConstructor(Class<E> clazz, Object... argValues) {
		return invokeConstructor(clazz, argValues, ClassUtil.getObjectTypes(argValues));
	}
	
	/**
	 * <des> 调用构造方法得到一个实例  </des>
	 * @param clazz Class
	 * @param argValues 构造方法的参数值列表
	 * @param argTypes 构造方法的参数类型列表
	 * @return Class 的一个实例
	 * @since 1.0.0
	 */
	public static <E> E invokeConstructor(Class<E> clazz, Object[] argValues, Class<?>[] argTypes) {
		try {
			Constructor<E> constructor = clazz.getDeclaredConstructor(argTypes);
			constructor.setAccessible(true);
			return constructor.newInstance(argValues);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 调用对象的成员方法(或类的类方法), 特别注意, 
	 * 调该方法要求被调方法参数必须不能有基本数据类型存在 </des>
	 * @param object 具体的对象(或类)
	 * @param methodName 对象的成员方法名称(或类的类方法名称)
	 * @param argValues 方法的参数值列表
	 * @return 方法调用的结果
	 * @see org.jelly.util.MethodUtil#invokeMethod(Object, String, Object[], Class[])
	 * @since 1.0.0
	 */
	public static <E> E invokeMethod(Object object, String methodName, Object... argValues) {
		return invokeMethod(object, methodName, argValues, ClassUtil.getObjectTypes(argValues));
	}
	
	/**
	 * <des> 调用对象的成员方法(或类的类方法) </des>
	 * @param object 具体的对象(或类)
	 * @param methodName 对象的成员方法名称(或类的类方法名称)
	 * @param argValues 方法的参数值列表
	 * @param argTypes 方法的参数类型列表
	 * @return 方法调用的结果
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public static <E> E invokeMethod(Object object, String methodName, Object[] argValues, Class<?>[] argTypes) {
		try {
			return (E) getAccessibleMethod(object, methodName, argTypes).invoke(object, argValues);
		} catch (ClassCastException e) { // must catch ClassCastException
			throw e;
		}  catch (NullPointerException e) { // must catch NullPointerException
			String classname = ClassUtil.getSimpleClassName(object);
			String message;
			if(ArrayUtil.isEmpty(argTypes)){
				message = StringUtil.parse("类 ? 中找不到签名为 ?() 的方法", classname, methodName);
			}else{
				String types = ArrayUtil.toSimpleString(argTypes);
				message = StringUtil.parse("类 ? 中找不到签名为 ?(?) 的方法", classname, methodName, types);
			}
			throw new ExecutetimeException(new NoSuchMethodException(message));
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 获取可访问的对象的成员方法(或类的类方法)的方法对象 </des>
	 * @param object 具体的对象(或类)
	 * @param methodName 对象的成员方法名称(或类的类方法名称)
	 * @param types 方法的参数类型列表
	 * @return 可访问的方法对象
	 * @since 1.0.0
	 */
	public static Method getAccessibleMethod(Object object, String methodName, Class<?>... types){
		Class<?> entityClass = object instanceof Class ? (Class<?>) object : object.getClass();
		while(entityClass != null){
			try {
				Method target = entityClass.getDeclaredMethod(methodName, types);
				target.setAccessible(true);
				return target;
			} catch (Throwable e) { /* ignore the thrown exception */ }
			/* go to the super class */
			entityClass = entityClass.getSuperclass();
		}
		return null;
	}
	
	/**
	 * <des> 获取类声明的方法的列表集合(不包含父类) </des>
	 * @param clazz Class
	 * @return 类声明的方法的列表集合
	 * @since 1.0.0
	 */
	public static List<Method> getDeclaredMethods(Class<?> clazz){
		if(clazz == null) return null;
		Method[] methods = clazz.getDeclaredMethods();
		if(methods.length == 0){
			return null;
		}
		for(Method method : methods){
			method.setAccessible(true);
		}
		return Arrays.asList(methods);
	}
	
	/**
	 * <des> 获取类声明的方法的名称的列表集合(不包含父类) </des>
	 * @param clazz Class
	 * @return 类声明的方法的名称的列表集合
	 * @since 1.0.0
	 */
	public static List<String> getDeclaredMethodNames(Class<?> clazz){
		if(clazz == null) return null;
		Method[] methods = clazz.getDeclaredMethods();
		if(methods.length == 0){
			return null;
		}
		List<String> methodNames = new ArrayList<String>(methods.length);
		for(Method method : methods){
			methodNames.add(method.getName());
		}
		return methodNames;
	}
	
	/**
	 * <des> 获取类声明的方法的列表集合(包含父类) </des>
	 * @param clazz Class
	 * @return 类声明的方法的列表集合
	 * @since 1.0.0
	 */
	public static List<Method> getAllMethods(Class<?> clazz){
		if(clazz == null) return null;
		List<Method> methods = new ArrayList<Method>();
		List<Method> thisClassMethod;
		while(clazz != null){
			thisClassMethod = getDeclaredMethods(clazz);
			if(thisClassMethod != null){
				methods.addAll(thisClassMethod);
			}
			clazz = clazz.getSuperclass();
		}
		return methods.size() == 0 ? null : methods;
	}
	
	/**
	 * <des> 获取类声明的方法的名称的列表集合(包含父类) </des>
	 * @param clazz Class
	 * @return 类声明的方法的名称的列表集合
	 * @since 1.0.0
	 */
	public static List<String> getAllMethodNames(Class<?> clazz){
		if(clazz == null) return null;
		List<Method> methods = getAllMethods(clazz);
		List<String> methodNames = new ArrayList<String>(methods.size());
		for(Method method : methods){
			methodNames.add(method.getName());
		}
		return methodNames;
	}
	
}