package com.loujie.www.designpattern.create.builder.weiwei;

import com.loujie.www.designpattern.create.builder.FloorCompent;
import com.loujie.www.designpattern.create.builder.House;
import com.loujie.www.designpattern.create.builder.HouseBuilder;
import com.loujie.www.designpattern.create.builder.HouseDirector;
import com.loujie.www.designpattern.create.builder.WindowCompent;
import com.loujie.www.designpattern.create.builder.WoodCompent;

public class WeiWeiHouseDirector extends HouseDirector {

	public WeiWeiHouseDirector(HouseBuilder houseBuilder) {
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
