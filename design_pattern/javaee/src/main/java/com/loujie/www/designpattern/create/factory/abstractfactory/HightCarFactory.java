package com.loujie.www.designpattern.create.factory.abstractfactory;

/**
 * 高端汽车工厂
 * 
 * @author loujie
 *
 */
public class HightCarFactory implements AbstractCar {

	@Override
	public Engine createEngine() {
		return new HightEngine();
	}

	@Override
	public Seat createSeat() {
		return new HightSeat();
	}

	@Override
	public Tyre createTyre() {
		return new HightTyre();
	}

}
