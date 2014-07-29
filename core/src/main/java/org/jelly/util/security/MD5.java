package org.jelly.util.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import org.jelly.code.IndexCode;
import org.jelly.exception.ExecutetimeException;
import org.jelly.util.FileUtil;
/**
 * MD5 信息摘要
 * @author Lychie Fan
 * @since 1.4.0
 */
public final class MD5 {
	
	private static final int BUFFER_SIZE = 1024 * 1024 / 4;
	private static final char[] hexDigits = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
	};
	
	private MD5(){}
	
	/**
	 * <des> md5 信息摘要 </des>
	 * @since 1.4.0
	 */
	public static String encrypt(String text){
		try {
			if(text == null) return null;
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes());
			return byteArrayToHex(md5.digest());
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> md5 文件信息摘要 </des>
	 * @since 1.4.0
	 */
	public static String encrypt(File file){
		try {
			return encrypt(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ExecutetimeException(e);
		}
	}

	/**
	 * <des> md5 输入流信息摘要 </des>
	 * @since 1.4.0
	 */
	public static String encrypt(InputStream in){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			int read;
			byte[] buffer = new byte[BUFFER_SIZE];
			while((read = in.read(buffer)) != IndexCode.EOF.toCode()){
				md5.update(buffer, 0, read);
			}
			return byteArrayToHex(md5.digest());
		} catch (Throwable e) {
			throw new ExecutetimeException(e);
		} finally {
			FileUtil.closeStream(in);
		}
	}
	
	// byte array to hex
	private static String byteArrayToHex(byte[] bytes){
		char[] chars = new char[bytes.length * 2];
		int index = 0;
		for(byte by : bytes){
			chars[index++] = hexDigits[by >>> 4 & 0xf];
			chars[index++] = hexDigits[by & 0xf];
		}
		return new String(chars);
	}
}