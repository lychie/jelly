package org.jelly.util;

import java.nio.charset.Charset;
import org.jelly.code.EncodingCode;
import org.jelly.code.IndexCode;
/**
 * 封装了字符串相关操作的工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public class StringUtil {

	private static final String PLACEHOLDER = "\\?";
	
	private StringUtil(){}
	
	/**
	 * <des> 判断对象是否为空 </des>
	 * @param source 源串
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean isEmpty(String source) {
		return source == null || source.length() == 0;
	}
	
	/**
	 * <des> 判断对象是否不为空 </des>
	 * @param source 源串
	 * @return true or false
	 * @since 1.0.0
	 */
	public static boolean isNotEmpty(String source) {
		return !isEmpty(source);
	}
	
	/**
	 * <des> 分割字符串 </des>
	 * @param source 源串
	 * @param beginIndex 开始索引值(可为负数, 负数代表从后向前的索引值, 负数的索引值从-1开始)
	 * @return 子串
	 * @since 1.0.0
	 */
	public static String substring(String source, int beginIndex){
		return substring(source, beginIndex, source.length());
	}
	
	/**
	 * <des> 分割字符串 </des>
	 * @param source 源串
	 * @param beginIndex 开始索引值(可为负数, 负数代表从后向前的索引值, 负数的索引值从-1开始)
	 * @param endIndex 结束索引值(可为负数, 负数代表从后向前的索引值, 负数的索引值从-1开始)
	 * @return 子串
	 * @since 1.0.0
	 */
	public static String substring(String source, int beginIndex, int endIndex){
		if(isEmpty(source)) return source;
		int length = source.length();
		if(beginIndex < 0){
			beginIndex += length;
		}
		if(endIndex < 0){
			endIndex += length;
		}
		return source.substring(beginIndex, endIndex);
	}
	
	/**
	 * <des> 切割源串, 索引从0开始到源串中参数串第一次出现的位置结束, 得到的子串不包括参数串。 </des>
	 * @param source 源串
	 * @param substring 参数串
	 * @return 子串, 如果源串中不含有参数串, 则返回源串本身
	 * @since 1.0.0
	 */
	public static String substringBefore(String source, String substring) {
		int index = source.indexOf(substring);
		return index == IndexCode.INDEX_NOT_FOUND.toCode() ? source : source.substring(0, index);
	}
	
	/**
	 * <des> 切割源串, 索引从0开始到源串中参数串第一次出现的位置结束, 得到的子串包括参数串。 </des>
	 * @param source 源串
	 * @param substring 参数串
	 * @return 子串, 如果源串中不含有参数串, 则返回源串本身
	 * @since 1.0.0
	 */
	public static String substringBeforeWith(String source, String substring) {
		int index = source.indexOf(substring);
		return index == IndexCode.INDEX_NOT_FOUND.toCode() ? source : source.substring(0, index + substring.length());
	}
	
	/**
	 * <des> 切割源串, 索引从0开始到源串中参数串最后一次出现的位置结束, 得到的子串不包括参数串。 </des>
	 * @param source 源串
	 * @param substring 参数串
	 * @return 子串, 如果源串中不含有参数串, 则返回源串本身
	 * @since 1.0.0
	 */
	public static String substringBeforeLast(String source, String substring) {
		int index = source.lastIndexOf(substring);
		return index == IndexCode.INDEX_NOT_FOUND.toCode() ? source : source.substring(0, index);
	}

	/**
	 * <des> 切割源串, 索引从0开始到源串中参数串最后一次出现的位置结束, 得到的子串包括参数串。 </des>
	 * @param source 源串
	 * @param substring 参数串
	 * @return 子串, 如果源串中不含有参数串, 则返回源串本身
	 * @since 1.0.0
	 */
	public static String substringBeforeLastWith(String source, String substring) {
		int index = source.lastIndexOf(substring);
		return index == IndexCode.INDEX_NOT_FOUND.toCode() ? source : source.substring(0, index + substring.length());
	}
	
	/**
	 * <des> 切割源串, 索引从源串中参数串第一次出现的位置开始到源串最后结束, 得到的子串不包括参数串。 </des>
	 * @param source 源串
	 * @param substring 参数串
	 * @return 子串, 如果源串中不含有参数串, 则返回源串本身
	 * @since 1.0.0
	 */
	public static String substringAfter(String source, String substring) {
		int index = source.indexOf(substring);
		return index == IndexCode.INDEX_NOT_FOUND.toCode() ? source : source.substring(index + substring.length());
	}

	/**
	 * <des> 切割源串, 索引从源串中参数串第一次出现的位置开始到源串最后结束, 得到的子串包括参数串。 </des>
	 * @param source 源串
	 * @param substring 参数串
	 * @return 子串, 如果源串中不含有参数串, 则返回源串本身
	 * @since 1.0.0
	 */
	public static String substringAfterWith(String source, String substring) {
		int index = source.indexOf(substring);
		return index == IndexCode.INDEX_NOT_FOUND.toCode() ? source : source.substring(index);
	}

	/**
	 * <des> 切割源串, 索引从源串中参数串最后一次出现的位置开始到源串最后结束, 得到的子串不包括参数串。 </des>
	 * @param source 源串
	 * @param substring 参数串
	 * @return 子串, 如果源串中不含有参数串, 则返回源串本身
	 * @since 1.0.0
	 */
	public static String substringAfterLast(String source, String substring) {
		int index = source.lastIndexOf(substring);
		return index == IndexCode.INDEX_NOT_FOUND.toCode() ? source : source.substring(index + substring.length());
	}

	/**
	 * <des> 切割源串, 索引从源串中参数串最后一次出现的位置开始到源串最后结束, 得到的子串包括参数串。 </des>
	 * @param source 源串
	 * @param substring 参数串
	 * @return 子串, 如果源串中不含有参数串, 则返回源串本身
	 * @since 1.0.0
	 */
	public static String substringAfterLastWith(String source, String substring) {
		int index = source.lastIndexOf(substring);
		return index == IndexCode.INDEX_NOT_FOUND.toCode() ? source : source.substring(index);
	}
	
	/**
	 * <des> 将源串编码为一个字节序列 </des>
	 * @param source 源串
	 * @param charset 字符集
	 * @return 编码后的字节序列
	 * @see java.nio.charset.Charset
	 * @since 1.0.0
	 */
	public static byte[] getBytes(String source, Charset charset){
		return source == null ? null : source.getBytes(charset);
	}
	
	/**
	 * <des> 将源串编码为一个字节序列 </des>
	 * @param source 源串
	 * @param charset 字符集
	 * @return 编码后的字节序列
	 * @see org.jelly.code.EncodingCode
	 * @since 1.0.0
	 */
	public static byte[] getBytes(String source, String charset){
        return getBytes(source, Charset.forName(charset));
	}
	
	/**
	 * <des> 将源串编码为一个 ISO-8859-1 字节序列 </des>
	 * @param source 源串
	 * @return  ISO-8859-1 字节序列
	 * @since 1.0.0
	 */
	public static byte[] getBytesISO1(String source){
		return getBytes(source, Charset.forName(EncodingCode.ISO_8859_1.toCode()));
	}
	
	/**
	 * <des> 将源串编码为一个 UTF-8 字节序列 </des>
	 * @param source 源串
	 * @return UTF-8 字节序列
	 * @since 1.0.0
	 */
	public static byte[] getBytesUTF8(String source){
		return getBytes(source, Charset.forName(EncodingCode.UTF_8.toCode()));
	}
	
	/**
	 * <des> 将源串编码为一个 GBK 字节序列 </des>
	 * @param source 源串
	 * @return GBK 字节序列
	 * @since 1.0.0
	 */
	public static byte[] getBytesGBK(String source){
		return getBytes(source, Charset.forName(EncodingCode.GBK.toCode()));
	}
	
	/**
	 * <des> 将源串编码为一个 GB2312 字节序列 </des>
	 * @param source 源串
	 * @return GB2312 字节序列
	 * @since 1.0.0
	 */
	public static byte[] getBytesGB2312(String source){
		return getBytes(source, Charset.forName(EncodingCode.GB2312.toCode()));
	}
	
	/**
	 * <des> 将源串编码为一个 ASCII 字节序列 </des>
	 * @param source 源串
	 * @return ASCII 字节序列
	 * @since 1.0.0
	 */
	public static byte[] getBytesASCII(String source){
		return getBytes(source, Charset.forName(EncodingCode.ASCII.toCode()));
	}
	
	/**
	 * <des> 使用参数指定的字符集解码字节序列 </des>
	 * @param bytes 字节序列
	 * @param charset 字符集
	 * @return 解码后的字符串
	 * @see java.nio.charset.Charset
	 * @since 1.0.0
	 */
	public static String getString(byte[] bytes, Charset charset) {
        return bytes == null ? null : new String(bytes, charset);
    }
	
	/**
	 * <des> 使用参数指定的字符集解码字节序列 </des>
	 * @param bytes 字节序列
	 * @param charset 字符集
	 * @return 解码后的字符串
	 * @see org.jelly.code.EncodingCode
	 * @since 1.0.0
	 */
	public static String getString(byte[] bytes, String charset) {
        return getString(bytes, Charset.forName(charset));
    }
	
	/**
	 * <des> 使用 ISO-8859-1 解码字节序列 </des>
	 * @param bytes 字节序列
	 * @return 解码后的字符串
	 * @since 1.0.0
	 */
	public static String getStringISO1(byte[] bytes) {
        return getString(bytes, Charset.forName(EncodingCode.ISO_8859_1.toCode()));
    }
	
	/**
	 * <des> 使用 UTF-8 解码字节序列 </des>
	 * @param bytes 字节序列
	 * @return 解码后的字符串
	 * @since 1.0.0
	 */
	public static String getStringUTF8(byte[] bytes) {
        return getString(bytes, Charset.forName(EncodingCode.UTF_8.toCode()));
    }
	
	/**
	 * <des> 使用 GBK 解码字节序列 </des>
	 * @param bytes 字节序列
	 * @return 解码后的字符串
	 * @since 1.0.0
	 */
	public static String getStringGBK(byte[] bytes) {
        return getString(bytes, Charset.forName(EncodingCode.GBK.toCode()));
    }
	
	/**
	 * <des> 使用 GB2312 解码字节序列 </des>
	 * @param bytes 字节序列
	 * @return 解码后的字符串
	 * @since 1.0.0
	 */
	public static String getStringGB2312(byte[] bytes) {
        return getString(bytes, Charset.forName(EncodingCode.GB2312.toCode()));
    }
	
	/**
	 * <des> 返还一个由参数顺序替换源串占位符(?)的字符串 </des>
	 * @param source 源串
	 * @param values 占位符值列表
	 * @return 值替换占位符后的字符串
	 * @since 1.0.0
	 */
	public static String parse(String source, Object... values){
		if(source == null || ArrayUtil.isEmpty(values)){
			return source;
		}
		for(int i = 0; i < values.length; i++){
			source = source.replaceFirst(PLACEHOLDER, values[i].toString());
		}
		return source;
	}
	
	/**
	 * <des> 首字母大写 </des>
	 * @param source 源串
	 * @return 结果串
	 * @since 1.0.0
	 */
	public static String toFirstLetterUpperCase(String source){
		return source.substring(0, 1).toUpperCase() + source.substring(1);
	}
	
	/**
	 * <des> 使用参数分隔符将源串中大写字母变换成小写并保留该字符 </des>
	 * @param source 源串
	 * @param separator 分隔符
	 * @return 结果串
	 * @since 1.0.0
	 */
	public static String convertUpperCaseLetter(String source, String separator) {
		if(source.equals(source.toLowerCase()) || separator == null){
			return source;
		}
		char[] chars = source.toCharArray();
		StringBuilder builder = new StringBuilder();
		for(char ch : chars){
			if(ch >= 'A' && ch <= 'Z'){
				builder.append(separator).append(Character.toLowerCase(ch));
			}else{
				builder.append(ch);
			}
		}
		return builder.toString();
	}
}