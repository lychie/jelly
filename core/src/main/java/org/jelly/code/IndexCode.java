package org.jelly.code;
/**
 * 常用索引类型枚举
 * @author Lychie Fan
 * @since 1.0.0
 */
public enum IndexCode {

	/**
	 * 正数 :: 1
	 */
	POSITIVE(1),

	/**
	 * 负数 :: -1
	 */
	NAGATIVE(-1),

	/**
	 * 零 :: 0
	 */
	ZERO(0),

	/**
	 * 没有, 空 :: 0
	 */
	NONE(0),

	/**
	 * 非负数 :: 1
	 */
	NONNEGATIVE(1),

	/**
	 * 非正数 :: -1
	 */
	NONPOSITIVE(-1), 

	/**
	 * 找不到索引 :: -1
	 */
	INDEX_NOT_FOUND(-1), 
	
	/**
	 * 文件末尾的标识 :: -1
	 */
	EOF(-1);
	
	private final int code;
	
	private IndexCode(int code){
		this.code = code;
	}
	
	/**
	 * <des> 得到与参数匹配的枚举实例 </des>
	 * @param code 代号值
	 * @return 若匹配则返回匹配到枚举实例, 否则返回null
	 * @since 1.0.0
	 */
	public static IndexCode fromCode(int code){
		for(IndexCode indexCode : IndexCode.values()){
			if(indexCode.code == code || indexCode.name().equals(code)){
				return indexCode;
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
	public static boolean equalsCode(IndexCode e1, IndexCode e2){
		if(e1 == null && e2 == null){
			return true;
		}else if(e1 != null && e2 != null){
			return e1.ordinal() == e2.ordinal();
		}else{
			return false;
		}
	}

	/**
	 * <des> 字面值 </des>
	 * @return 例如, 若声明 INDEX_NOT_FOUND(-1), toFace() = INDEX_NOT_FOUND
	 * @since 1.0.0
	 */
	public String toFace(){
		return this.name();
	}

	/**
	 * <des> 代号值 </des>
	 * @return 例如, 若声明 INDEX_NOT_FOUND(-1), toCode() = -1
	 * @since 1.0.0
	 */
	public int toCode() {
		return code;
	}
	
}