package org.jelly.examples.cases.util.demo;

import org.jelly.examples.cases.util.demo.model.Foo;
import org.jelly.helper.Testing;
import org.jelly.util.ConvertUtil;
import org.jelly.util.FieldUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class ConvertUtilDemo {

	/**
	 * 演示错误的用法
	 */
	@Test
	public void badOperation(){
		String value = "10086";
		Foo foobar = new Foo();
		FieldUtil.setFieldValue(foobar, "bar", value);
		Testing.printlnObject(foobar);
	}
	
	/**
	 * 正确的做法
	 */
	@Test
	public void corretOperation(){
		String value = "10086";
		Foo foobar = new Foo();
		Class<?> type = FieldUtil.getFieldType(foobar, "bar");
		// 转换成正确的数据类型, 常见案例有HTTP数据包、读取XML、读取EXCEL等
		Object objectValue = ConvertUtil.objectValue(value, type);
		FieldUtil.setFieldValue(foobar, "bar", objectValue);
		Testing.printlnObject(foobar);
	}
	
}