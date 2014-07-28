package org.jelly.examples.orm.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.jelly.code.DateFormatCode;
import org.jelly.util.DateUtil;
/**
 * @author Lychie Fan
 */
@Entity
public class Person {

	private int id;
	private int age;
	private String sex;
	private String name;
	private String address;
	private Date birthday;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	@Transient
	public String getBirthdayStr() {
		return DateUtil.formatDate(birthday, DateFormatCode.SHORT_HYPHEN.toCode());
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		final String TAB = "	";
		StringBuilder builder = new StringBuilder();
		builder.append(id).append(TAB).append(name).append(TAB);
		builder.append(age).append(TAB).append(sex).append(TAB);
		builder.append(address).append(TAB);
		builder.append(getBirthdayStr());
		return builder.toString();
	}

}