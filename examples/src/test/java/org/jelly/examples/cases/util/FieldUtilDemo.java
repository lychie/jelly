package org.jelly.examples.cases.util;

import java.lang.reflect.Field;
import java.util.List;

import org.jelly.examples.cases.util.model.Foobar;
import org.jelly.helper.Testing;
import org.jelly.util.FieldUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class FieldUtilDemo {

	/**
	 * 设置属性的值
	 */
	@Test
	public void setFieldValue(){
		FieldUtil.setFieldValue(Foobar.class, "version", 1.1);
		Foobar foobar = new Foobar();
		FieldUtil.setFieldValue(foobar, "bar", 1);
		FieldUtil.setFieldValue(foobar, "baz", "Hi there");
		Testing.printlnObject(foobar);
	}

	/**
	 * 获取属性的值
	 */
	@Test
	public void getFieldValue(){
		Foobar foobar = new Foobar();
		double version = FieldUtil.getFieldValue(Foobar.class, "version");
		String baz = FieldUtil.getFieldValue(foobar, "baz");
		Testing.printlnObject(version);
		Testing.printlnObject(baz);
	}

	/**
	 * 获取可访问的字段属性对象
	 */
	@Test
	public void getAccessibleField(){
		Field versionField = FieldUtil.getAccessibleField(Foobar.class, "version");
		Field bazField = FieldUtil.getAccessibleField(Foobar.class, "baz");
		Testing.printlnObject(versionField);
		Testing.printlnObject(bazField);
	}

	/**
	 * 获取字段的数据类型
	 */
	@Test
	public void getFieldType(){
		Class<?> versionType = FieldUtil.getFieldType(Foobar.class, "version");
		Class<?>  bazType = FieldUtil.getFieldType(Foobar.class, "baz");
		Testing.printlnObject(versionType);
		Testing.printlnObject(bazType);
	}

	/**
	 * 获取类声明的字段对象列表
	 */
	@Test
	public void getDeclaredFields(){
		List<Field> fields = FieldUtil.getDeclaredFields(Foobar.class);
		Testing.printlnObject(fields);
	}

	/**
	 * 获取类声明的字段属性名称列表
	 */
	@Test
	public void getDeclaredFieldNames(){
		List<String> fieldNames = FieldUtil.getDeclaredFieldNames(Foobar.class);
		Testing.printlnObject(fieldNames);
	}

	/**
	 * 获取类以及超类的字段属性对象列表
	 * P.S : Object 没有属性字段
	 */
	@Test
	public void getAllFields(){
		List<Field> fields = FieldUtil.getAllFields(Foobar.class);
		Testing.printlnObject(fields);
	}

	/**
	 * 获取类以及超类的字段属性名称列表
	 * P.S : Object 没有属性字段
	 */
	@Test
	public void getAllFieldNames(){
		List<String> fieldNames = FieldUtil.getAllFieldNames(Foobar.class);
		Testing.printlnObject(fieldNames);
	}
}