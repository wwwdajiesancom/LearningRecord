package com.loujie.www.designpattern.create.builder;

/**
 * 装配器
 * 
 * @author loujie
 *
 */
public abstract class HouseDirector {

	private HouseBuilder houseBuilder;// 房子的构建者

	public HouseDirector(HouseBuilder houseBuilder) {
		super();
		this.houseBuilder = houseBuilder;
	}

	public abstract House directorHouse();

	public HouseBuilder getHouseBuilder() {
		return houseBuilder;
	}

	public void setHouseBuilder(HouseBuilder houseBuilder) {
		this.houseBuilder = houseBuilder;
	}

}
