package org.jelly.examples.util;

import java.util.ArrayList;
import java.util.List;
import org.jelly.helper.Testing;
import org.jelly.util.ArrayUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class ArrayUtilDemo {

	/**
	 * 判定是否为空
	 */
	@Test
	public void isEmpty(){
		Testing.printlnObject(ArrayUtil.isEmpty(null));
		Testing.printlnObject(ArrayUtil.isEmpty(new String[0]));
		Testing.printlnObject(ArrayUtil.isEmpty(new String[1]));
	}
	
	/**
	 * 判定对象是否是一个数组
	 */
	@Test
	public void isArray(){
		Object o1 = "";
		Object o2 = new String[1];
		Testing.printlnObject(ArrayUtil.isArray(o1));
		Testing.printlnObject(ArrayUtil.isArray(o2));
	}
	
	/**
	 * 参数作为一个数组
	 */
	@Test
	public void paramAsArray(){
		String[] origin;
		origin = ArrayUtil.asArray("Lychie", "Fan");
		Testing.printlnObject(origin);
	}
	
	/**
	 * 集合作为一个数组
	 */
	@Test
	public void collectionAsArray(){
		List<String> list = new ArrayList<String>();
		list.add("Lychie");
		list.add("Fan");
		String[] origin = ArrayUtil.asArray(list);
		Testing.printlnObject(origin);
	}
	
	/**
	 * 判定数组是否包含
	 */
	@Test
	public void contains(){
		String[] origin = {"Lychie", "Fan"};
		Testing.printlnObject(ArrayUtil.contains(origin, null));
		Testing.printlnObject(ArrayUtil.contains(origin, "lychie"));
		Testing.printlnObject(ArrayUtil.contains(origin, "Lychie"));
	}
	
	/**
	 * 获取数组元素的类型
	 */
	@Test
	public void arrayElementType(){
		String[] origin = {"Lychie", "Fan"};
		Testing.printlnObject(ArrayUtil.getElementType(null));
		Testing.printlnObject(ArrayUtil.getElementType(origin));
	}
	
	/**
	 * 数组的字符串表示
	 */
	@Test
	public void string(){
		String[] origin = {"Lychie", "Fan"};
		String result = ArrayUtil.toString(origin);
		Testing.printlnObject(result);
	}
	
	/**
	 * 数组的简单的字符串表示
	 */
	@Test
	public void simpleString(){
		String[] origin = {"Lychie", "Fan"};
		String result = ArrayUtil.toSimpleString(origin);
		Testing.printlnObject(result);
	}
}