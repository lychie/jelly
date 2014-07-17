package org.jelly.examples.util;

import org.jelly.code.EncodingCode;
import org.jelly.helper.Testing;
import org.jelly.util.StringUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class StringUtilDemo {

	static final String SOURCE = "An explorer is a friend to all, be it plants or fish or tiny mole";
	static final String SEPARATOR = " or ";
	
	/**
	 * 判定是否为空
	 */
	@Test
	public void isEmpty(){
		Testing.printlnObject(StringUtil.isEmpty(null));
		Testing.printlnObject(StringUtil.isEmpty(""));
		Testing.printlnObject(StringUtil.isEmpty(" "));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substring(){
		Testing.printlnObject(StringUtil.substring(SOURCE, 1));
		Testing.printlnObject(StringUtil.substring(SOURCE, -1));
		Testing.printlnObject(StringUtil.substring(SOURCE, 1, -1));
		Testing.printlnObject(StringUtil.substring(SOURCE, -2, -1));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substringBefore(){
		Testing.printlnObject(StringUtil.substringBefore(SOURCE, SEPARATOR));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substringBeforeWith(){
		Testing.printlnObject(StringUtil.substringBeforeWith(SOURCE, SEPARATOR));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substringBeforeLast(){
		Testing.printlnObject(StringUtil.substringBeforeLast(SOURCE, SEPARATOR));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substringBeforeLastWith(){
		Testing.printlnObject(StringUtil.substringBeforeLastWith(SOURCE, SEPARATOR));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substringAfter(){
		Testing.printlnObject(StringUtil.substringAfter(SOURCE, SEPARATOR));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substringAfterWith(){
		Testing.printlnObject(StringUtil.substringAfterWith(SOURCE, SEPARATOR));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substringAfterLast(){
		Testing.printlnObject(StringUtil.substringAfterLast(SOURCE, SEPARATOR));
	}

	/**
	 * 切割字符串
	 */
	@Test
	public void substringAfterLastWith(){
		Testing.printlnObject(StringUtil.substringAfterLastWith(SOURCE, SEPARATOR));
	}

	/**
	 * 首字母大写
	 */
	@Test
	public void toFirstLetterUpperCase(){
		String source = "when the sun shines we shine together";
		Testing.printlnObject(StringUtil.toFirstLetterUpperCase(source));
	}

	/**
	 * 编码
	 */
	@Test
	public void getBytes(){
		Testing.printlnObject(StringUtil.getBytes(SOURCE, EncodingCode.UTF_8.toCode()));
	}

	/**
	 * 编码
	 */
	@Test
	public void getBytes_(){
		Testing.printlnObject(StringUtil.getBytesISO1(SOURCE));
		Testing.printlnObject(StringUtil.getBytesUTF8(SOURCE));
		Testing.printlnObject(StringUtil.getBytesGBK(SOURCE));
		Testing.printlnObject(StringUtil.getBytesGB2312(SOURCE));
		Testing.printlnObject(StringUtil.getBytesASCII(SOURCE));
	}

	/**
	 * 解码
	 */
	@Test
	public void getString(){
		byte[] bytes = {-28, -69, -118, -27, -92, -87, -28, -67, -96, -27, -66, -82, -25, -84, -111, -28, -70, -122, -27, -112, -105, -17, -68, -97};
		Testing.printlnObject(StringUtil.getString(bytes, EncodingCode.UTF_8.toCode()));
		Testing.printlnObject(StringUtil.getStringGB2312(bytes));
		Testing.printlnObject(StringUtil.getStringGBK(bytes));
		Testing.printlnObject(StringUtil.getStringISO1(bytes));
		Testing.printlnObject(StringUtil.getStringUTF8(bytes));
	}

	/**
	 * 占位符
	 */
	@Test
	public void parse(){
		String result = StringUtil.parse("name: ?", "Lychie Fan");
		Testing.printlnObject(result);
		result = StringUtil.parse("info(?, ?)", "Lychie Fan", "Jelly");
		Testing.printlnObject(result);
		result = StringUtil.parse("info(?, ?)", "Lychie Fan", "Jelly", "1.0.0");
		Testing.printlnObject(result);
		result = StringUtil.parse("info(?, ?, ?)", "Lychie Fan", "Jelly");
		Testing.printlnObject(result);
	}
}