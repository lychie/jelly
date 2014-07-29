package org.jelly.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jelly.code.EncodingCode;
import org.jelly.exception.ExecutetimeException;
import org.jelly.json.Json;
import org.jelly.util.DateUtil;
/**
 * 文件上传接口
 * @author Lychie Fan
 * @since 1.4.0
 */
public abstract class FileUpload extends HttpServlet implements IFileUpload {
	
	private File fileuploadBaseDir;
	private static final String LOCK = "";
	private static final Map<String, Object> config = new HashMap<String, Object>(10, 1f);
	private static final StringBuilder supportedTypeMessage = new StringBuilder();
	private static final long serialVersionUID = 6058840886438690025L;

	/**
	 * <des> 是否允许文件上传。<br>
	 * 若允许上传, 则需返回 ENABLE； <br>
	 * 若不允许上传, 返回 DISABLE 以得到一个不允许上传的信息；<br>
	 * 若返回值既不是 ENABLE 也不是 DISABLE, 则该返回值将以操作成功的方式返还出去；<br>
	 * 例如, 上传的文件在服务器中已经存在, 可直接返回该文件路径, 组件将认为操作成功并将该信息返还出去；</des>
	 * @since 1.4.0
	 */
	protected String isEnableToUpload(File fileuploadBaseDir, InputStream input, HttpServletRequest request){
		return ENABLE;
	}
	
