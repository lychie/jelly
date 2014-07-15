package org.jelly.code;
/**
 * 日期格式化模式类型枚举
 * @author Lychie Fan
 * @since 1.0.0
 */
public enum DateFormatCode implements Code {

	/**
	 * yyyMMdd
	 */
	SHORT("yyyMMdd"), 

	/**
	 * yyyy-MM-dd
	 */
	SHORT_HYPHEN("yyyy-MM-dd"), 

	/**
	 * yyyy/MM/dd
	 */
	SHORT_VIRGULE("yyyy/MM/dd"), 

	/**
	 * yyyMMddHHmmss
	 */
	LONG("yyyMMddHHmmss"), 

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	LONG_HYPHEN("yyyy-MM-dd HH:mm:ss"), 

	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	LONG_VIRGULE("yyyy/MM/dd HH:mm:ss"), 

	/**
	 * yyyMMddHHmmssSSS
	 */
	FULL("yyyMMddHHmmssSSS"), 

	/**
	 * yyyy-MM-dd HH:mm:ss:SSS
	 */
	FULL_HYPHEN("yyyy-MM-dd HH:mm:ss:SSS"), 

	/**
	 * yyyy/MM/dd HH:mm:ss:SSS
	 */
	FULL_VIRGULE("yyyy/MM/dd HH:mm:ss:SSS");
	
	private final String code;
	
	private DateFormatCode(String code){
		this.code = code;
	}
	
	/**
	 * <des> 得到与参数匹配的枚举实例 </des>
	 * @param code 字面值或代号值
	 * @return 若匹配则返回匹配到枚举实例, 否则返回null
	 * @since 1.0.0
	 */
	public static DateFormatCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(DateFormatCode dateFormatCode : DateFormatCode.values()){
			if(dateFormatCode.code.equals(code) || dateFormatCode.name().equals(code)){
				return dateFormatCode;
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
	public static boolean equalsCode(DateFormatCode e1, DateFormatCode e2){
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