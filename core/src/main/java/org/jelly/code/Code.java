package org.jelly.code;
/**
 * 枚举类型的接口
 * @author Lychie Fan
 * @since 1.0.0
 */
public interface Code {

	/**
	 * <des> 字面值 </des>
	 * @return 例如, 若声明 UTF_8("UTF-8"), toFace() = UTF_8
	 * @since 1.0.0
	 */
	String toFace();
	
	/**
	 * <des> 代号值 </des>
	 * @return 例如, 若声明 UTF_8("UTF-8"), toCode() = UTF-8
	 * @since 1.0.0
	 */
	String toCode();
	
}