	/**
	 * <des> 重命名文件。<br>
	 * 默认产生 32 位长度的识别码 ( UUID ) 来重命名文件。<br>
	 * 若返回 null, 则不重命名文件而使用原上传文件的名称 ( 支持中文 )。<br>
	 * 若返回其它串内容 ( 无后缀名称的串 ), 则以该串内容来重命名文件名称</des>
	 * @since 1.4.0
	 */
	protected String renameFileName(HttpServletRequest request, String filename){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	// 处理请求
	protected final void service(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String> json = new HashMap<String, String>(2, 1f);
		if(!ServletFileUpload.isMultipartContent(request)){
			notMultipartContent(response, json);
			return ;
		}
		try {
			upload(request, response, json);
		} catch (Throwable e) {
			serverThrowsException(response, json);
			throw new ExecutetimeException(e);
		}
		return ;
	}
	
	// 文件上传逻辑, 支持多文件上传, 多文件上传时, 有一个文件上传成功则认为操作成功
	private void upload(HttpServletRequest request, HttpServletResponse response, Map<String, String> json) throws Throwable {
		String serverUploadPath = getServletContext().getRealPath(SEPARATOR);
		ServletFileUpload fileupload = new ServletFileUpload(new DiskFileItemFactory());
		fileupload.setHeaderEncoding(EncodingCode.UTF_8.toCode());
		List<FileItem> fileItems = fileupload.parseRequest(request);
		if(!hasFilesAreSelected(fileItems)){
			noFilesAreSelected(response, json);
			return ;
		}
		String multiAction = FAIL;
		StringBuilder multiResult = new StringBuilder();
		for(FileItem item : fileItems){
			if(!item.isFormField()){
				String logic = isEnableToUpload(fileuploadBaseDir, item.getInputStream(), request);
				if(logic == ENABLE){
					String result = uploadFile(request, response, json, item, serverUploadPath);
					if(result == null) return ;
					multiAction = SUCCESS;
					multiResult.append(result).append(",");
				}else if(logic != null && logic != DISABLE){
					multiAction = SUCCESS;
					multiResult.append(logic).append(",");
				}else{
					multiResult.append(config.get(DISABLE_TO_UPLOAD)).append(",");
				}
			}
		}
		String result = multiResult.toString().substring(0, multiResult.length() - 1);
		if(multiAction == SUCCESS){
			fileuploadFinish(response, json, result);
		}else{
			disableToUpload(response, json, result);
		}
	}
	
	// 文件上传, 若上传成功, 则返回该文件的绝对地址
	private String uploadFile(HttpServletRequest request, HttpServletResponse response, Map<String, String> json, FileItem item, String serverUploadPath) throws Throwable {
		String[] condition = checkoutCondition(response, json, item);
		if(condition == null) return null;
		String savePath = serverUploadPath + config.get(UPLOAD_DIR) + SEPARATOR + condition[0];
		savePath = buildRuleOfDirectory(savePath);
		String newFilename = renameFileName(request, item.getName());
		newFilename = newFilename == null ? item.getName() : newFilename + "." + condition[1];
		String projectPath = request.getContextPath() + SEPARATOR;
		item.write(new File(savePath, newFilename));
		String filepath = savePath.substring(serverUploadPath.length(), savePath.length());
		if(filepath.contains("\\")){
			filepath = filepath.replaceAll("\\\\", SEPARATOR);
		}
		return projectPath + filepath + newFilename;
	}
	
	// 文件上传之前接受检查 ( 大小是否受限, 类型是否支持 )
	// 检查通过则返还上传文件的类型 ( 图像, 媒体, 还是文件 ) 以及文件后缀名
	private String[] checkoutCondition(HttpServletResponse response, Map<String, String> json, FileItem item){
		String[] condition = new String[2];
		String filename = item.getName();
		int index = filename.lastIndexOf(".");
		String suffix = index == -1 ? null : filename.substring(index + 1);
		String type = getFileType(suffix);
		if(type == null){
			notSupportedFileType(response, json);
			return null;
		}
		long maxSize = Long.valueOf(config.get(MAX_SIZE).toString());
		if(item.getSize() > maxSize){
			overSize(response, json, maxSize);
			return null;
		}
		condition[0] = type;
		condition[1] = suffix;
		return condition;
	}

	// 首次激活时候进行的初始化工作
	@Override
	public final void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(getConfigFile(servletConfig)));
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
		Set<Entry<Object, Object>> set = prop.entrySet();
		for(Entry<Object, Object> e : set){
			config.put(e.getKey().toString(), e.getValue());
		}
		String uploadBaseDir = parseUploadDirConfig();
		parseMaxSizeConifg();
		parseMessageConfig();
		parseImageConfig(uploadBaseDir);
		parseMediaConfig(uploadBaseDir);
		parseFileConfig(uploadBaseDir);
	}
	
	// 获取配置文件
	private File getConfigFile(ServletConfig servletConfig){
		String path = servletConfig.getInitParameter(CONFIG_FILE_PATH);
		if(path.startsWith(CLASS_PATH)){
			path = getClass().getResource(SEPARATOR).getPath() + path.substring(CLASS_PATH.length());
			if(path.startsWith(SEPARATOR) || path.startsWith(File.separator)){
				path = path.substring(1);
			}
		}
		return new File(path);
	}
	
	// 解析配置文件中 uploadDir 配置行信息
	private String parseUploadDirConfig(){
		String dir = config.get(UPLOAD_DIR).toString();
		if(dir == null){
			dir = DEFAULT_UPLOAD_DIR;
			config.put(UPLOAD_DIR, DEFAULT_UPLOAD_DIR);
		}
		String uploadBaseDir = getServletContext().getRealPath(SEPARATOR) + dir + SEPARATOR;
		File uploadDir = new File(uploadBaseDir);
		if(!uploadDir.exists()){
			uploadDir.mkdirs();
		}
		fileuploadBaseDir = new File(uploadBaseDir);
		return uploadBaseDir;
	}

	// 解析配置文件中 maxSize 配置行信息
	private void parseMaxSizeConifg(){
		if(config.containsKey(MAX_SIZE)){
			String[] size = config.get(MAX_SIZE).toString().split("\\*");
			if(size.length > 1){
				long sizeValue = 1;
				for(String value : size){
					sizeValue *= Integer.valueOf(value.trim());
				}
				config.put(MAX_SIZE, String.valueOf(sizeValue));
			}
		}else{
			config.put(MAX_SIZE, String.valueOf(DEFAULT_MAX_SIZE));
		}
	}

	// 解析配置文件中 image 配置行信息
	private void parseImageConfig(String uploadBaseDir){
		if(config.containsKey(IMAGE)){
			String[] images = config.get(IMAGE).toString().split(",");
			String info = Arrays.toString(images).replaceAll("\\[", "");
			info = info.replaceAll("\\]", "").replaceAll(", ", ",");
			supportedTypeMessage.append("\n允许的图片类型：").append(info);
			config.put(IMAGE, images);
			File fileTypeDir = new File(uploadBaseDir + IMAGE);
			if(!fileTypeDir.exists()){
				fileTypeDir.mkdirs();
			}
		}
	}

	// 解析配置文件中 file 配置行信息
	private void parseFileConfig(String uploadBaseDir){
		if(config.containsKey(FILE)){
			String[] files = config.get(FILE).toString().split(",");
			String info = Arrays.toString(files).replaceAll("\\[", "");
			info = info.replaceAll("\\]", "").replaceAll(", ", ",");
			supportedTypeMessage.append("\n允许的文件类型：").append(info);
			config.put(FILE, files);
			File fileTypeDir = new File(uploadBaseDir + FILE);
			if(!fileTypeDir.exists()){
				fileTypeDir.mkdirs();
			}
		}
	}

	// 解析配置文件中 media 配置行信息
	private void parseMediaConfig(String uploadBaseDir){
		if(config.containsKey(MEDIA)){
			String[] medias = config.get(MEDIA).toString().split(",");
			String info = Arrays.toString(medias).replaceAll("\\[", "");
			info = info.replaceAll("\\]", "").replaceAll(", ", ",");
			supportedTypeMessage.append("\n允许的媒体类型：").append(info);
			config.put(MEDIA, medias);
			File fileTypeDir = new File(uploadBaseDir + MEDIA);
			if(!fileTypeDir.exists()){
				fileTypeDir.mkdirs();
			}
		}
	}

	// 解析配置文件中返还信息提示配置行信息
	private void parseMessageConfig(){
		if(config.get(OVER_SIZE) == null){
			config.put(OVER_SIZE, DEFAULT_OVER_SIZE_MESSAGE);
		}
		if(config.get(DISABLE_TO_UPLOAD) == null){
			config.put(DISABLE_TO_UPLOAD, DEFAULT_DISABLE_TO_UPLOAD_MESSAGE);
		}
		if(config.get(NO_FILES_ARE_SELECTED) == null){
			config.put(NO_FILES_ARE_SELECTED, DEFAULT_NO_FILES_ARE_SELECTED_MESSAGE);
		}
		if(config.get(NOT_SUPPORTED_FILE_TYPE) == null){
			config.put(NOT_SUPPORTED_FILE_TYPE, DEFAULT_NOT_SUPPORTED_FILE_TYPE_MESSAGE);
		}
		if(config.get(SERVER_THROWS_EXCEPTION) == null){
			config.put(SERVER_THROWS_EXCEPTION, DEFAULT_SERVER_THROWS_EXCEPTION_MESSAGE);
		}
	}

	// 获取上传文件的类型 ( 图像, 媒体, 还是文件 )
	private String getFileType(String suffix){
		if(suffix == null){
			return null;
		}
		if(config.containsKey(IMAGE)){
			String[] imageType = (String[])config.get(IMAGE);
			for(String type : imageType){
				if(suffix.equalsIgnoreCase(type.trim())){
					return IMAGE;
				}
			}
		}
		if(config.containsKey(MEDIA)){
			String[] mediaType = (String[])config.get(MEDIA);
			for(String type : mediaType){
				if(suffix.equalsIgnoreCase(type.trim())){
					return MEDIA;
				}
			}
		}
		if(config.containsKey(FILE)){
			String[] fileType = (String[])config.get(FILE);
			for(String type : fileType){
				if(suffix.equalsIgnoreCase(type.trim())){
					return FILE;
				}
			}
		}
		return null;
	}
	
	// 检查是否有上传文件处理
	private boolean hasFilesAreSelected(List<FileItem> fileItems){
		if(fileItems != null && fileItems.size() > 0){
			for(FileItem fileItem : fileItems){
				if(!fileItem.isFormField()){
					return true;
				}
			}
		}
		return false;
	}
	
	// 构建规则的存储路径
	private String buildRuleOfDirectory(String basepath){
		basepath = trimPathSeparator(basepath);
		File directory = new File(basepath + DateUtil.getCurrentDateString("yyyy/MM/dd"));
		synchronized (LOCK) {
			if(!directory.exists()){
				directory.mkdirs();
			}
		}
		return trimPathSeparator(directory.getAbsolutePath());
	}
	
	// 整理路径分隔符
	private String trimPathSeparator(String basepath){
		if(!basepath.endsWith(SEPARATOR) && !basepath.endsWith(File.separator)){
			basepath += SEPARATOR;
		}
		return basepath.replaceAll(File.separator + File.separator, SEPARATOR);
	}

	// 没有文件被选择信息
	private void noFilesAreSelected(HttpServletResponse response, Map<String, String> json){
		json.put(ACTION, FAIL);
		json.put(RESULT, config.get(NO_FILES_ARE_SELECTED).toString());
		Json.getDefault().outputObject(json, response);
	}

	// 服务器端抛出异常信息
	private void serverThrowsException(HttpServletResponse response, Map<String, String> json){
		json.put(ACTION, FAIL);
		json.put(RESULT, config.get(SERVER_THROWS_EXCEPTION).toString());
		Json.getDefault().outputObject(json, response);
	}

	// 上传文件大小超出限制信息
	private void overSize(HttpServletResponse response, Map<String, String> json, long size){
		json.put(ACTION, FAIL);
		json.put(RESULT, config.get(OVER_SIZE) + "。最大支持" + (size / 1024 / 1024) + "M");
		Json.getDefault().outputObject(json, response);
	}

	// 上传的文件类型不被支持信息 
	private void notSupportedFileType(HttpServletResponse response, Map<String, String> json){
		json.put(ACTION, FAIL);
		json.put(RESULT, config.get(NOT_SUPPORTED_FILE_TYPE) + supportedTypeMessage.toString());
		Json.getDefault().outputObject(json, response);
	}

	// 文件不允许上传信息
	private void disableToUpload(HttpServletResponse response, Map<String, String> json, String result){
		json.put(ACTION, FAIL);
		json.put(RESULT, result);
		Json.getDefault().outputObject(json, response);
	}

	// 文件上传成功信息
	private void fileuploadFinish(HttpServletResponse response, Map<String, String> json, String result){
		json.put(ACTION, SUCCESS);
		json.put(RESULT, result);
		Json.getDefault().outputObject(json, response);
	}

	// 不支持文件上传信息
	private void notMultipartContent(HttpServletResponse response, Map<String, String> json){
		json.put(ACTION, FAIL);
		json.put(RESULT, DEFAULT_NOT_MULTIPART_CONTENT_MESSAGE);
		Json.getDefault().outputObject(json, response);
	}

}