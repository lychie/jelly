package org.jelly.examples.cases.util.demo.model;

import java.util.Date;
import org.jelly.code.DateFormatCode;
import org.jelly.util.DateUtil;

public class User {

	private int id;
	private String sex;
	private String name;
	private Date registerTime;
	private boolean valid;
	
	public User(int id, String name, String sex, boolean valid, Date registerTime){
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.valid = valid;
		this.registerTime = registerTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id).append("	");
		builder.append(name).append("	");
		builder.append(sex).append("	");
		builder.append(valid).append("	");
		builder.append(DateUtil.formatDate(registerTime, DateFormatCode.SHORT_HYPHEN.toCode()));
		return builder.toString();
	}
}