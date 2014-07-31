package org.jelly.excel;

import java.util.LinkedHashMap;
import java.util.List;
import org.jelly.util.FieldUtil;
import org.jelly.util.MapUtil;
/**
 * Excel
 * @author Lychie Fan
 * @since 1.5.0
 */
public abstract class Excel {

	protected String sheetName;
	protected String dateFormat;
	protected String numberFormat;
	protected LinkedHashMap<String, String> mapping;
	protected static final int TITLE_ROW_NUMBER = 0;
	protected static final int BODY_ROW_NUMBER = 1;
	protected static final String DEFAULT_SHEET_PREFIX = "Sheet";
	protected static final String TEXT_FORMAT = "TEXT";
	protected static final String DATE_FORMAT = "DATE";
	protected static final String NUMBER_FORMAT = "NUMBER";
	private static final String DEFAULT_TITLE_PREFIX = "标题 ";
	
	public Excel() {
		this.sheetName = DEFAULT_SHEET_PREFIX + 1;
		this.mapping = new LinkedHashMap<String, String>();
		this.dateFormat = Format.DATE_SHORT_HYPHEN.toString();
		this.numberFormat = Format.TEXT.toString();
	}
	
	// 构建默认的映射
	protected void initMapping(Class<?> pojoClass){
		if(MapUtil.isEmpty(mapping)){
			List<String> fieldList = FieldUtil.getDeclaredNonStaticFieldNames(pojoClass);
			for(int i = 0; i < fieldList.size(); i++){
				mapping.put(fieldList.get(i), DEFAULT_TITLE_PREFIX + (i + 1));
			}
		}
	}
	
	/**
	 * <des> 设置字段属性的映射 </des>
	 * @since 1.5.0
	 */
	public void setMapping(String field, String label){
		mapping.put(field, label);
	}

	/**
	 * <des> 设置 Excel Sheet Name </des>
	 * @since 1.5.0
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
}