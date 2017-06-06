package com.loujie.www.designpattern.create.builder;

/**
 * 房子的组件的构造接口
 * 
 * @author loujie
 *
 */
public interface HouseBuilder {

	WoodCompent builderWoodCompent();// 构建木头

	WindowCompent builderWindowCompent();// 构建窗户

	FloorCompent builderFloorCompent();// 构建地板

}
