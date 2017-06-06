package com.loujie.www.designpattern.create.factory.abstractfactory;

/**
 * 汽车工厂接口
 * 
 * @author loujie
 *
 */
public interface AbstractCar {

	Engine createEngine();// 创建引擎

	Seat createSeat();// 椅子

	Tyre createTyre();// 轮胎

}
