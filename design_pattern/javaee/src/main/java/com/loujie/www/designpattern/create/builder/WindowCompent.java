package com.loujie.www.designpattern.create.builder;

/**
 * 窗户组件
 * 
 * @author loujie
 *
 */
public class WindowCompent {

	private String name;// 窗户名称

	public WindowCompent(String name) {
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
