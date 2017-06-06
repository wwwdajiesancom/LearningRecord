package com.loujie.www.designpattern.create.builder;

/**
 * 房子
 * 
 * @author loujie
 *
 */
public class House {

	private WoodCompent woodCompent;// 木头

	private WindowCompent windowCompent;// 窗户

	private FloorCompent floorCompent;// 地板

	public WoodCompent getWoodCompent() {
		return woodCompent;
	}

	public void setWoodCompent(WoodCompent woodCompent) {
		this.woodCompent = woodCompent;
	}

	public WindowCompent getWindowCompent() {
		return windowCompent;
	}

	public void setWindowCompent(WindowCompent windowCompent) {
		this.windowCompent = windowCompent;
	}

	public FloorCompent getFloorCompent() {
		return floorCompent;
	}

	public void setFloorCompent(FloorCompent floorCompent) {
		this.floorCompent = floorCompent;
	}

}
