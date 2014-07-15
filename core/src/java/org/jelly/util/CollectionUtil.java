package org.jelly.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jelly.code.SortCode;
import org.jelly.model.SimpleComparator;
/**
 * 集合相关的主要面向 List、Set 的常用的操作的工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class CollectionUtil {

	private CollectionUtil(){}
	
	/**
	 * <des> 判定集合是否为空 </des>
	 * @param collection 集合
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean isEmpty(Collection<?> collection){
		return collection == null || collection.size() == 0;
	}

	/**
	 * <des> 判定集合是否不为空 </des>
	 * @param collection 集合
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	/**
	 * <des> 将集合转换为数组表示 </des>
	 * @param collection集合
	 * @return 数组
	 * @since 1.0.0
	 */
	public static <E> E[] toArray(Collection<E> collection){
		return ArrayUtil.asArray(collection);
	}
	
	/**
	 * <des> 参数作为集合表示 </des>
	 * @param obj 可变参数项
	 * @return ArrayList
	 * @since 1.0.0
	 */
	public static <E> List<E> asList(E... obj){
		return Arrays.asList(obj);
	}
	
	/**
	 * <des> 参数转换为List表示 </des>
	 * @param collection 集合
	 * @return ArrayList
	 * @since 1.0.0
	 */
	public static <E> List<E> asList(Collection<E> collection){
		if(collection == null){
			return null;
		}
		if(collection instanceof List){
			return (List<E>)collection;
		}
		return new ArrayList<E>(collection);
	}
	
	/**
	 * <des> 参数转换为Set表示 </des>
	 * @param object 可变参数项
	 * @return HashSet
	 * @since 1.0.0
	 */
	public static <E> Set<E> asSet(E... object){
		return new HashSet<E>(Arrays.asList(object));
	}
	
	/**
	 * <des> 参数转换为Set表示 </des>
	 * @param collection 集合
	 * @return HashSet
	 * @since 1.0.0
	 */
	public static <E> Set<E> asSet(Collection<E> collection){
		if(collection == null){
			return null;
		}
		if(collection instanceof Set){
			return (Set<E>)collection;
		}
		return new HashSet<E>(collection);
	}
	
	/**
	 * <des> 创建一个 ArrayList 实例 </des>
	 * @return ArrayList
	 * @since 1.0.0
	 */
	public static <E> List<E> newList(){
		return new ArrayList<E>();
	}
	
	/**
	 * <des> 创建一个指定容量的 ArrayList 实例 </des>
	 * @param size 容量大小
	 * @return ArrayList
	 * @since 1.0.0
	 */
	public static <E> List<E> newList(int size){
		return new ArrayList<E>(size);
	}
	
	/**
	 * <des> 创建一个 HashSet 实例 </des>
	 * @return HashSet
	 * @since 1.0.0
	 */
	public static <E> Set<E> newSet(){
		return new HashSet<E>(16, .75f);
	}
	
	/**
	 * <des> 创建一个指定容量大小的 HashSet 实例 </des>
	 * @param initialCapacity 初始容量
	 * @param loadFactor 加载因子。加载因子越大, 散列表填充程度越密集, 可节省内存空间的开销,
	 * 但容易引起哈希冲突, 造成查询效率降低; 加载因子越小, 散列表填充程度越稀疏, 不易造成哈希冲突, 
	 * 但容易造成内存空间的浪费, 以及引发散列表内部结构不断重构。<br>
	 * 当散列表存储元素的条目超出 '初始容量' * '加载因子' 时, 散列表内部自动进行扩容<br>
	 * P.S ：HashSet 是基于 HashMap 实现的
	 * @return HashSet
	 * @since 1.0.0
	 */
	public static <E> Set<E> newSet(int initialCapacity, double loadFactor){
		return new HashSet<E>(initialCapacity, (float) loadFactor);
	}
	
	/**
	 * <des> 根据关键字排序集合, 关键字类型支持常用的数值类型, 字符类型, 布尔类型, 日期类型。
	 * 特别说明, 中文排序不十分准确, 常用汉字排序可用, 若要求中文严格排序, 建议不要使用 </des>
	 * @param collection 需要进行排序的集合
	 * @param key 关键字
	 * @since 1.0.0
	 */
	public static <E> void sortByAsc(Collection<E> collection, String key){
		sortBySortKey(collection, key, SortCode.ASC);
	}
	
	/**
	 * <des> 根据关键字排序集合, 关键字类型支持常用的数值类型, 字符类型, 布尔类型, 日期类型。
	 * 特别说明, 中文排序不十分准确, 常用汉字排序可用, 若要求中文严格排序, 建议不要使用 </des>
	 * @param collection 需要进行排序的集合
	 * @param key 关键字
	 * @since 1.0.0
	 */
	public static <E> void sortByDesc(Collection<E> collection, String key){
		sortBySortKey(collection, key, SortCode.DESC);
	}
	
	// 根据排序关键字排序集合
	@SuppressWarnings("unchecked")
	private static <E> void sortBySortKey(Collection<E> collection, String key, SortCode sortCode){
		if(isEmpty(collection)) return ;
		Object[] source = ArrayUtil.asArray(collection);
		E e = collection.iterator().next();
		SimpleComparator comparator = new SimpleComparator(e.getClass(), key, sortCode);
		quicksort(source, 0, source.length - 1, comparator);
		collection.clear();
		collection.addAll((List<E>)asList(source));
	}
	
	// 快速排序算法
	private static void quicksort(Object[] array, int low,int hight, SimpleComparator comparator){
		if(low < hight){
			int position = partition(array, low, hight, comparator);
			quicksort(array, low, position - 1, comparator);
			quicksort(array, position + 1, hight, comparator);
		}
	}

	// 快速排序算法
	private static int partition(Object[] array, int low,int hight, SimpleComparator comparator){
		Object key = array[low];
		while(low < hight){
			while(low < hight && comparator.compare(array[hight], key) >= 0){
				hight--;
			}
			array[low] = array[hight];
			while(low < hight && comparator.compare(array[low], key) <= 0){
				low++;
			}
			array[hight] = array[low];
		}
		array[low] = key;
		return low;
	}
	
}