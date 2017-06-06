package com.loujie.www.designpattern.create.factory.methodfactory;

import com.loujie.www.designpattern.create.factory.simplefactory.Aodi;
import com.loujie.www.designpattern.create.factory.simplefactory.Car;

/**
 * 方法工厂模式 创建aodi的工厂
 * 
 * @author loujie
 *
 */
public class AodiFactory implements CarFactory {

	@Override
	public Car createCar() {
		return new Aodi();
	}

}
