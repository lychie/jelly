package org.jelly.helper;

import org.jelly.code.SystemCode;
/**
 * 系统参数助手类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class SystemHelper {
	
	private SystemHelper(){}
	
	/**
	 * <des> 获取项目文件编码的字符集 </des>
	 * @return 项目文件编码的字符集
	 * @since 1.0.0
	 */
	public static String getFileEncoding(){
		return getProperty(SystemCode.FILE_ENCODING);
	}
	
	/**
	 * <des> 获取项目目录路径 </des>
	 * @return 项目目录路径
	 * @since 1.0.0
	 */
	public static String getProjectDir(){
		return getProperty(SystemCode.USER_DIR);
	}
	
	/**
	 * <des> 获取系统参数值 </des>
	 * @param systemCode SystemCode
	 * @return 系统参数值
	 * @see org.jelly.code.SystemCode
	 * @since 1.0.0
	 */
	public static String getProperty(SystemCode systemCode){
		return System.getProperty(systemCode.toCode());
	}
	
}