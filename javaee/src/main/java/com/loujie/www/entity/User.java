package com.loujie.www.entity;

import java.io.Serializable;

/**
 * 用户表
 * 
 * @author loujie
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1920424343775021042L;

	private Integer id;// Id主键

	private String phone;// 手机号

	private String name;// 姓名

	private String password;// 密码

	private String idno;// 身份证号

	private Integer status = 1;// 状态

	private String createTime;// 创建时间

	public User(Integer id, String phone, String name, String password, String idno, Integer status, String createTime) {
		super();
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.password = password;
		this.idno = idno;
		this.status = status;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", phone=" + phone + ", name=" + name + ", password=" + password + ", idno=" + idno + ", createTime=" + createTime + "]";
	}

}
