package org.jelly.code;
/**
 * 布尔类型枚举
 * @author Lychie Fan
 * @since 1.0.0
 */
public enum BoolCode implements Code {

	/**
	 * T
	 */
	TRUE("T"), 
	
	/**
	 * F
	 */
	FALSE("F");
	
	private final String code;
	
	private BoolCode(String code){
		this.code = code;
	}
	
	/**
	 * <des> 得到与参数匹配的枚举实例 </des>
	 * @param code 字面值或代号值
	 * @return 若匹配则返回匹配到枚举实例, 否则返回null
	 * @since 1.0.0
	 */
	public static BoolCode fromCode(String code){
		if(code == null || code.length() == 0) 
			return null;
		for(BoolCode boolCode : BoolCode.values()){
			if(boolCode.code.equals(code) || boolCode.name().equals(code)){
				return boolCode;
			}
		}
		return null;
	}
	
	/**
	 * <des> 得到与参数匹配的枚举实例 </des>
	 * @param code true or false
	 * @return 返回相匹配的枚举实例
	 * @since 1.0.0
	 */
	public static BoolCode fromCode(boolean code){
		return code ? TRUE : FALSE;
	}
	
	/**
	 * <des> 实例的真实值 </des>
	 * @return 返回该实例所表示的真实类型的值
	 * @since 1.0.0
	 */
	public boolean boolValue(){
		return this.ordinal() == 0;
	}
	
	/**
	 * <des> 判定两个实例是否相等 </des>
	 * @param e1 实例1
	 * @param e2 实例2
	 * @return 相等则返回true, 否则返回false
	 * @since 1.0.0
	 */
	public static boolean equalsCode(BoolCode e1, BoolCode e2){
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