package org.jelly.model;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import org.jelly.exception.ExecutetimeException;
/**
 * 类型令牌
 * @author Lychie Fan
 * @since 1.6.0
 */
public abstract class ClassToken <E> {

	/**
	 * <des> 泛型类型参数 </des>
	 * @since 1.6.0
	 */
	public final Type getType(){
		try {
			Type type = getClass().getGenericSuperclass();
	        Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
	        return parameterizedType[0];
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 泛型参数类型, 非泛型返还其本身类型 </des>
	 * @since 1.6.0
	 */
	public static Class<?> getParameterizedType(Type type) {
		return getParameterizedType(type, 0);
	}
	
	/**
	 * <des> 泛型参数类型, 非泛型返还其本身类型 </des>
	 * @since 1.6.0
	 */
	public static Class<?> getParameterizedType(Type type, int index) {
		try {
			return (Class<?>) type;
		} catch (ClassCastException e) {
			return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[index];
		}
	}
	
	/**
	 * <des> 泛型原生类型, 非泛型返还其本身类型 </des>
	 * @since 1.6.0
	 */
	public static Class<?> getRawType(Type type) {
		try {
			return (Class<?>) type;
		} catch (ClassCastException e) {
			return (Class<?>) ((ParameterizedType) type).getRawType();
		}
	}
	
}