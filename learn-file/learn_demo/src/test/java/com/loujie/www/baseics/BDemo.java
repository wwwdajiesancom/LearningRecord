package com.loujie.www.baseics;

import java.util.Scanner;

import org.junit.Test;

public class BDemo {

	@Test
	public void test2() {
		String[][] abc = new String[1][1];

	}

	public static class Dl {
		static int ox() {

			return 11;
		}
	}

	private Scanner scanner;

	public static void abc() {
	}

	public static abstract class Abc {
		abstract void lb();
	}

	public static int abc(String srt) {
		int abc = 123;
		return 0;
	}

	String str3 = "大家好";

	@Test
	public void shortDemo() {
		String str1 = "大家" + "好";
		String str2 = "大家好";
		System.err.println(str3 == str2);
	}

	@Test
	public void switchDemo() {
		Demo lDemo = Demo.A;
		switch (lDemo) {
			case A :
				break;
			case B :
				break;
			case C :
				break;
			case D :
				break;
			default :
				break;
		}
	}

	@Test
	public void forDemo() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j <= i; j++) {
				System.err.print(j + "*" + i + "=" + j * i);
				System.err.print("   ");
			}
			System.err.println();
		}
		String abc = Ill.abc;
		System.err.println(abc);
	}

	enum Demo {
		A, B, C, D;
	}

}

interface Ill {

	static String abc = "abc";

	public void abc();

}
