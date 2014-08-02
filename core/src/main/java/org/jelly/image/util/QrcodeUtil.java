package org.jelly.image.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.jelly.exception.ExecutetimeException;
import org.jelly.image.helper.QrcodeConfigHelper;
import org.jelly.util.FileUtil;
import org.jelly.util.StringUtil;
/**
 * 二维码工具
 * @author Lychie Fan
 * @since 1.7.0
 */
public class QrcodeUtil implements QrcodeConfigHelper {

	private String text;
	private int width = QrcodeConfigHelper.width;
	private int height = QrcodeConfigHelper.height;
	private Color foreground = QrcodeConfigHelper.foreground;
	private Color background = QrcodeConfigHelper.background;
	private String imageType = QrcodeConfigHelper.imageType;
	private char errorCorrect = QrcodeConfigHelper.errorCorrect;
	private com.swetake.util.Qrcode qrcode = new com.swetake.util.Qrcode();
	
	private QrcodeUtil(String text){
		this.text = text;
		qrcode.setQrcodeVersion(size);
		qrcode.setQrcodeEncodeMode(encodeMode);
		qrcode.setQrcodeErrorCorrect(errorCorrect);
	}
	
	/**
	 * <des> 二维码实例 </des>
	 * @since 1.7.0
	 */
	public static QrcodeUtil from(String text){
		return new QrcodeUtil(text);
	}

	/**
	 * <des> 二维码图片类型 </des>
	 * @since 1.7.0
	 */
	public QrcodeUtil to(ImageType imageType) {
		this.imageType = imageType.name().toLowerCase();
		return this;
	}
	
	/**
	 * <des> 生成二维码图片到目录 </des>
	 * @since 1.7.0
	 */
	public File file(String dirpath){
		return file(dirpath, buildName());
	}
	
	/**
	 * <des> 生成二维码图片到文件 </des>
	 * @since 1.7.0
	 */
	public File file(String dirpath, String name){
		try {
			return generateImage(new File(dirpath, name));
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 写出二维码图片 </des>
	 * @since 1.7.0
	 */
	public void write(OutputStream out){
		try {
			generateImage(out);
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 二维码文件输出流 </des>
	 * @since 1.7.0
	 */
	public OutputStream stream() {
		buildTempDir();
		return FileUtil.getFileOutputStream(file(tempDir));
	}
	
	// 创建二维码
	private BufferedImage createQrcode() throws Throwable {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics2D graphics = createGraphics(image);
		boolean[][] code = qrcode.calQrcode(StringUtil.getBytesUTF8(text));
		int pixoff = 1;
		for(int i = 0; i < code.length; i++){
			for(int j = 0; j < code.length; j++){
				if(code[j][i]){
					graphics.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
				}
			}
		}
		graphics.dispose(); image.flush();
		return image;
	}
	
	// 生成图片
	private File generateImage(File file) throws Throwable {
		BufferedImage image = createQrcode();
		ImageIO.write(image, imageType, file);
		return file;
	}
	
	// 生成图片
	private void generateImage(OutputStream out) throws Throwable {
		BufferedImage image = createQrcode();
		ImageIO.write(image, imageType, out);
		FileUtil.closeStream(out);
	}
	
	// 创建画笔
	private Graphics2D createGraphics(BufferedImage image){
		Graphics2D graphics = image.createGraphics();
		graphics.setBackground(background);
		graphics.clearRect(0, 0, image.getWidth(), image.getHeight());
		graphics.setColor(foreground);
		return graphics;
	}
	
	// 构建文件名
	private String buildName(){
		StringBuilder builder = new StringBuilder();
		builder.append(UUID.randomUUID().toString().replaceAll("-", ""));
		return builder.append(".").append(imageType).toString();
	}
	
	// 构建临时目录
	private void buildTempDir(){
		FileUtil.delete(new File(tempDir));
		FileUtil.createDir(tempDir);
	}
	
	/**
	 * <des> 设置生成的图片大小，默认值为 7 </des>
	 * @since 1.7.0
	 */
	public void setSize(int size){
		width = 67 + 12 * (size - 1);
		height = width;
		qrcode.setQrcodeVersion(size);
	}
	
	/**
	 * <des> 设置二维码容错率 </des>
	 * @since 1.7.0
	 */
	public void setErrorCorrect(QrcodeErrorCorrect qrcodeErrorCorrect){
		qrcode.setQrcodeErrorCorrect(qrcodeErrorCorrect.name().charAt(0));
	}

	/**
	 * <des> 设置二维码图片的前景颜色，默认为黑色 </des>
	 * @since 1.7.0
	 */
	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}

	/**
	 * <des> 设置二维码图片的背景颜色，默认为白色 </des>
	 * @since 1.7.0
	 */
	public void setBackground(Color background) {
		this.background = background;
	}
	
	public enum QrcodeErrorCorrect { L, M, Q, H; }
	
	public enum ImageType { PNG, JPG, GIF, BMP, JPEG }
	
}