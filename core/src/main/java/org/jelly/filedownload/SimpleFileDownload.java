package org.jelly.filedownload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jelly.util.FileUtil;
import org.jelly.util.StringUtil;
/**
 * 文件下载
 * @author Lychie Fan
 * @since 1.4.0
 */
public class SimpleFileDownload extends HttpServlet {

	private static String fileRootPath;
	private static String path;
	private static final long serialVersionUID = 5974440172983895500L;

	@Override
	protected final void service(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		File file = getTargetFile(request.getParameter("filename"));
		response.reset();
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Length", String.valueOf(file.length()));
		String filename = StringUtil.getStringISO1(StringUtil.getBytesUTF8(file.getName()));
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		OutputStream out = new BufferedOutputStream(response.getOutputStream());
		FileUtil.copyFile(in, out);
		return ;
	}

	@Override
	public final void init(ServletConfig config) throws ServletException {
		super.init(config);
		path = getServletContext().getRealPath("/");
		fileRootPath = config.getInitParameter("fileRootPath");
		if(fileRootPath != null){
			StringBuilder builder = new StringBuilder();
			builder.append(path).append(fileRootPath).append("/");
			fileRootPath = builder.toString();
		}
	}
	
	private File getTargetFile(String name){
		return new File(fileRootPath == null ? path + name : fileRootPath + name);
	}
	
}