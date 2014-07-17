package org.jelly.examples.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jelly.helper.Testing;
import org.jelly.util.ArrayUtil;
import org.jelly.util.CollectionUtil;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class TestingDemo {

	private static List<String> list = new ArrayList<String>();
	private static Map<String, Object> map = new HashMap<String, Object>();
	private static String[] array = new String[4];

	/**
	 * 打印集合
	 */
	@Test
	public void printCollection(){
		Testing.printlnObject(list);
	}
	
	/**
	 * 打印MAP
	 */
	@Test
	public void printMap(){
		Testing.printlnObject(map);
	}
	
	/**
	 * 打印数组
	 */
	@Test
	public void printArray(){
		Testing.printObject(array);
		Testing.printlnObject(array);
	}
	
	@BeforeClass
	public static void prepare(){
		list = CollectionUtil.asList("My name is Lychie".split(" "));
		array = ArrayUtil.asArray(list);
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
	}
	
}