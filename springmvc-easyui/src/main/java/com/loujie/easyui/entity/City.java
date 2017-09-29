package com.loujie.easyui.entity;

import java.io.Serializable;
import java.util.Date;

public class City implements Serializable {

	private static final long serialVersionUID = -8953992750396305155L;

	private Integer id;// 主键,自增Id

	private String name;// 城市名称

	private String nameInt;// 外文城市名称

	private Long type;// 类型

	private Integer countryId;// 国家Id

	private Integer sort;// 排序

	private Integer status;// 状态:0=无效;1=正常

	private Date createdAt;// 创建时间

	private Date updatedAt;// 更新时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameInt() {
		return nameInt;
	}

	public void setNameInt(String nameInt) {
		this.nameInt = nameInt;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
