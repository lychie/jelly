package org.jelly.examples.excel.model;

import java.util.Date;
import org.jelly.code.DateFormatCode;
import org.jelly.util.DateUtil;
/**
 * @author Lychie Fan
 */
public class Person {

	private int id;
	private String sex;
	private String name;
	private String phone;
	private double owe;
	private String address;
	private Date date;
	private static int counts = 1001;
	
	public Person(){}
	
	public Person(String name, String sex, String phone, double owe, String address, String date){
		this(name, sex, phone, owe, address, DateUtil.parseDate(date));
	}
	
	public Person(String name, String sex, String phone, double owe, String address, Date date){
		this.id = counts++;
		this.sex = sex;
		this.owe = owe;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.date = date;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getOwe() {
		return owe;
	}

	public void setOwe(double owe) {
		this.owe = owe;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		final String TAB = "	";
		builder.append(id).append(TAB);
		builder.append(name).append(TAB);
		builder.append(sex).append(TAB);
		builder.append(owe).append(TAB);
		builder.append(phone).append(TAB);
		builder.append(address).append(TAB);
		builder.append(DateUtil.formatDate(date, DateFormatCode.SHORT_HYPHEN.toCode()));
		return builder.toString();
	}
}