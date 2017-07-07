package com.loujie.www.baseics;

import org.junit.Test;

public class CDemo {

	@Test
	public void lll() {
		System.err.println("oxoxoxoxooxoxoxoxoxoxo");
	}

}

class B {

	public final static int v = getV();

	public static int getV() {
		System.err.println("getV");
		return 3;
	}

	static {
		System.err.println("BBBBB");
	}

}