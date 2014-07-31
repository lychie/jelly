package org.jelly.excel;
/**
 * 单元格格式
 * @author Lychie Fan
 * @since 1.5.0
 */
public class Format {
	
	private String format;
	
	private Format(String format){
		this.format = format;
	}
	
	/**
	 * <des> 单元格格式 </des>
	 * @since 1.5.0
	 */
	public static Format format(String format){
		return new Format(format);
	}
	
	/**
	 * 文本格式
	 */
	public static final Format TEXT = new Format("@");
	/**
	 * 整型格式
	 */
	public static final Format INTEGER = new Format("0");
	/**
	 * 整型格式，百分比
	 */
	public static final Format INTEGER_PERCENT = new Format("0%");
	/**
	 * 整型格式，千位分隔
	 */
	public static final Format INTEGER_THOUSANDS = new Format("#,##0");
	/**
	 * 整型格式，货币
	 */
	public static final Format INTEGER_ACCOUNTING = new Format("￥#,##0");
	/**
	 * 小数，保留两位小数
	 */
	public static final Format FLOAT = new Format("0.00");
	/**
	 * 小数，保留两位小数，百分比
	 */
	public static final Format FLOAT_PERCENT = new Format("0.00%");
	/**
	 * 小数，保留两位小数，千位分隔
	 */
	public static final Format FLOAT_THOUSANDS = new Format("#,##0.00");
	/**
	 * 小数，保留两位小数，货币
	 */
	public static final Format FLOAT_ACCOUNTING = new Format("￥#,##0.00");
	/**
	 * 日期，年-月-日
	 */
	public static final Format DATE_SHORT_HYPHEN = new Format("yyyy-MM-dd");
	/**
	 * 日期，年/月/日
	 */
	public static final Format DATE_SHORT_VIRGULE = new Format("yyyy/MM/dd");
	/**
	 * 日期，年-月-日 时:分:秒
	 */
	public static final Format DATE_LONG_HYPHEN = new Format("yyyy-MM-dd HH:mm:ss");
	/**
	 * 日期，年/月/日 时:分:秒
	 */
	public static final Format DATE_LONG_VIRGULE = new Format("yyyy/MM/dd HH:mm:ss");
	/**
	 * 整型格式，正数绿色，负数红色，零蓝色
	 */
	public static final Format INTEGER_COLOR = new Format("_COLOR0");
	/**
	 * 整型格式，百分比，正数绿色，负数红色，零蓝色
	 */
	public static final Format INTEGER_PERCENT_COLOR = new Format("_COLOR0%");
	/**
	 * 整型格式，千位分隔，正数绿色，负数红色，零蓝色
	 */
	public static final Format INTEGER_THOUSANDS_COLOR = new Format("_COLOR#,##0");
	/**
	 * 整型格式，货币，正数绿色，负数红色，零蓝色
	 */
	public static final Format INTEGER_ACCOUNTING_COLOR = new Format("_COLOR￥#,##0");
	/**
	 * 小数，保留两位小数，正数绿色，负数红色，零蓝色
	 */
	public static final Format FLOAT_COLOR = new Format("_COLOR0.00");
	/**
	 * 小数，保留两位小数，百分比，正数绿色，负数红色，零蓝色
	 */
	public static final Format FLOAT_PERCENT_COLOR = new Format("_COLOR0.00%");
	/**
	 * 小数，保留两位小数，千位分隔，正数绿色，负数红色，零蓝色
	 */
	public static final Format FLOAT_THOUSANDS_COLOR = new Format("_COLOR#,##0.00");
	/**
	 * 小数，保留两位小数，货币，正数绿色，负数红色，零蓝色
	 */
	public static final Format FLOAT_ACCOUNTING_COLOR = new Format("_COLOR￥#,##0.00");
	// JXL 不支持
//	public static final Format INTEGER_COLOR = new Format("[绿色]00;[红色]-00;[蓝色]0");
//	public static final Format INTEGER_PERCENT_COLOR = new Format("[绿色]0%;[红色]-0%;[蓝色]0%");
//	public static final Format INTEGER_THOUSANDS_COLOR = new Format("[绿色]#,##0;[红色]-#,##0;[蓝色]0");
//	public static final Format INTEGER_ACCOUNTING_COLOR = new Format("[绿色]￥#,##0;[红色]￥-#,##0;[蓝色]￥0");
//	public static final Format FLOAT_COLOR = new Format("[绿色]0.00;[红色]-0.00;[蓝色]0");
//	public static final Format FLOAT_PERCENT_COLOR = new Format("[绿色]0.00%;[红色]-0.00%;[蓝色]0%");
//	public static final Format FLOAT_THOUSANDS_COLOR = new Format("[绿色]#,##0.00;[红色]-#,##0.00;[蓝色]0");
//	public static final Format FLOAT_ACCOUNTING_COLOR = new Format("[绿色]￥#,##0.00;[红色]￥-#,##0.00;[蓝色]￥0");

	@Override
	public String toString() {
		return format;
	}

}