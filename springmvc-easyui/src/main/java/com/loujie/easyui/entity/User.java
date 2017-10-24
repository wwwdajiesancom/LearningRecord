package com.loujie.easyui.entity;

import com.loujie.easyui.entity.base.BaseEntity;

public class User extends BaseEntity {

	private static final long serialVersionUID = -6485194675462577318L;

	private String name;

	private String loginName;

	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
