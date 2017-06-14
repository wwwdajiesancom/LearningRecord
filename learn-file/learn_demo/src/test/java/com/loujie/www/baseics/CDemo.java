package com.loujie.www.baseics;

import java.text.ParseException;

import org.junit.Test;

public class CDemo {

	@Test
	public void ll() throws ParseException {
		
	}

}

class Ni {
	public static String abc = getAbc();

	public static String getAbc() {
		System.err.println("getAbc()");
		System.err.println(Ix.abc);
		return "ooxx";
	}

	public void sys() {
		System.err.println("jsdlkfjdlsfjklsdjfkl");
	}

	static class Ix {
		public static final String abc = getOOxx();

		public static String getOOxx() {
			System.err.println("GETooXX");
			return "OOxx";
		}

	}

}