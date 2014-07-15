package org.jelly.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jelly.exception.ExecutetimeException;
/**
 * 反射相关的字段域的常用的操作的工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class FieldUtil {

	private FieldUtil(){}
	
	/**
	 * <des> 设置对象成员属性(或类属性)字段的值 </des>
	 * @param object 具体的对象(或类)
	 * @param fieldName 成员属性名称(或类属性名称)
	 * @param value 期望设置的值
	 * @since 1.0.0
	 */
	public static void setFieldValue(Object object, String fieldName, Object value) {
		Field field = null;
		try {
			field = getAccessibleField(object, fieldName);
			field.set(object, value);
		} catch (Throwable e) {
			if(field != null){  // must ensure
				throw new ExecutetimeException(e);
			}
			throw generateFieldNotFoundException(object, fieldName);
		}
	}
	
	/**
	 * <des> 获取对象成员属性(或类属性)字段的值 </des>
	 * @param object 具体的对象(或类)
	 * @param fieldName 成员属性名称(或类属性名称)
	 * @return 获取得到的值
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public static <E> E getFieldValue(Object object, String fieldName) {
		try {
			return (E) getAccessibleField(object, fieldName).get(object);
		} catch (ClassCastException e) {  // must catch ClassCastException first
			throw e;
		} catch (Throwable e) {
			throw generateFieldNotFoundException(object, fieldName);
		}
	}
	
	/**
	 * <des> 获取可访问的对象的成员属性(或类的类属性)字段对象 </des>
	 * @param object 具体的对象(或类)
	 * @param fieldName 对象的成员属性名称(或类的类属性名称)
	 * @return 可访问的字段对象
	 * @since 1.0.0
	 */
	public static Field getAccessibleField(Object object, String fieldName){
		Class<?> entityClass = object instanceof Class ? (Class<?>) object : object.getClass();
		while(entityClass != null){
			try {
				Field field = entityClass.getDeclaredField(fieldName);
				field.setAccessible(true);
				return field;
			} catch (Throwable e) { /* ignore the thrown exception */ }
			/* go to the super class */
			entityClass = entityClass.getSuperclass();
		}
		return null;
	}
	
	/**
	 * <des> 获取对象成员属性(或类的类属性)字段的类型 </des>
	 * @param object 具体的对象(或类)
	 * @param fieldName 对象的成员属性名称(或类的类属性名称)
	 * @return 字段的类型
	 * @since 1.0.0
	 */
	public static Class<?> getFieldType(Object object, String fieldName) {
		try {
			return getAccessibleField(object, fieldName).getType();
		} catch (NullPointerException e) { // only NullPointerException could be thrown
			throw generateFieldNotFoundException(object, fieldName);
		}
	}
	
	/**
	 * <des> 获取类声明的字段类型的列表集合(不包含父类) </des>
	 * @param clazz Class
	 * @return 类声明的字段类型的列表集合
	 * @see org.jelly.util.FieldUtil#getAllFields(Class)
	 * @since 1.0.0
	 */
	public static List<Field> getDeclaredFields(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		if(fields.length == 0){
			return null;
		}
		for(Field field : fields){
			field.setAccessible(true);
		}
		return Arrays.asList(fields);
	}
	
	/**
	 * <des> 获取类声明的字段的名称的列表集合(不包含父类) </des>
	 * @param clazz Class
	 * @return 类声明的字段的名称的列表集合
	 * @see org.jelly.util.FieldUtil#getAllFieldNames(Class)
	 * @since 1.0.0
	 */
	public static List<String> getDeclaredFieldNames(Class<?> clazz){
		Field[] fields = clazz.getDeclaredFields();
		if(fields.length == 0){
			return null;
		}
		List<String> fieldNames = new ArrayList<String>(fields.length);
		for(Field field : fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}
	
	/**
	 * <des> 获取类声明的字段类型的列表集合(包含父类) </des>
	 * @param clazz Class
	 * @return 类声明的字段类型的列表集合
	 * @since 1.0.0
	 */
	public static List<Field> getAllFields(Class<?> clazz){
		if(clazz == null){
			throw new ExecutetimeException("clazz must not be null");
		}
		List<Field> fields = new ArrayList<Field>();
		List<Field> thisClassFields;
		while(clazz != null){
			thisClassFields = getDeclaredFields(clazz);
			if(thisClassFields != null){
				fields.addAll(thisClassFields);
			}
			clazz = clazz.getSuperclass();
		}
		return fields.size() == 0 ? null : fields;
	}
	
	/**
	 * <des> 获取类声明的字段的名称的列表集合(包含父类) </des>
	 * @param clazz Class
	 * @return 类声明的字段的名称的列表集合
	 * @since 1.0.0
	 */
	public static List<String> getAllFieldNames(Class<?> clazz){
		if(clazz == null){
			throw new ExecutetimeException("clazz must not be null");
		}
		List<Field> fields = getAllFields(clazz);
		if(fields == null){
			return null;
		}
		List<String> fieldNames = new ArrayList<String>(fields.size());
		for(Field field : fields){
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}
	
	// 生成找不到字段属性的异常对象
	private static RuntimeException generateFieldNotFoundException(Object object, String field){
		String classname = ClassUtil.getSimpleClassName(object);
		String message = "类 ? 中找不到名称为 ? 的成员属性或类属性！";
		String errorMessage = StringUtil.parse(message, field, classname);
		return new ExecutetimeException(new NoSuchFieldException(errorMessage));
	}
	
}