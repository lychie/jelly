package org.jelly.examples.cases.util;

import java.util.Calendar;
import java.util.Date;
import org.jelly.code.DateFormatCode;
import org.jelly.helper.Testing;
import org.jelly.util.DateUtil;
import org.junit.Test;
/**
 * @author Lychie Fan
 */
public class DateUtilDemo {
	
	/**
	 * 当前日期
	 */
	@Test
	public void getCurrentDate(){
		Testing.printlnObject(DateUtil.getCurrentDate());
	}

	/**
	 * 当前日期时间
	 */
	@Test
	public void getCurrentDateTime(){
		Testing.printlnObject(DateUtil.getCurrentDateTime());
	}

	/**
	 * 当前日期, 模式串
	 */
	@Test
	public void getCurrentDateString(){
		String date = DateUtil.getCurrentDateString(DateFormatCode.LONG_HYPHEN.toCode());
		Testing.printlnObject(date);
	}

	/**
	 * 格式化日期
	 */
	@Test
	public void formatDate(){
		String date = DateUtil.formatDate(new Date(), DateFormatCode.SHORT_HYPHEN.toCode());
		Testing.printlnObject(date);
	}

	/**
	 * 日期的模式串
	 */
	@Test
	public void getPattern(){
		String dateString1 = "2014-07-07";
		String dateString2 = "2014-07-07 21:12:34";
		String dateString3 = "2014年07月07日 21时12分34秒";
		Testing.printlnObject(DateUtil.getPattern(dateString1));
		Testing.printlnObject(DateUtil.getPattern(dateString2));
		Testing.printlnObject(DateUtil.getPattern(dateString3));
	}

	/**
	 * 解析日期
	 */
	@Test
	public void parseDate(){
		String dateString = "2014-05-20";
		Date date = DateUtil.parseDate(dateString, DateFormatCode.SHORT_HYPHEN.toCode());
		Testing.printlnObject(date);
	}

	/**
	 * 解析日期, 自动解析日期字符串的模式
	 */
	@Test
	public void parseDateNonPattern(){
		String dateString = "2014-05-20";
		Date date = DateUtil.parseDate(dateString);
		Testing.printlnObject(date);
	}

	/**
	 * Calendar
	 */
	@Test
	public void getCalendar(){
		Calendar calendar = DateUtil.getCalendar(new Date());
		Testing.printlnObject(calendar);
	}

	/**
	 * 与当前时间相距的日期
	 */
	@Test
	public void getDateModifyDays(){
		Date date1 = new Date();
		Date date2 = DateUtil.getDateModifyDays(date1, -1);
		Date date3 = DateUtil.getDateModifyDays(date1, 1);
		String string1 = DateUtil.formatDate(date1, DateFormatCode.SHORT_HYPHEN.toCode());
		String string2 = DateUtil.formatDate(date2, DateFormatCode.SHORT_HYPHEN.toCode());
		String string3 = DateUtil.formatDate(date3, DateFormatCode.SHORT_HYPHEN.toCode());
		Testing.printlnObject(string1);
		Testing.printlnObject(string2);
		Testing.printlnObject(string3);
	}
	
}