package org.jelly.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jelly.code.IndexCode;
import org.jelly.code.SystemCode;
import org.jelly.helper.SystemHelper;
/**
 * 类路径工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class ClassPathUtil {

	private ClassPathUtil(){}
	
	/**
	 * <des> 获取项目类路径目录列表 </des>
	 * @return 项目类路径目录列表
	 * @since 1.0.0
	 */
	public static String[] getClassPathDirs(){
		List<String> dirList = new ArrayList<String>();
		String classpath = System.getProperty(SystemCode.CLASS_PATH_DIR.toCode());
		String[] classpaths = classpath.split(";");
		for(String path : classpaths){
			if(new File(path).isDirectory()){
				dirList.add(path);
			}
		}
		return ArrayUtil.asArray(dirList);
	}

	/**
	 * <des> 获取类路径下与参数名相同的文件 </des>
	 * @param filename 文件名称
	 * @return 若查找的到则返回该文件对象, 否则返回null
	 * @since 1.0.0
	 */
	public static File getClassPathFile(String filename){
		filename = StringUtil.substringAfter(filename, ":");
		String basedir = SystemHelper.getProjectDir();
		String[] classpathDirs = getClassPathDirs();
		File result;
		for(String classpathDir : classpathDirs){
			if(classpathDir.indexOf(basedir) != IndexCode.INDEX_NOT_FOUND.toCode()){
				result = FileUtil.findFile(classpathDir, filename);
				if(result != null){
					return result;
				}
			}
		}
		return null;
	}
	
}