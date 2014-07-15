package org.jelly.code;
/**
 * 常用字符编码集类型枚举
 * @author Lychie Fan
 * @since 1.0.0
 */
public enum EncodingCode implements Code {

	/**
	 * GBK
	 */
	GBK("GBK"), 

	/**
	 * GB2312
	 */
	GB2312("GB2312"), 

	/**
	 * UTF-8
	 */
	UTF_8("UTF-8"), 

	/**
	 * UTF-16
	 */
	UTF_16("UTF-16"), 

	/**
	 * US-ASCII
	 */
	ASCII("US-ASCII"), 

	/**
	 * ISO-8859-1
	 */
	ISO_8859_1("ISO-8859-1");
	
	private final String code;
	
	private EncodingCode(String code){
		this.code = code;
	}
	
	/**
	 * <des> 得到与参数匹配的枚举实例 </des>
	 * @param code 字面值或代号值
	 * @return 若匹配则返回匹配到枚举实例, 否则返回null
	 * @since 1.0.0
	 */
	public static EncodingCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(EncodingCode encodingCode : EncodingCode.values()){
			if(encodingCode.code.equalsIgnoreCase(code) || encodingCode.name().equals(code)){
				return encodingCode;
			}
		}
		return null;
	}
	
	/**
	 * <des> 判定两个实例是否相等 </des>
	 * @param e1 实例1
	 * @param e2 实例2
	 * @return 相等则返回true, 否则返回false
	 * @since 1.0.0
	 */
	public static boolean equalsCode(EncodingCode e1, EncodingCode e2){
		if(e1 == null && e2 == null){
			return true;
		}else if(e1 != null && e2 != null){
			return e1.ordinal() == e2.ordinal();
		}else{
			return false;
		}
	}

	@Override
	public String toFace(){
		return this.name();
	}

	@Override
	public String toCode(){
		return code;
	}
	
}