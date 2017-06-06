package com.loujie.www.designpattern.create.builder;

import org.junit.Test;

import com.loujie.www.designpattern.create.builder.jiege.JiegeHouseBuilder;
import com.loujie.www.designpattern.create.builder.jiege.JiegeHouseDirector;

/**
 * 建造者模式测试
 * 
 * @author loujie
 *
 */
public class BuilderDemo {

	@Test
	public void jiegeHouse() {
		// 创建了构建器
		HouseBuilder houseBuilder = new JiegeHouseBuilder();
		// 创建了装配器
		HouseDirector houseDirector = new JiegeHouseDirector(houseBuilder);
		House house = houseDirector.directorHouse();

		System.err.println(house.getFloorCompent().getName());
		System.err.println(house.getWindowCompent().getName());
	}

}
