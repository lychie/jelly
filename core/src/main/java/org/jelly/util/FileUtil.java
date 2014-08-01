package org.jelly.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.jelly.code.IndexCode;
import org.jelly.exception.ExecutetimeException;
/**
 * 文件常用的操作的工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class FileUtil {

	private FileUtil(){}
	
	private static final int BUFFER_SIZE = 1024 * 1024 / 2;
	
	/**
	 * <des> 文件拷贝, 方法调用结束或抛出异常, 都将关闭输入输出流, 输出流flush </des>
	 * @param in InputStream
	 * @param out OutputStream
	 * @since 1.0.0
	 */
	public static void copyFile(InputStream in, OutputStream out){
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			int read;
			while((read = in.read(buffer)) != IndexCode.EOF.toCode()){
				out.write(buffer, 0, read);
			}
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		} finally {
			closeStream(in, out);
		}
	}
	
	/**
	 * <des> 写出, 方法调用结束或抛出异常, 都将关闭输入输出流, 输出流flush </des>
	 * @since 1.6.0
	 */
	public static void write(InputStream in, OutputStream out){
		copyFile(in, out);
	}

	/**
	 * <des> 字符串内容写出, 方法调用结束或抛出异常, 都将关闭输入输出流, 输出流flush </des>
	 * @since 1.6.0
	 */
	public static void write(String text, OutputStream out){
		try {
			out.write(text.getBytes());
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		} finally {
			closeStream(out);
		}
	}

	/**
	 * <des> 字符串内容写出到文件, 方法调用结束或抛出异常, 都将关闭输入输出流, 输出流flush </des>
	 * @since 1.6.0
	 */
	public static void write(String text, File file){
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new StringReader(text));
			writer = new BufferedWriter(new FileWriter(file));
			char[] buffer = new char[BUFFER_SIZE];
			int read;
			while((read = reader.read(buffer)) != IndexCode.EOF.toCode()){
				writer.write(buffer, 0, read);
			}
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		} finally {
			closeStream(reader, writer);
		}
	}
	
	/**
	 * <des> 读取获得文件内容 </des>
	 * @param pathname 文件全路径名称
	 * @return 读取获得的文件内容
	 * @since 1.0.0
	 */
	public static String readFile(String pathname){
		return readFile(new File(pathname));
	}
	
	/**
	 * <des> 读取获得文件内容 </des>
	 * @param file 文件对象
	 * @return 读取获得的文件内容
	 * @since 1.0.0
	 */
	public static String readFile(File file){
		String[] contents = readLineFile(file);
		StringBuilder builder = new StringBuilder();
		for(String content : contents){
			builder.append(content);
		}
		return builder.toString();
	}
	
	/**
	 * <des> 按行读取文件, 文件的每一行作为数组的一个元素 </des>
	 * @param pathname 文件全路径名称
	 * @return 数组
	 * @since 1.0.0
	 */
	public static String[] readLineFile(String pathname){
		return readLineFile(new File(pathname));
	}
	
	/**
	 * <des> 按行读取文件, 文件的每一行作为数组的一个元素 </des>
	 * @param file 文件对象
	 * @return 数组
	 * @since 1.0.0
	 */
	public static String[] readLineFile(File file){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			LinkedList<String> list = new LinkedList<String>();
			String read;
			while((read = reader.readLine()) != null){
				list.add(read);
			}
			return ArrayUtil.asArray(list);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		} finally {
			closeStream(reader);
		}
	}
	
	/**
	 * <des> 获取目录下所有的文件对象 </des>
	 * @param directory 文件目录对象
	 * @return 文件对象列表
	 * @since 1.0.0
	 */
	public static List<File> listFiles(File directory){
		return listFiles(directory, null);
	}
	
	/**
	 * <des> 获取目录下所有由参数指定类型的文件对象 </des>
	 * @param source 文件目录对象
	 * @param filter 期望得到的文件的后缀名称
	 * @return 文件对象列表
	 * @since 1.0.0
	 */
	public static List<File> listFiles(File source, String filter){
		File[] fileList = source.listFiles();
		List<File> list = new ArrayList<File>();
		filter = filter == null ? null : filter.toLowerCase();
		if(fileList != null && fileList.length > 0){
			for(File file : fileList){
				if(file.isFile()){
					add(list, file, filter);
				}else if(file.isDirectory()){
					list.addAll(listFiles(file, filter));
				}
			}
		}else if(source.isFile()){
			add(list, source, filter);
		}
		return list;
	}
	
	/**
	 * <des> 在参数指定的目录中查找文件 </des>
	 * @param dirpath 文件目录全路径名称
	 * @param filename 查找的文件名称
	 * @return 若查找的到, 则返还该文件对象, 若查找不到, 则返还null
	 * @since 1.0.0
	 */
	public static File findFile(String dirpath, String filename){
		return findFile(new File(dirpath), filename);
	}
	
	/**
	 * <des> 在参数指定的目录中查找文件 </des>
	 * @param directory 文件目录对象
	 * @param filename 查找的文件名称
	 * @return 若查找的到, 则返还该文件对象, 若查找不到, 则返还null
	 * @since 1.0.0
	 */
	public static File findFile(File directory, String filename){
		String filter = StringUtil.substringAfterLastWith(filename, ".");
		List<File> files = listFiles(directory, filter);
		if(files.size() == 0) return null;
		for(File file : files){
			if(file.getAbsolutePath().endsWith(filename)){
				return file;
			}
		}
		return null;
	}
	
	/**
	 * <des> 删除文件或目录 </des>
	 * @param file 文件或文件目录对象
	 * @since 1.0.0
	 */
	public static void delete(File file){
		if(file.isFile()){
			file.delete();
		}else if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File item : files){
				delete(item);
			}
		}
		file.delete();
	}
	
	/**
	 * <des> 创建目录 </des>
	 * @param pathname 目录名称
	 * @return 若目录不存在则创建, 若存在则直接返还true
	 * @since 1.0.0
	 */
	public static boolean createDir(String pathname){
		return createDir(new File(pathname));
	}
	
	/**
	 * <des> 创建目录 </des>
	 * @param dir 目录对象
	 * @return 若目录不存在则创建, 若存在则直接返还true
	 * @since 1.0.0
	 */
	public static boolean createDir(File dir){
		if(!dir.exists()){
			return dir.mkdirs();
		}
		return true;
	}
	
	/**
	 * <des> 关闭输入输出流, 并flush输出流 </des>
	 * @param streams 输入或输出流对象
	 * @since 1.0.0
	 */
	public static void closeStream(Object... streams){
		if(ArrayUtil.isEmpty(streams)) return ;
		for(Object stream : streams){
			if(stream != null){
				try {
					MethodUtil.invokeMethod(stream, "flush");
				} catch (ExecutetimeException e) {
					
				} finally {
					MethodUtil.invokeMethod(stream, "close");
				}
			}
		}
	}
	
	/**
	 * <des> 获取FileInputStream实例 </des>
	 * @since 1.0.0
	 */
	public static FileInputStream getFileInputStream(String pathname){
		return getFileInputStream(new File(pathname));
	}
	
	/**
	 * <p><des> 获取FileInputStream实例 </des></p>
	 * @since 1.0.0
	 */
	public static FileInputStream getFileInputStream(File file){
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <p><des> 获取FileOutputStream实例 </des></p>
	 * @since 1.0.0
	 */
	public static FileOutputStream getFileOutputStream(String pathname){
		return getFileOutputStream(new File(pathname), false);
	}
	
	/**
	 * <p><des> 获取FileOutputStream实例 </des></p>
	 * @since 1.0.0
	 */
	public static FileOutputStream getFileOutputStream(String pathname, boolean append){
		return getFileOutputStream(new File(pathname), append);
	}
	
	/**
	 * <p><des> 获取FileOutputStream实例 </des></p>
	 * @since 1.0.0
	 */
	public static FileOutputStream getFileOutputStream(File file){
		return getFileOutputStream(file, false);
	}
	
	/**
	 * <p><des> 获取FileOutputStream实例 </des></p>
	 * @since 1.0.0
	 */
	public static FileOutputStream getFileOutputStream(File file, boolean append){
		try {
			return new FileOutputStream(file, append);
		} catch (FileNotFoundException e) {
			throw new ExecutetimeException(e);
		}
	}
	
	// 添加文件到列表
	private static void add(List<File> list, File file, String filter){
		if(filter == null){
			list.add(file);
		}else if(file.getAbsolutePath().toLowerCase().endsWith(filter)){
			list.add(file);
		}
	}
	
}