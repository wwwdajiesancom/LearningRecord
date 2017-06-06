package com.loujie.www.designpattern.create.factory.methodfactory;

import com.loujie.www.designpattern.create.factory.simplefactory.Byd;
import com.loujie.www.designpattern.create.factory.simplefactory.Car;

/**
 * 方法工厂模式 创建byd的工厂类
 * 
 * @author loujie
 *
 */
public class BydFactory implements CarFactory {

	@Override
	public Car createCar() {

		return new Byd();
	}

}
