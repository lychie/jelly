package org.jelly.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import javax.activation.UnsupportedDataTypeException;
import org.jelly.code.SortCode;
import org.jelly.exception.ExecutetimeException;
import org.jelly.util.ClassUtil;
import org.jelly.util.ConvertUtil;
import org.jelly.util.FieldUtil;
import org.jelly.util.StringUtil;
/**
 * 简单的对象比较器。支持的可排序的类型有：数值类型、字符类型、字符串类型、布尔类型、日期类型<br>
 * 特别说明, 字符型对中文的支持不十分准确, 支持常用汉字, 若要求中文严格比较, 建议不要使用
 * @author Lychie Fan
 * @since 1.0.0
 */
public class SimpleComparator implements Comparator<Object> {

	private String key;
	private Class<?> keyType;
	private SortCode sortCode;
	
	public SimpleComparator(Class<?> entityClass, String key){
		this(entityClass, key, SortCode.ASC);
	}
	
	public SimpleComparator(Class<?> entityClass, String key, SortCode sortCode){
		this.key = key;
		this.sortCode = sortCode;
		this.keyType = FieldUtil.getFieldType(entityClass, key);
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		int result;
		switch (SortableType.fromCode(keyType)) {
		case NUMBER:
			result = numberCompare(value(o1), value(o2));
			break;
		case STRING:
			result = stringCompare(value(o1), value(o2));
			break;
		case CHAR:
			result = charCompare(value(o1), value(o2));
			break;
		case BOOL:
			result = boolCompare(value(o1), value(o2));
			break;
		case DATE:
			result = dateCompare(value(o1), value(o2));
			break;
		default:
			String message = "关键字 ?(?) 不是支持的可排序类型";
			String errorMessage = StringUtil.parse(message, key, keyType.getName());
			throw new ExecutetimeException(new UnsupportedDataTypeException(errorMessage));
		}
		return SortCode.equalsCode(sortCode, SortCode.ASC) ? result : -result;
	}

	// 比较两个数值类型关键字的大小
	private int numberCompare(Object o1, Object o2) {
		BigDecimal n1 = new BigDecimal(o1.toString());
		BigDecimal n2 = new BigDecimal(o2.toString());
		return n1.compareTo(n2);
	}

	// 比较两个字符类串型关键字的大小
	private int stringCompare(Object o1, Object o2) {
		String s1 = StringUtil.getStringISO1(StringUtil.getBytesGBK(o1.toString()));
		String s2 = StringUtil.getStringISO1(StringUtil.getBytesGBK(o2.toString()));
		return s1.compareTo(s2);
	}

	// 比较两个字符类型关键字的大小
	private int charCompare(Object o1, Object o2) {
		char c1 = (Character) o1;
		char c2 = (Character) o2;
		return c1 - c2;
	}

	// 比较两个布尔类型关键字的大小
	private int boolCompare(Object o1, Object o2) {
		Boolean b1 = (Boolean) o1;
		Boolean b2 = (Boolean) o2;
		return b1.compareTo(b2);
	}

	// 比较两个日期类型关键字的大小
	private int dateCompare(Object o1, Object o2) {
		Date d1 = (Date) o1;
		Date d2 = (Date) o2;
		return d1.compareTo(d2);
	}

	// 获取关键字的值
	private Object value(Object o) {
		return FieldUtil.getFieldValue(o, key);
	}
	
	/**
	 * 支持的可排序的类型, 包括常用的数值类型、字符类型、字符串类型、布尔类型、日期类型
	 * @author Lychie Fan
	 * @since 1.0.0
	 */
	enum SortableType {
		
		NUMBER, STRING, CHAR, BOOL, DATE, UNDEFINED;

		public static SortableType fromCode(Class<?> type){
			type = ConvertUtil.getBoxedPrimitiveType(type);
			if(ClassUtil.isInstanceOf(type, Number.class)){
				return NUMBER;
			}else if(ClassUtil.isInstanceOf(type, String.class)){
				return STRING;
			}else if(ClassUtil.isInstanceOf(type, Boolean.class)){
				return BOOL;
			}else if(ClassUtil.isInstanceOf(type, Character.class)){
				return CHAR;
			}else if(ClassUtil.isInstanceOf(type, Date.class)){
				return DATE;
			}else{
				return UNDEFINED;
			}
		}
		
	}
	
}