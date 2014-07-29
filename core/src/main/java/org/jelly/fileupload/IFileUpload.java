package org.jelly.fileupload;
/**
 * 文件上传接口
 * @author Lychie Fan
 * @since 1.4.0
 */
public interface IFileUpload {
	
	/* --- configuration file key --- */
	String FILE = "file";
	String IMAGE = "image";
	String MEDIA = "media";
	String MAX_SIZE = "maxSize";
	String UPLOAD_DIR = "uploadDir";
	String OVER_SIZE = "overSize";
	String DISABLE_TO_UPLOAD = "disableToUpload";
	String NO_FILES_ARE_SELECTED = "noFilesAreSelected";
	String NOT_SUPPORTED_FILE_TYPE = "notSupportedFileType";
	String SERVER_THROWS_EXCEPTION = "serverThrowsException";
	
	/* --- configuration file key default value --- */
	int DEFAULT_MAX_SIZE = 1024 * 1024 * 1;
	String DEFAULT_UPLOAD_DIR = "upload";
	String DEFAULT_OVER_SIZE_MESSAGE = "上传的文件大小超出限制";
	String DEFAULT_DISABLE_TO_UPLOAD_MESSAGE = "文件已存在！";
	String DEFAULT_NO_FILES_ARE_SELECTED_MESSAGE = "请选择文件";
	String DEFAULT_NOT_SUPPORTED_FILE_TYPE_MESSAGE = "不支持的文件类型！";
	String DEFAULT_SERVER_THROWS_EXCEPTION_MESSAGE = "服务器端发生异常，文件上传失败。";
	String DEFAULT_NOT_MULTIPART_CONTENT_MESSAGE = "不支持文件上传的表单域！";
	
	/* --- json key & value --- */
	String ACTION = "action";
	String RESULT = "result";
	String FAIL = "fail";
	String SUCCESS = "success";
	
	/* --- constant --- */
	String SEPARATOR = "/";
	String ENABLE = "enable";
	String DISABLE = "disable";
	String CONFIG_FILE_PATH = "configFilePath";
	String CLASS_PATH = "classpath:";
	
}