package org.jelly.fileupload;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jelly.util.FileUtil;
import org.jelly.util.security.MD5;
/**
 * 采用MD5检验上传的文件, 确保唯一
 * @author Lychie Fan
 * @since 1.4.0
 */
public class MD5FileUpload extends FileUpload {
	
	private static final long serialVersionUID = 5158667008985571805L;

	/**
	 * <des> 文件散列码相同的文件不上传, 直接返还已存在的文件的地址。<br>
	 * 适合小文件使用, 如上传图片等。大文件可能会造成性能影响</des>
	 * @since 1.4.0
	 */
	@Override
	protected String isEnableToUpload(File fileuploadBaseDir, InputStream input, HttpServletRequest request){
		List<File> fileList = FileUtil.listFiles(fileuploadBaseDir, null);
		if(fileList == null || fileList.size() == 0) return ENABLE;
		String fileMD5String = MD5.encrypt(input);
		for(File file : fileList){
			if(fileMD5String.equals(MD5.encrypt(file))){
				String baseDir = fileuploadBaseDir.getAbsolutePath().replaceAll("\\\\", SEPARATOR);
				baseDir = baseDir.substring(baseDir.lastIndexOf(SEPARATOR)) + SEPARATOR;
				String path = file.getAbsolutePath().replaceAll("\\\\", SEPARATOR);
				path = path.substring(path.lastIndexOf(baseDir));
				StringBuilder builder = new StringBuilder(request.getContextPath());				
				return builder.append(path).toString();
			}
		}
		return ENABLE;
	}

}