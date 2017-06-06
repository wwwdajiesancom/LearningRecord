package com.loujie.www.designpattern.create.builder.weiwei;

import com.loujie.www.designpattern.create.builder.FloorCompent;
import com.loujie.www.designpattern.create.builder.HouseBuilder;
import com.loujie.www.designpattern.create.builder.WindowCompent;
import com.loujie.www.designpattern.create.builder.WoodCompent;

/**
 * 伟伟房子构建组件
 * 
 * @author loujie
 *
 */
public class WeiWeiHouseBuilder implements HouseBuilder {

	@Override
	public WoodCompent builderWoodCompent() {
		System.err.println("构建了木头");
		return new WoodCompent("伟伟木头");
	}

	@Override
	public WindowCompent builderWindowCompent() {
		System.err.println("构建了窗户");
		return new WindowCompent("伟伟窗户");
	}

	@Override
	public FloorCompent builderFloorCompent() {
		System.err.println("构建了地板");
		return new FloorCompent("伟伟地板");
	}

}
