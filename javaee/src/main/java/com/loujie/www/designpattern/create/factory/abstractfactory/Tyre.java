package com.loujie.www.designpattern.create.factory.abstractfactory;

/**
 * 轮胎
 * 
 * @author loujie
 *
 */
public interface Tyre {

	void material();

}

/**
 * 高端轮胎
 * 
 * @author loujie
 *
 */
class HightTyre implements Tyre {

	@Override
	public void material() {
		System.err.println("高端轮胎材料:合金合成");
	}
}

/**
 * 低端轮胎
 * 
 * @author loujie
 *
 */
class LowTyre implements Tyre {

	@Override
	public void material() {
		System.err.println("低端轮胎材料:采用普通皮质品合成");
	}

}
