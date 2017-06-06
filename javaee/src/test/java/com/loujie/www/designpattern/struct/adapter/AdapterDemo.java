package com.loujie.www.designpattern.struct.adapter;

import org.junit.Test;

public class AdapterDemo {

	@Test
	public void computerTest() {
		// 存放目标的类
		Computer computer = new Computer();

		// 通过适配器创建Target
		UsbTarget ut = new PS2UsbAdapter(new PS2Adaptee());

		// 调用方法
		computer.usb(ut);
	}

}
