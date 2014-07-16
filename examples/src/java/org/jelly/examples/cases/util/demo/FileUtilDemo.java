package org.jelly.examples.cases.util.demo;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.jelly.helper.Testing;
import org.jelly.util.ClassPathUtil;
import org.jelly.util.FileUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class FileUtilDemo {

	final String filepath = ClassPathUtil.getClassPathFile("user.data").getAbsolutePath();
	
	/**
	 * 拷贝文件
	 */
	@Test
	public void copyFile(){
		InputStream in = FileUtil.getFileInputStream(filepath);
		OutputStream out = FileUtil.getFileOutputStream("target/user-backup.data");
		FileUtil.copyFile(in, out);
	}

	/**
	 * 读取文件内容
	 */
	@Test
	public void readFile(){
		String result = FileUtil.readFile(filepath);
		Testing.printlnObject(result);
	}
	
	/**
	 * 行读取文件, 每一行作为数组的一个元素
	 */
	@Test
	public void readLineFile(){
		String[] lines = FileUtil.readLineFile(filepath);
		Testing.printlnObject(lines);
	}

	/**
	 * 目录的文件对象列表
	 */
	@Test
	public void listFiles(){
		List<File> list = FileUtil.listFiles(new File("examples/src/java"));
		Testing.printlnObject(list);
	}

	/**
	 * 目录的文件对象列表, 按后缀过滤文件
	 */
	@Test
	public void listFilesByFilter(){
		List<File> list = FileUtil.listFiles(new File("examples/src/java"), ".data");
		Testing.printlnObject(list);
	}

	/**
	 * 在目录下查找文件
	 */
	@Test
	public void findFile(){
		File file = FileUtil.findFile("examples/src/java", "FileUtilDemo.java");
		Testing.printlnObject(file);
	}
	
}