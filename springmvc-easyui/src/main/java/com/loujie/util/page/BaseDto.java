package com.loujie.util.page;

import java.io.Serializable;

/**
 * 基础Dto
 * 
 * @author loujie
 *
 */
public class BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8914896710360648099L;

	private String apikey;//

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

}
