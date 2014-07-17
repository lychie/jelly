package org.jelly.helper;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.jelly.exception.ExecutetimeException;
import org.jelly.util.ArrayUtil;
import org.jelly.util.CollectionUtil;
import org.jelly.util.MapUtil;
/**
 * 测试助手类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class Testing {
	
	private Testing(){}
	
	/**
	 * <des> 不换行打印输出对象信息。支持 Collection, Map, Iterator, Pojo, 数组 </des>
	 * @param object 需要打印输出的对象
	 * @since 1.0.0
	 */
	public static void printObject(Object object){
		printObject(object, false);
	}
	
	/**
	 * <des> 换行打印输出对象信息。支持 Collection, Map, Iterator, Pojo, 数组 </des>
	 * @param object 需要打印输出的对象
	 * @since 1.0.0
	 */
	public static void printlnObject(Object object){
		printObject(object, true);
	}
	
	/**
	 * <des> 使用操作系统工具打开文件 </des>
	 * @param file 文件对象
	 * @since 1.0.0
	 */
	public static void openFile(File file){
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			throw new ExecutetimeException(e);
		}
	}
	
	// 打印输出对象信息
	private static void printObject(Object object, boolean isNewLine){
		if(object instanceof Collection){
			printCollection((Collection<?>)object, isNewLine);
		}else if(object instanceof Iterator){
			printIterator((Iterator<?>)object, isNewLine);
		}else if(object instanceof Map){
			printMap((Map<?, ?>)object, isNewLine);
		}else if(ArrayUtil.isArray(object)){
			printArray(object, isNewLine);
		}else{
			printOut(object, isNewLine, true);
		}
	}
	
	// 处理 Collection
	private static void printCollection(Collection<?> collection, boolean isNewLine){
		if(CollectionUtil.isEmpty(collection)){
			printEmpty(); return ;
		}
		int length = collection.size(), counts = 0;
		for(Object o : collection){
			printOut(o, isNewLine, ++counts == length);
		}
	}
	
	// 处理 Iterator
	private static void printIterator(Iterator<?> it, boolean isNewLine){
		if(!it.hasNext()){
			printEmpty(); return ;
		}
		while(it.hasNext()){
			printOut(it.next(), isNewLine, it.hasNext());
		}
	}

	// 处理 Map
	private static void printMap(Map<?, ?> map, boolean isNewLine){
		if(MapUtil.isEmpty(map)){
			printEmpty(); return ;
		}
		int length = map.size(), counts = 0;
		for(Object key : map.keySet()){
			printOut(key + " : " + map.get(key), isNewLine, ++counts == length);
		}
	}
	
	// 处理数组
	private static void printArray(Object object, boolean isNewLine){
		int length = Array.getLength(object);
		if(!isNewLine) System.out.print("[");
		for(int i = 0; i < length - 1; i++){
			printOut(Array.get(object, i), isNewLine, false);
		}
		System.out.print(Array.get(object, length - 1));
		if(!isNewLine) {
			System.out.println("]");
		}else{
			System.out.println("");
		} 
	}
	
	// 处理是否换行以及最后一个元素的输出方式
	private static void printOut(Object object, boolean isNewLine, boolean isLastItem){
		if(isNewLine || isLastItem){
			System.out.println(object);
		}else{
			System.out.print(object + ", ");
		}
	}
	
	// 容器对象为空的时候输出的信息
	private static void printEmpty(){
		System.out.println("----->>> The object argument is empty <<<-----");
	}
}