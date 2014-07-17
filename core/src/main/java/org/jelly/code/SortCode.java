package org.jelly.code;
/**
 * 排序类型枚举
 * @author Lychie Fan
 * @since 1.0.0
 */
public enum SortCode implements Code {

	/**
	 * asc
	 */
	ASC("asc"), 

	/**
	 * desc
	 */
	DESC("desc");
	
	private final String code;
	
	private SortCode(String code){
		this.code = code;
	}
	
	/**
	 * <des> 得到与参数匹配的枚举实例 </des>
	 * @param code 字面值或代号值
	 * @return 若匹配则返回匹配到枚举实例, 否则返回null
	 * @since 1.0.0
	 */
	public static SortCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(SortCode sortCode : SortCode.values()){
			if(sortCode.code.equals(code) || sortCode.name().equals(code)){
				return sortCode;
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
	public static boolean equalsCode(SortCode e1, SortCode e2){
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
	public String toCode() {
		return code;
	}
}