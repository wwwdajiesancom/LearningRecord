package com.loujie.www.helloword;

public class HelloWorld {

	private String name;

	private String desc;

	public HelloWorld() {
		System.out.println("HelloWorld.HelloWorld()");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("HelloWorld.setName()");
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void init_method() {
		System.out.println("HelloWorld.init_method()");
	}

	public void destory() {
		System.out.println("HelloWorld.destory()");
	}

	public void hello() {
		System.out.println("hello:" + this.name);
	}

	@Override
	public String toString() {
		return "HelloWorld [name=" + name + ", desc=" + desc + "]";
	}

}
