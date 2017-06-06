package com.loujie.www.designpattern.create.builder.jiege;

import com.loujie.www.designpattern.create.builder.FloorCompent;
import com.loujie.www.designpattern.create.builder.HouseBuilder;
import com.loujie.www.designpattern.create.builder.WindowCompent;
import com.loujie.www.designpattern.create.builder.WoodCompent;

/**
 * 杰哥房子构造器
 * 
 * @author loujie
 *
 */
public class JiegeHouseBuilder implements HouseBuilder {

	@Override
	public WoodCompent builderWoodCompent() {
		System.err.println("构建了木头");
		return new WoodCompent("杰哥木头");
	}

	@Override
	public WindowCompent builderWindowCompent() {
		System.err.println("构建了窗户");
		return new WindowCompent("杰哥窗户");
	}

	@Override
	public FloorCompent builderFloorCompent() {
		System.err.println("构建地板");
		return new FloorCompent("杰哥地板");
	}

}
