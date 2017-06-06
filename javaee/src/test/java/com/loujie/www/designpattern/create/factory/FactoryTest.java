package com.loujie.www.designpattern.create.factory;

import org.junit.Test;

import com.loujie.www.designpattern.create.factory.abstractfactory.AbstractCar;
import com.loujie.www.designpattern.create.factory.abstractfactory.HightCarFactory;
import com.loujie.www.designpattern.create.factory.methodfactory.AodiFactory;
import com.loujie.www.designpattern.create.factory.methodfactory.BydFactory;
import com.loujie.www.designpattern.create.factory.simplefactory.Aodi;
import com.loujie.www.designpattern.create.factory.simplefactory.Byd;
import com.loujie.www.designpattern.create.factory.simplefactory.Car;
import com.loujie.www.designpattern.create.factory.simplefactory.CarFactory;

public class FactoryTest {

	/**
	 * 简单工厂测试
	 */
	@Test
	public void simpleFactory() {
		// 针对的是同一种产品
		// 基本测试,没有通过工厂来创建Car;调用者与创建者没有分离

		Car car1 = new Aodi();
		Car car2 = new Byd();

		car1.run();
		car2.run();
	}

	/**
	 * 分离了
	 */
	@Test
	public void simpleFactory2() {
		// 针对的是同一种产品
		// 采用了工厂,但是如果有新车型的话,无法扩展;一般都采用静态的方法,所以也可以叫做静态工厂模式
		// 没有满足OCP原则
		// 可以将工厂写成抽象类,这样写一个抽象方法,利于扩展;这样就失去了工厂的意义

		Car car1 = CarFactory.createCar("aodi");
		Car car2 = CarFactory.createCar("byd");

		car1.run();
		car2.run();
	}

	@Test
	public void methodFactory() {
		// 针对的是同一种产品
		// 方法工厂模式,可以扩展,但是如果新增车型的话,需要再增加两个类,有点繁琐,一般还是选用第一种
		Car car1 = new BydFactory().createCar();
		Car car2 = new AodiFactory().createCar();

		car1.run();
		car2.run();
	}

	@Test
	public void abstractFactory() {
		// 创建一个高端的车
		AbstractCar abstractCar = new HightCarFactory();

		abstractCar.createEngine().runSpeed();
	}

}
