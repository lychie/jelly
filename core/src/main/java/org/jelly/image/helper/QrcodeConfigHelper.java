package org.jelly.image.helper;

import java.awt.Color;
import org.jelly.code.SystemCode;
import org.jelly.helper.SystemHelper;
/**
 * 二维码配置项助手
 * @author Lychie Fan
 * @since 1.7.0
 */
public interface QrcodeConfigHelper {

	/**
	 * 宽度
	 */
	int width = 139;
	/**
	 * 高度
	 */
	int height = width;
	/**
	 * 二维码图片大小
	 */
	int size = 7;
	/**
	 * 二维码图片前景颜色
	 */
	Color foreground = Color.BLACK;
	/**
	 * 二维码图片背景颜色
	 */
	Color background = Color.WHITE;
	/**
	 * 二维码图片类型
	 */
	String imageType = "png";
	/**
	 * 二维码图片容错率
	 */
	char errorCorrect = 'H';
	/**
	 * 编码
	 */
	char encodeMode = 'B';
	/**
	 * 临时目录
	 */
	String tempDir = SystemHelper.getProperty(SystemCode.JAVA_IO_TMPDIR) + "\\jelly_temp\\";
	
}