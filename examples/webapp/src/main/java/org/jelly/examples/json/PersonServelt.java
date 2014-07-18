package org.jelly.examples.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jelly.examples.json.model.Person;
import org.jelly.json.Json;
import org.jelly.util.DateUtil;
/**
 * @author Lychie Fan
 */
public class PersonServelt extends HttpServlet {

	private static final long serialVersionUID = 5586344927777757698L;
	private static final List<Person> persons = new ArrayList<Person>();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		Json.getDefault().outputObject("persons", persons, response);
		return ;
	}

	/**
	 * 预设数据
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		Date date = new Date();
		persons.add(new Person("﻿何国群", "男", "广东茂名", DateUtil.getDateModifyDays(date, -5)));
		persons.add(new Person("杨晓婷", "女", "广东深圳", DateUtil.getDateModifyDays(date, -4)));
		persons.add(new Person("杨忠杰", "男", "广东佛山", DateUtil.getDateModifyDays(date, -3)));
		persons.add(new Person("叶水燕", "女", "广东湛江", DateUtil.getDateModifyDays(date, -2)));
		persons.add(new Person("钟婷婷", "女", "广东东莞", DateUtil.getDateModifyDays(date, -1)));
	}
	
}