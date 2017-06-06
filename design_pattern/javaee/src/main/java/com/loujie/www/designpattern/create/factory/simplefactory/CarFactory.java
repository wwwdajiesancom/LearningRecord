package com.loujie.www.designpattern.create.factory.simplefactory;

/**
 * 简单工厂模式
 * 
 * @author loujie
 *
 */
public abstract class CarFactory {

	/**
	 * 工厂创建实体
	 * 
	 * @param type
	 * @return
	 */
	public static Car createCar(String type) {
		if ("aodi".equals(type)) {
			return new Aodi();
		} else if ("byd".equals(type)) {
			return new Byd();
		} else {
			return null;
		}
	}

}
