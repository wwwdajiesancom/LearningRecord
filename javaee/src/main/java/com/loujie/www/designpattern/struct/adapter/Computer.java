package com.loujie.www.designpattern.struct.adapter;

/**
 * 电脑,主题
 * 
 * @author loujie
 *
 */
public class Computer {

	/**
	 * 电脑上的USB端口
	 * 
	 * @param ut
	 */
	public void usb(UsbTarget ut) {
		ut.handleReq();
	}

}
