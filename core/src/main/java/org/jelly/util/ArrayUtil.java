package org.jelly.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
/**
 * 数组相关的常用的操作的工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class ArrayUtil {
	
	private ArrayUtil(){}
	
	/**
	 * <des> 判定数组是否为空 </des>
	 * @param origin 源数组
	 * @return 若源数组为null, 或长度为0, 则返回true, 否则返回false
	 * @since 1.0.0
	 */
	public static boolean isEmpty(Object[] origin){
		return origin == null || origin.length == 0;
	}
	
	/**
	 * <des> 判定数组是否为不空 </des>
	 * @param origin 源数组
	 * @return 若源数组不为null, 而且长度不为0, 则返回true, 否则返回false
	 * @since 1.0.0
	 */
	public static boolean isNotEmpty(Object[] origin){
		return !isEmpty(origin);
	}
	
	public static boolean isArray(Object object){
		return object != null && object.getClass().isArray();
	}
	
	/**
	 * <des> 将参数作为数组表示 </des>
	 * @param object 可变参数项
	 * @return 数组
	 * @since 1.0.0
	 */
	public static <E> E[] asArray(E... object){
		return object;
	}

	/**
	 * <des> 将参数集合转换为数组表示 </des>
	 * @param collection 集合
	 * @return 数组
	 * @since 1.0.0
	 */
	public static <E> E[] asArray(Collection<E> collection){
		if(CollectionUtil.isEmpty(collection)){
			return null;
		}
		E e = collection.iterator().next();
		@SuppressWarnings("unchecked")
		E[] array = (E[]) Array.newInstance(e.getClass(), collection.size());
		return collection.toArray(array);
	}
	
	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static <E> boolean contains(E[] origin, E target){
		if(origin == null){
			return false;
		}
		if(target == null){
			for(E e : origin){
				if(e == null) return true;
			}
		}else{
			if(getElementType(origin).isInstance(target)){
				for(E e : origin){
					if(e == target || e.equals(target)) return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static boolean contains(byte[] origin, byte target){
		if(origin == null){
			return false;
		}
		for(byte item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static boolean contains(char[] origin, char target){
		if(origin == null){
			return false;
		}
		for(char item : origin){
			if(item == target) return true;
		}
		return false;
	}

	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static boolean contains(short[] origin, short target){
		if(origin == null){
			return false;
		}
		for(short item : origin){
			if(item == target) return true;
		}
		return false;
	}

	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static boolean contains(int[] origin, int target){
		if(origin == null){
			return false;
		}
		for(int item : origin){
			if(item == target) return true;
		}
		return false;
	}

	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static boolean contains(long[] origin, long target){
		if(origin == null){
			return false;
		}
		for(long item : origin){
			if(item == target) return true;
		}
		return false;
	}

	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static boolean contains(float[] origin, float target){
		if(origin == null){
			return false;
		}
		for(float item : origin){
			if(item == target) return true;
		}
		return false;
	}

	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static boolean contains(double[] origin, double target){
		if(origin == null){
			return false;
		}
		for(double item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <des> 判定数组元素中是否包含参数对象 </des>
	 * @param origin 源数组
	 * @param target 测试目标
	 * @return 如果包含则返回true, 否则返回false。特别的, 如果数组为null, 则永远返回false
	 * @since 1.0.0
	 */
	public static boolean contains(boolean[] origin, boolean target){
		if(origin == null){
			return false;
		}
		for(boolean item : origin){
			if(item == target) return true;
		}
		return false;
	}
	
	/**
	 * <des> 获取数组元素的实际数据类型 </des>
	 * @param origin 源数组
	 * @return 返回数组元素的实际类型。特别的, 如果数组为null, 则返回null
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public static <E> Class<E> getElementType(E[] origin){
		return origin == null ? null : (Class<E>) origin.getClass().getComponentType();
	}
	
	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(Object[] origin){
		return Arrays.toString(origin);
	}
	
	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(byte[] origin){
		return Arrays.toString(origin);
	}

	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(char[] origin){
		return Arrays.toString(origin);
	}

	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(short[] origin){
		return Arrays.toString(origin);
	}

	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(int[] origin){
		return Arrays.toString(origin);
	}

	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(long[] origin){
		return Arrays.toString(origin);
	}

	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(float[] origin){
		return Arrays.toString(origin);
	}

	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(double[] origin){
		return Arrays.toString(origin);
	}

	/**
	 * <des> 数组的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个字符串
	 * @since 1.0.0
	 */
	public static String toString(boolean[] origin){
		return Arrays.toString(origin);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(Object[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(byte[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(char[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(short[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(int[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(long[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(float[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(double[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}

	/**
	 * <des> 数组的简单的字符串表示 </des>
	 * @param origin 源数组
	 * @return 返还一个表示该数组的一个简单的字符串
	 * @since 1.0.0
	 */
	public static String toSimpleString(boolean[] origin){
		if(origin == null) return "null";
		String string = Arrays.toString(origin);
		return string.substring(1, string.length() - 1);
	}
}