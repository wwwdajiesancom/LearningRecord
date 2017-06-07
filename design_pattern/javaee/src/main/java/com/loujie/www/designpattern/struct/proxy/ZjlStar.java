package com.loujie.www.designpattern.struct.proxy;

/**
 * 周杰伦明星
 * 
 * @author loujie
 *
 */
public class ZjlStar implements Star {

	@Override
	public void negotiate() {
		System.out.println("ZjlStar.negotiate()");
	}

	@Override
	public void signContract() {
		System.out.println("ZjlStar.signContract()");

	}

	@Override
	public void sing() {
		System.out.println("ZjlStar.sing()，我是小周周呀");
	}

	@Override
	public void receivables() {
		System.out.println("ZjlStar.receivables()");

	}

}
