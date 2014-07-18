package org.jelly.json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
/**
 * 排除策略
 * @author Lychie Fan
 * @since 1.1.0
 */
public class JsonExcluder implements ExclusionStrategy {

	private Object[] exclusions;
	
	JsonExcluder(Object[] exclusions){
		this.exclusions = exclusions;
	}
	
	/**
	 * 忽略类型
	 */
	@Override
	public boolean shouldSkipClass(Class<?> skipClass) {
		for(Object item : exclusions){
			if(item == skipClass){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 忽略字段属性
	 */
	@Override
	public boolean shouldSkipField(FieldAttributes skipField) {
		String fieldName = skipField.getName();
		for(Object item : exclusions){
			if(item.equals(fieldName)){
				return true;
			}
		}
		return false;
	}
	
}