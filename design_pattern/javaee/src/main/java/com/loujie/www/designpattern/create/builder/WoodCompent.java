package com.loujie.www.designpattern.create.builder;

/**
 * 木头组件
 * 
 * @author loujie
 *
 */
public class WoodCompent {

	private String name;// 组建名称

	public WoodCompent(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
