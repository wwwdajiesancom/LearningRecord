package com.loujie.easyui.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 存放共有的成员变量类
 * 
 * @author loujie
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8835844004811142611L;

	protected Integer id;// 自增主键

	// DEFAULT CURRENT_TIMESTAMP NOT NULL;
	protected Date createTime;// 创建时间

	// ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;
	protected Date updateTime;// 最新更新时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
