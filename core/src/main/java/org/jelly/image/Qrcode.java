package org.jelly.image;

import java.awt.Color;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jelly.exception.ExecutetimeException;
import org.jelly.image.util.QrcodeUtil;
import org.jelly.image.util.QrcodeUtil.ImageType;
import org.jelly.image.util.QrcodeUtil.QrcodeErrorCorrect;
import org.jelly.util.FieldUtil;
import org.jelly.util.StringUtil;
/**
 * 二维码
 * @author Lychie Fan
 * @since 1.7.0
 */
public class Qrcode extends HttpServlet {
	
	private int size = 7;
	private Color foreground = Color.BLACK;
	private Color background = Color.WHITE;
	private ImageType imageType = ImageType.JPG;
	private QrcodeErrorCorrect errorCorrect = QrcodeErrorCorrect.H;
	private static final long serialVersionUID = 1829054065610250871L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String type = imageType.name().toLowerCase().replace("jpg", "jpeg");
			response.setContentType("image/" + type);
			createQrcode(request.getParameter("text")).write(response.getOutputStream());
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
		return ;
	}
	
	// 创建二维码
	private QrcodeUtil createQrcode(String text){
		QrcodeUtil qrcode = QrcodeUtil.from(text).to(imageType);
		qrcode.setSize(size);
		qrcode.setForeground(foreground);
		qrcode.setBackground(background);
		qrcode.setErrorCorrect(errorCorrect);
		return qrcode;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String size = config.getInitParameter("size");
		String imageType = config.getInitParameter("imageType");
		String foreground = config.getInitParameter("foreground");
		String background = config.getInitParameter("background");
		String errorCorrect = config.getInitParameter("errorCorrect");
		if(StringUtil.isNotEmpty(size)){
			this.size = Integer.parseInt(size);
		}
		if(StringUtil.isNotEmpty(imageType)){
			this.imageType = ImageType.valueOf(imageType.toUpperCase());
		}
		if(StringUtil.isNotEmpty(errorCorrect)){
			this.errorCorrect = QrcodeErrorCorrect.valueOf(errorCorrect.toUpperCase());
		}
		if(StringUtil.isNotEmpty(foreground)){
			this.foreground = getColor(foreground);
		}
		if(StringUtil.isNotEmpty(background)){
			this.background = getColor(background);
		}
	}
	
	// 颜色
	private Color getColor(String arg){
		if(arg.contains(",")){
			String[] rgb = arg.split(",");
			int r = Integer.parseInt(rgb[0].trim());
			int g = Integer.parseInt(rgb[1].trim());
			int b = Integer.parseInt(rgb[2].trim());
			return new Color(r, g, b);
		}
		return FieldUtil.getFieldValue(Color.class, arg.toUpperCase());
	}
	
}