package org.jelly.xml;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import org.jelly.model.ClassToken;
import org.jelly.util.FieldUtil;
import org.jelly.util.MapUtil;
import org.jelly.util.StringUtil;
/**
 * XML
 * @author Lychie Fan
 * @since 1.6.0
 */
public abstract class XML {

	protected Class<?> pojoClass;
	protected LinkedHashMap<String, String> mapping = new LinkedHashMap<String, String>();

	/**
	 * <des> 获取可写的XML </des>
	 * @since 1.6.0
	 */
	public static ReadableXml getReadableXml() {
		return new ReadableXml();
	}

	/**
	 * <des> 获取可读的XML </des>
	 * @since 1.6.0
	 */
	public static WritableXml getWritableXml() {
		return new WritableXml();
	}

	/**
	 * <des> 设置映射 </des>
	 * @since 1.6.0
	 */
	public void setMapping(String field, String lable) {
		mapping.put(field, lable);
	}

	/**
	 * 初始化映射
	 * @since 1.6.0
	 */
	protected void initMapping(Type type) {
		this.pojoClass = ClassToken.getParameterizedType(type);
		if (MapUtil.isEmpty(mapping)) {
			List<String> fieldNames = FieldUtil.getDeclaredNonStaticFieldNames(pojoClass);
			for (String fieldName : fieldNames) {
				mapping.put(fieldName,StringUtil.convertUpperCaseLetter(fieldName, "-"));
			}
		}
	}

}