package com.loujie.www.designpattern.create.builder.jiege;

import com.loujie.www.designpattern.create.builder.FloorCompent;
import com.loujie.www.designpattern.create.builder.House;
import com.loujie.www.designpattern.create.builder.HouseBuilder;
import com.loujie.www.designpattern.create.builder.HouseDirector;
import com.loujie.www.designpattern.create.builder.WindowCompent;
import com.loujie.www.designpattern.create.builder.WoodCompent;

/**
 * 杰哥装配器
 * 
 * @author loujie
 *
 */
public class JiegeHouseDirector extends HouseDirector {

	public JiegeHouseDirector(HouseBuilder houseBuilder) {
		super(houseBuilder);
	}

	@Override
	public House directorHouse() {
		FloorCompent fc = super.getHouseBuilder().builderFloorCompent();
		WindowCompent wc = super.getHouseBuilder().builderWindowCompent();
		WoodCompent woc = super.getHouseBuilder().builderWoodCompent();

		House house = new House();
		house.setFloorCompent(fc);
		house.setWindowCompent(wc);
		house.setWoodCompent(woc);
		return house;
	}

}
