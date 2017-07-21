package com.loujie.www.baseics;

import java.util.Arrays;

import org.junit.Test;

public class CDemo {

	@Test
	public void lll() {
		String[] ll = "oooooooxoooooooo".split("o");
		System.err.println(Arrays.toString(ll));
		System.err.println(ll.length);
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