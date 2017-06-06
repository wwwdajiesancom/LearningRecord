package com.loujie.www.designpattern.create.factory.methodfactory;

import com.loujie.www.designpattern.create.factory.simplefactory.Car;

/**
 * 方法工厂模式 创建车的接口
 * 
 * @author loujie
 *
 */
public interface CarFactory {

	Car createCar();

}
