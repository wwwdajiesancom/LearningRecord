package com.loujie.www.designpattern.create.prototype;

import java.util.Date;

/**
 * 人(实现了深克隆)
 * 
 * 深克隆：属性也被重新的设置了
 * 
 * @author loujie
 *
 */
public class People implements Cloneable {

	private String name;// 名称

	private Date birthday;// 生日

	public People() {
		super();
	}

	public People(String name, Date birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object object = super.clone();
		// 手动设置
		((People) object).setBirthday((Date) (this.birthday.clone()));

		return super.clone();
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
		return "People [name=" + name + ", birthday=" + birthday + "]";
	}

}
