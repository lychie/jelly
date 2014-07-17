package org.jelly.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.jelly.exception.ExecutetimeException;
/**
 * 与反射相关的 Class 常用的操作的工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class ClassUtil {

	private ClassUtil(){}
	
	/**
	 * <des> 获取参数 Class 类型的一个实例 </des>
	 * @param clazz Class
	 * @return Class 类型的一个新的实例
	 * @since 1.0.0
	 */
	public static <E> E getInstance(Class<E> clazz){
		try {
			return clazz.newInstance();
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 获取参数指定的全类名表示的一个 Class 类型的实例 </des>
	 * @param className 全类名
	 * @return 全类名表示的一个 Class 类型的实例
	 * @since 1.0.0
	 */
	public static Class<?> forName(String className){
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 判定测试实例是否是目标类型所表示的类(包括子类)或接口的一个实例。
	 * 特别的, 如果目标类型是一个表示基本数据类型的 Class, 则永远返回false </des>
	 * @param testInstance 测试实例
	 * @param targetClass  目标类型
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean isInstanceOf(Object testInstance, Class<?> targetClass){
		if(testInstance instanceof Class){
			return isInstanceOf((Class<?>)testInstance, targetClass);
		}
		return targetClass.isInstance(testInstance);
	}
	
	/**
	 * <des> 判定目标类型所表示的类或接口是否与测试类型所表示的类或接口相同, 或是否是其超类或超接口 </des>
	 * @param testClass   测试类型
	 * @param targetClass 目标类型
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean isInstanceOf(Class<?> testClass, Class<?> targetClass){
		return targetClass.isAssignableFrom(testClass);
	}
	
	/**
	 * <des> 获取参数指定的类的父类列表 </des>
	 * @param clazz Class
	 * @return 父类 Class 集合
	 * @since 1.0.0
	 */
	public static List<Class<?>> getSuperclasses(Class<?> clazz){
		List<Class<?>> classes = new ArrayList<Class<?>>();
		while((clazz = clazz.getSuperclass()) != null){
			classes.add(clazz);
		}
		return classes.size() == 0 ? null : classes;
	}
	
	/**
	 * <des> 获取泛型的实际参数类型 </des>
	 * @param clazz Class
	 * @return 泛型列表的索引值为0的参数类型
	 * @see org.jelly.util.ClassUtil#getSuperclassGenericType(Class, int)
	 * @since 1.0.0
	 */
	public static Class<?> getSuperclassGenericType(Class<?> clazz){
		return getSuperclassGenericType(clazz, 0);
	}
	
	/**
	 * <des> 获取泛型的实际参数类型 </des>
	 * @param clazz Class
	 * @param index 泛型类型列表的索引值
	 * @return 泛型列表的索引值为 index 的参数类型
	 * @since 1.0.0
	 */
	public static Class<?> getSuperclassGenericType(Class<?> clazz, int index){
		try {
			Type type = clazz.getGenericSuperclass();
	        Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
	        return (Class<?>) parameterizedType[index];
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 获取参数值的实际类型 </des>
	 * @param items 可变参数项
	 * @return 参数的实际类型集合
	 * @since 1.0.0
	 */
	public static Class<?>[] getObjectTypes(Object... items){
		if(ArrayUtil.isEmpty(items)){
			return null;
		}
		Class<?>[] classes = new Class<?>[items.length];
		for(int i = 0; i < items.length; i++){
			classes[i] = items[i].getClass();
		}
		return classes;
	}
	
	/**
	 * <des> 获取全类名 </des>
	 * @param obj Class 或 Class 类型的一个具体对象
	 * @return 字符串表示的全类名
	 * @since 1.0.0
	 */
	public static String getClassName(Object obj){
		if(obj == null){
			return null;
		}
		if(obj instanceof Class){
			return ((Class<?>)obj).getName();
		}
		return obj.getClass().getName();
	}
	
	/**
	 * <des> 获取短类名 </des>
	 * @param obj Class 或 Class 类型的一个具体对象
	 * @return 字符串表示的短类名
	 * @since 1.0.0
	 */
	public static String getSimpleClassName(Object obj){
		if(obj == null){
			return null;
		}
		if(obj instanceof Class){
			return ((Class<?>)obj).getSimpleName();
		}
		return obj.getClass().getSimpleName();
	}
	
}