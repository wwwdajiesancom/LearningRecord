package com.loujie.util.result;

import java.io.Serializable;

/**
 * soa返回状态
 * 
 * @author loujie
 *
 */
public enum ResultStatus implements Serializable {

	SUCCESS(200, "成功"), //
	FAIL(201, "失败"), //
	FORBIDDEN(403, "禁止访问"), //
	LOGIN_TIMEOUT(503, "登陆超时"), //
	NO_LOGIN(503, "没有登陆"), //
	PARAM_ERROR(504, "参数错误");

	private int code;
	private String msg;

	private ResultStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
