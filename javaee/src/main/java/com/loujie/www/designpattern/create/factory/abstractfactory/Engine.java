package com.loujie.www.designpattern.create.factory.abstractfactory;

/**
 * 引擎
 * 
 * @author loujie
 *
 */
public interface Engine {

	public void runSpeed();// 运行的转速

}

/**
 * 高端引擎
 * 
 * @author loujie
 *
 */
class HightEngine implements Engine {

	@Override
	public void runSpeed() {
		System.err.println("高端运行转速是:" + 1000l * 1024);

	}

}

/**
 * 低端引擎
 * 
 * @author loujie
 *
 */
class LowEngine implements Engine {

	@Override
	public void runSpeed() {
		System.err.println("低端运行转速是:" + 1000L * 32);
	}

}
