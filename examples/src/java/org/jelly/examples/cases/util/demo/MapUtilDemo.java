package org.jelly.examples.cases.util.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jelly.helper.Testing;
import org.jelly.util.MapUtil;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class MapUtilDemo {

	private static final Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 判定是否为空
	 */
	@Test
	public void isEmpty(){
		Map<String, Object> map1 = new HashMap<String, Object>();
		Testing.printlnObject(MapUtil.isEmpty(map));
		Testing.printlnObject(MapUtil.isEmpty(null));
		Testing.printlnObject(MapUtil.isEmpty(map1));
	}

	/**
	 * 根据值查找键
	 */
	@Test
	public void findKey(){
		String key = MapUtil.findKey(map, "value2");
		Testing.printlnObject(key);
	}

	/**
	 * 反转键值对的映射
	 */
	@Test
	public void reverseKeyValue(){
		Map<Object, String> map2 = MapUtil.reverseKeyValue(map);
		Testing.printlnObject(map2);
	}

	/**
	 * 键列表
	 */
	@Test
	public void mapKeyAsList(){
		List<String> keys = MapUtil.mapKeyAsList(map);
		Testing.printlnObject(keys);
	}

	/**
	 * 值列表
	 */
	@Test
	public void mapValueAsList(){
		List<Object> values = MapUtil.mapValueAsList(map);
		Testing.printlnObject(values);
	}

	/**
	 * 创建实例
	 */
	@Test
	public void newMap(){
		Map<String, Set<List<Object>>> map = MapUtil.newMap();
		Testing.printlnObject(map);
	}
	
	/**
	 * 预设数据
	 */
	@BeforeClass
	public static void prepare(){
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
	}
	
}