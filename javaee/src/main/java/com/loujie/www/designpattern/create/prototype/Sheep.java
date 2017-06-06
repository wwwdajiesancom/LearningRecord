package com.loujie.www.designpattern.create.prototype;

import java.util.Date;

/**
 * 羊(实现了浅克隆)
 * 
 * 浅克隆：属性值的引用是相同的
 * 
 * @author loujie
 *
 */
public class Sheep implements Cloneable {

	private String name;// 名字

	private Date birthday;// 生日

	public Sheep(String name, Date birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}

	public Sheep() {
		super();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object obj = super.clone();

		return obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Sheep [name=" + name + ", birthday=" + birthday + "]";
	}

}
