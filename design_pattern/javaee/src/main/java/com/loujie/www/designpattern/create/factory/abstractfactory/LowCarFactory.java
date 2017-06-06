package com.loujie.www.designpattern.create.factory.abstractfactory;

/**
 * 低端汽车工厂
 * 
 * @author loujie
 *
 */
public class LowCarFactory implements AbstractCar {

	@Override
	public Engine createEngine() {
		return new LowEngine();
	}

	@Override
	public Seat createSeat() {
		return new LowSeat();
	}

	@Override
	public Tyre createTyre() {
		return new LowTyre();
	}

}
