package org.jelly.examples.helper;

import org.jelly.code.SystemCode;
import org.jelly.helper.SystemHelper;
import org.jelly.helper.Testing;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class SystemHelperDemo {

	/**
	 * 文件编码
	 */
	@Test
	public void getSystemFileEncoding(){
		Testing.printlnObject(SystemHelper.getFileEncoding());
	}

	/**
	 * 项目路径
	 */
	@Test
	public void getProjectDir(){
		Testing.printlnObject(SystemHelper.getProjectDir());
	}

	/**
	 * 类路径
	 */
	@Test
	public void getProperty(){
		Testing.printlnObject(SystemHelper.getProperty(SystemCode.CLASS_PATH_DIR));
	}
}