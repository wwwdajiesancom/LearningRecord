package com.loujie.www.designpattern.struct.adapter;

/**
 * 适配器,将PS2端口转换成USB端口
 * 
 * @author loujie
 *
 */
public class PS2UsbAdapter implements UsbTarget {

	// 需要转换的类
	private PS2Adaptee adaptee;

	/**
	 * 适配器需要PS2这个参数
	 * 
	 * @param adaptee
	 */
	public PS2UsbAdapter(PS2Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void handleReq() {
		this.adaptee.function();
	}

}
