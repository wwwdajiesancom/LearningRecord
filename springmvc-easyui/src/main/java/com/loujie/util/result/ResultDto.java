package com.loujie.util.result;



import java.io.Serializable;

import com.loujie.util.page.PageResult;

/**
 * soa返回结果
 * 
 * @author loujie
 *
 */
public class ResultDto extends PageResult implements Serializable {

	private static final long serialVersionUID = 5379602983251302657L;

	/**
	 * 返回状态码
	 */
	private Integer code = 200;// 200是成功,它是依据ResultStatus来的

	/**
	 * 状态
	 */
	private ResultStatus status = null;

	/**
	 * 返回信息
	 */
	private String msg;

	/**
	 * 描述信息
	 */
	private String desc;

	/**
	 * 返回
	 */
	private Object object;

	public ResultDto(ResultStatus status) {
		this.status = status;
		this.init();
	}

	public ResultDto() {
		this.init();
	}

	public void initPage(PageResult pageResult) {
		super.init(pageResult);
	}

	private void init() {
		if (this.status == null) {
			this.status = ResultStatus.SUCCESS;
		}
		this.setStatus(this.status);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
		this.code = this.status.getCode();
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public boolean isSuccess() {
		if (this.status == ResultStatus.SUCCESS) {
			return true;
		}
		return false;
	}
}
