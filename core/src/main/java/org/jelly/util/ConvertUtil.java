package org.jelly.util;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jelly.code.EncodingCode;
/**
 * 转换工具
 * @author Lychie Fan
 * @since 1.0.0
 */
public class ConvertUtil {

	private ConvertUtil(){}
	
	public static final Map<Class<?>, Class<?>> primitiveBoxedMap;
	public static final Map<Class<?>, Class<?>> boxedPrimitiveMap;
	
	/**
	 * <des> 转换字符串为指定类型的值, 支持的类型有限(基本&包装数据类型、简单日期类型、字节序列) </des>
	 * @param value 待转换的字符串
	 * @param type 期望转成的类型
	 * @return 若期望类型是被支持的, 则返回转换后的对象; 若是不被支持的, 则返回源字符串对象
	 * @since 1.0.0
	 */
	public static Object objectValue(String value, Class<?> type){
		// primitive & boxer type
		if(type.isPrimitive() || boxedPrimitiveMap.containsKey(type)){
			Class<?> key = type.isPrimitive() ? type : boxedPrimitiveMap.get(type);
			String methodName = key + "Value";
			return MethodUtil.invokeMethod(ConvertUtil.class, methodName, value);
		}
		// Date type ( try to parse the value pattern )
		if(ClassUtil.isInstanceOf(type, Date.class)){
			return DateUtil.parseDate(value, DateUtil.getPattern(value));
		}
		// byte[] type ( UTF-8 )
		if(ClassUtil.isInstanceOf(type, byte[].class)){
			return value.getBytes(Charset.forName(EncodingCode.UTF_8.toCode()));
		}
		// undefined
		return value;
	}
	
	/**
	 * <des> 转换字符串为指定类型的值, 支持的类型有限(基本&包装数据类型、简单日期类型、字节序列) </des>
	 * @param values 待转换的字符串数组
	 * @param types 期望转成的类型数组
	 * @return 若期望类型是被支持的, 则返回转换后的对象; 若是不被支持的, 则返回源字符串对象
	 * @see org.jelly.util.ConvertUtil#objectValue(String, Class)
	 * @since 1.0.0
	 */
	public static Object[] objectValues(String[] values, Class<?>[] types){
		Object[] target = new Object[values.length];
		for(int i = 0; i < values.length; i++){
			target[i] = objectValue(values[i], types[i]);
		}
		return target;
	}
	
	/**
	 * <des> 获取基本数据类型的包装类型 </des>
	 * @param clazz 类型参数
	 * @return 若参数是一个基本数据类型, 则返回其包装类型, 否则直接返回该类型对象
	 * @since 1.0.0
	 */
	public static Class<?> getBoxedPrimitiveType(Class<?> clazz){
		if(primitiveBoxedMap.containsKey(clazz)){
			return primitiveBoxedMap.get(clazz);
		}
		return clazz;
	}
	
	// the following method is too simple and there is nothing to describe
	
	public static byte byteValue(String value){
		return Byte.parseByte(value);
	}
	
	public static short shortValue(String value){
		return Short.parseShort(value);
	}
	
	public static int intValue(String value){
		return Integer.parseInt(value);
	}
	
	public static long longValue(String value){
		return Long.parseLong(value);
	}
	
	public static float floatValue(String value){
		return Float.parseFloat(value);
	}
	
	public static double doubleValue(String value){
		return Double.parseDouble(value);
	}
	
	public static char charValue(String value){
		return value.charAt(0);
	}
	
	public static boolean booleanValue(String value){
		return Boolean.parseBoolean(value);
	}
	
	static {
		primitiveBoxedMap = new HashMap<Class<?>, Class<?>>(9, 1);
		primitiveBoxedMap.put(Byte.TYPE, Byte.class);
		primitiveBoxedMap.put(Character.TYPE, Character.class);
		primitiveBoxedMap.put(Short.TYPE, Short.class);
		primitiveBoxedMap.put(Integer.TYPE, Integer.class);
		primitiveBoxedMap.put(Long.TYPE, Long.class);
		primitiveBoxedMap.put(Float.TYPE, Float.class);
		primitiveBoxedMap.put(Double.TYPE, Double.class);
		primitiveBoxedMap.put(Boolean.TYPE, Boolean.class);
		primitiveBoxedMap.put(Void.TYPE, Void.class);
		boxedPrimitiveMap = MapUtil.reverseKeyValue(primitiveBoxedMap);
	}
}