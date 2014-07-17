package org.jelly.examples.cases.util;

import java.io.File;
import org.jelly.helper.Testing;
import org.jelly.util.ClassPathUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class ClassPathUtilDemo {

	/**
	 * 获取项目类路径目录列表
	 */
	@Test
	public void getClassPathDirs(){
		String[] classpaths = ClassPathUtil.getClassPathDirs();
		Testing.printlnObject(classpaths);
	}
	
	/**
	 * 获取类路径下的文件
	 */
	@Test
	public void getClassPathFile(){
		File file = ClassPathUtil.getClassPathFile("ClassPathUtilDemo.class");
		Testing.printlnObject(file);
	}
}