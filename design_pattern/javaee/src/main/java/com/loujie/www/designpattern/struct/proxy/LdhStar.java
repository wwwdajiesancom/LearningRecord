package com.loujie.www.designpattern.struct.proxy;

public class LdhStar implements Star {

	@Override
	public void negotiate() {
		System.out.println("LdhStar.negotiate()");
	}

	@Override
	public void signContract() {
		System.out.println("LdhStar.signContract()");
	}

	@Override
	public void sing() {
		System.out.println("LdhStar.sing(),我是刘德华");

	}

	@Override
	public void receivables() {
		System.out.println("LdhStar.receivables()");
	}

}
