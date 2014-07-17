package org.jelly.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jelly.exception.ExecutetimeException;
/**
 * 散列表常用的操作工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class MapUtil {

	private MapUtil(){}
	
	/**
	 * <des> 判定散列表是否为空 </des>
	 * @param map 测试对象
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean isEmpty(Map<?, ?> map){
		return map == null || map.isEmpty() ? true : false;
	}
	
	/**
	 * <des> 判定散列表是否不为空 </des>
	 * @param map 测试对象
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean isNotEmpty(Map<?, ?> map){
		return !isEmpty(map);
	}
	
	/**
	 * <des> 根据Value查找Key, 要求Value在散列表中是唯一的 </des>
	 * @param map 散列表对象
	 * @param value 值
	 * @return 键
	 * @since 1.0.0
	 */
	public static <K, V> K findKey(Map<K, V> map, V value){
		if(!map.containsValue(value)){
			return null;
		}
		K key = null;
		int counts = 0;
		for(K k : map.keySet()){
			if(map.get(k).equals(value)){
				key = k;
				counts++;
			}
		}
		if(counts > 1){
			throw new ExecutetimeException("发现散列表对象中有多个不同键映射到了同一个值中");
		}
		return key;
	}
	
	/**
	 * <des> 对调 Key&Value, 要求Value在散列表中是唯一的 </des>
	 * @param map 散列表对象
	 * @return Map
	 * @since 1.0.0
	 */
	public static <K, V> Map<V, K> reverseKeyValue(Map<K, V> map){
		Map<V, K> mapping = new HashMap<V, K>();
		V value;
		for(K key : map.keySet()){
			value = map.get(key);
			if(mapping.containsKey(value)){
				throw new ExecutetimeException("发现散列表对象中有多个不同键映射到了同一个值中");
			}
			mapping.put(value, key);
		}
		return mapping;
	}
	
	/**
	 * <des> 创建一个 HashMap 实例 </des>
	 * @return HashMap
	 * @since 1.0.0
	 */
	public static <K, V> Map<K, V> newMap(){
		return new HashMap<K, V>(16, .75f);
	}
	
	/**
	 * <des> 创建一个 HashMap 实例 </des>
	 * @param initialCapacity 初始容量
	 * @param loadFactor 加载因子。加载因子越大, 散列表填充程度越密集, 可节省内存空间的开销,
	 * 但容易引起哈希冲突, 造成查询效率降低; 加载因子越小, 散列表填充程度越稀疏, 不易造成哈希冲突, 
	 * 但容易造成内存空间的浪费, 以及引发散列表内部结构不断重构。<br>
	 * 当散列表存储元素的条目超出 '初始容量' * '加载因子' 时, 散列表内部自动进行扩容
	 * @return HashMap
	 * @since 1.0.0
	 */
	public static <K, V> Map<K, V> newMap(int initialCapacity, double loadFactor){
		return new HashMap<K, V>(initialCapacity, (float)loadFactor);
	}
	
	/**
	 * <des> Map 的 Key 作为一个 List </des>
	 * @param map 散列表对象
	 * @return key 的列表集合
	 * @since 1.0.0
	 */
	public static <K> List<K> mapKeyAsList(Map<K, ?> map){
		return isEmpty(map) ? null : new ArrayList<K>(map.keySet());
	}
	
	/**
	 * <des> Map 的 Value 作为一个 List </des>
	 * @param map 散列表对象
	 * @return value 的列表集合
	 * @since 1.0.0
	 */
	public static <V> List<V> mapValueAsList(Map<?, V> map){
		return isEmpty(map) ? null : new ArrayList<V>(map.values());
	}
	
}