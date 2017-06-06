package com.loujie.www.designpattern.create.factory.abstractfactory;

/**
 * 椅子
 * 
 * @author loujie
 *
 */
public interface Seat {

	void bearWeight();// 承受重量

}

/**
 * 高端椅子
 * 
 * @author loujie
 *
 */
class HightSeat implements Seat {

	@Override
	public void bearWeight() {
		System.err.println("高端椅子可承受:" + 100l * 10 + "kg");
	}
}

/**
 * 低端椅子
 * 
 * @author loujie
 *
 */
class LowSeat implements Seat {

	@Override
	public void bearWeight() {
		System.err.println("低端椅子能承受:" + 100l * 3 + "kg");

	}

}
