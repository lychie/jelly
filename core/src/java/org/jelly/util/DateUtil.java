package org.jelly.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.jelly.exception.ExecutetimeException;
import org.jelly.code.DateFormatCode;
/**
 * 日期相关的常用的操作的工具类
 * @author Lychie Fan
 * @since 1.0.0
 */
public final class DateUtil {

	private DateUtil(){}
	
	// 保证 SimpleDateFormat 多线程安全
	private static final ThreadLocal<SimpleDateFormat> THREADLOCAL = new ThreadLocal<SimpleDateFormat>(){
		
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat();
		}
		
	};
	
	/**
	 * <des> 获取 SimpleDateFormat 实例, 线程安全 </des>
	 * @return SimpleDateFormat - 使用默认的日期格式化模式串 'yyyy-MM-dd'
	 * @see org.jelly.util.DateUtil#getSimpleDateFormat(String)
	 * @since 1.0.0
	 */
	public static SimpleDateFormat getSimpleDateFormat(){
		return getSimpleDateFormat(DateFormatCode.SHORT_HYPHEN.toCode());
	}
	
	/**
	 * <des> 获取 SimpleDateFormat 实例, 线程安全 </des>
	 * @param pattern 日期格式化模式串
	 * @return SimpleDateFormat
	 * @since 1.0.0
	 */
	public static SimpleDateFormat getSimpleDateFormat(String pattern){
		SimpleDateFormat dateFormat = THREADLOCAL.get();
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}
	
	/**
	 * <des> 获取当前日期 </des>
	 * @return 'yyyy-MM-dd' 日期格式化字符串
	 * @since 1.0.0
	 */
	public static String getCurrentDate() {
		return getCurrentDateString(DateFormatCode.SHORT_HYPHEN.toCode());
	}
	
	/**
	 * <des> 获取当前日期时间 </des>
	 * @return 'yyyy-MM-dd HH:mm:ss' 日期格式化字符串
	 * @since 1.0.0
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateString(DateFormatCode.LONG_HYPHEN.toCode());
	}
	
	/**
	 * <des> 获取日期格式化字符串值 </des>
	 * @param pattern 日期格式化模式串
	 * @return 日期格式化后的字符串
	 * @since 1.0.0
	 */
	public static String getCurrentDateString(String pattern) {
		return getSimpleDateFormat(pattern).format(new Date());
	}
	
	/**
	 * <des> 格式化日期成字符串表示 </des>
	 * @param date 日期对象
	 * @param pattern 日期格式化模式串
	 * @return 日期格式化后的字符串
	 * @since 1.0.0
	 */
	public static String formatDate(Date date, String pattern){
		return getSimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * <des> 尝试解析日期字符串的模式, 并解析日期字符串为日期类型 </des>
	 * @param date 日期字符串
	 * @return 日期对象
	 * @since 1.0.0
	 */
	public static Date parseDate(String date){
		return parseDate(date, getPattern(date));
	}
	
	/**
	 * <des> 解析字符串为日期类型 </des>
	 * @param date 日期字符串
	 * @param pattern 模式串
	 * @return 日期对象
	 * @since 1.0.0
	 */
	public static Date parseDate(String date, String pattern){
		try {
			return getSimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			throw new ExecutetimeException(e);
		}
	}
	
	/**
	 * <des> 获取与指定日期对象相隔的年份为单位的日期对象 </des>
	 * @param date 日期对象
	 * @param arg  差异的值(可为负数)
	 * @return 设定的日期对象
	 * @since 1.0.0
	 */
	public static Date getDateModifyYear(Date date, int arg){
		return readjustDate(date, Calendar.YEAR, arg);
	}
	
	/**
	 * <des> 获取与指定日期对象相隔的月份为单位的日期对象 </des>
	 * @param date 日期对象
	 * @param arg  差异的值(可为负数)
	 * @return 设定的日期对象
	 * @since 1.0.0
	 */
	public static Date getDateModifyMonth(Date date, int arg){
		return readjustDate(date, Calendar.MONTH, arg);
	}
	
	/**
	 * <des> 获取与指定日期对象相隔的天为单位的日期对象 </des>
	 * @param date 日期对象
	 * @param arg  差异的值(可为负数)
	 * @return 设定的日期对象
	 * @since 1.0.0
	 */
	public static Date getDateModifyDays(Date date, int arg){
		return readjustDate(date, Calendar.DAY_OF_MONTH, arg);
	}
	
	/**
	 * <des> 获取与指定日期对象相隔的小时为单位的日期对象 </des>
	 * @param date 日期对象
	 * @param arg  差异的值(可为负数)
	 * @return 设定的日期对象
	 * @since 1.0.0
	 */
	public static Date getDateModifyHour(Date date, int arg){
		return readjustDate(date, Calendar.HOUR_OF_DAY, arg);
	}

	/**
	 * <des> 获取与指定日期对象相隔的分钟为单位的日期对象 </des>
	 * @param date 日期对象
	 * @param arg  差异的值(可为负数)
	 * @return 设定的日期对象
	 * @since 1.0.0
	 */
	public static Date getDateModifyMinute(Date date, int arg){
		return readjustDate(date, Calendar.MINUTE, arg);
	}

	/**
	 * <des> 获取与指定日期对象相隔的秒为单位的日期对象 </des>
	 * @param date 日期对象
	 * @param arg  差异的值(可为负数)
	 * @return 设定的日期对象
	 * @since 1.0.0
	 */
	public static Date getDateModifySecond(Date date, int arg){
		return readjustDate(date, Calendar.SECOND, arg);
	}

	/**
	 * <des> 获取与指定日期对象相隔的毫秒为单位的日期对象 </des>
	 * @param date 日期对象
	 * @param arg  差异的值(可为负数)
	 * @return 设定的日期对象
	 * @since 1.0.0
	 */
	public static Date getDateModifyMillisecond(Date date, int arg){
		return readjustDate(date, Calendar.MILLISECOND, arg);
	}
	
	/**
	 * <des> 获取参数指定日期对象表示的一个日历对象 </des>
	 * @param date 日期对象
	 * @return Calendar
	 * @since 1.0.0
	 */
	public static Calendar getCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	/**
	 * <des> 获取日期的格式化模式串(简单的年月日时分秒毫秒顺序) </des>
	 * @param date 日期字符串
	 * @return 匹配的模式串
	 * @since 1.0.0
	 */
	public static String getPattern(String date){
		char[] chars = date.toCharArray();
		char[][] pattern = {
			{'y', 'y', 'y', 'y'}, 
			{'M', 'M'}, 
			{'d', 'd'}, 
			{'H', 'H'}, 
			{'m', 'm'},
			{'s', 's'}, 
			{'S', 'S', 'S'}
		};
		int i = 0, j = 0, monthIndex = 1, hourIndex = 3, fullLength = 2;
		StringBuilder builder = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		for(char ch : chars){
			if(ch >= '0' && ch <= '9'){
				temp.append(pattern[i][j++]);
			}else{
				if((i == monthIndex || i == hourIndex) && temp.length() != fullLength){
					String lowerCase = temp.toString().toLowerCase();
					builder.append(lowerCase).append(lowerCase);
				}else{
					builder.append(temp.toString());
				}
				builder.append(ch);
				i++;
				j = 0;
				temp = new StringBuilder();
			}
		}
		builder.append(temp.toString());
		return builder.toString();
	}
	
	// 重新调整日期
	private static Date readjustDate(Date date, int field, int arg){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, arg);
		return calendar.getTime();
	}
}