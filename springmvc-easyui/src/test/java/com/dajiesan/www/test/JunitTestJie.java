package com.dajiesan.www.test;

import java.nio.charset.Charset;

import org.junit.Test;

public class JunitTestJie {

	@Test
	public void date_test() {

		char[] abc = new char[4];
		for (int i = 0; i < abc.length; i++) {
			System.err.println("ss" + abc[i] + "ss");
		}

	}

	public int getII(int i) {
		System.err.println("getII(" + i + ")");
		return i + 1;
	}

	public int getI() {
		System.err.println("getI()");
		return 0;
	}

	public int getJ() {
		System.err.println("getJ()");
		return 4;
	}

	public String getTableEntity(String tableName) {
		String[] segs = tableName.split("_");
		StringBuilder tableBuilder = new StringBuilder();
		for (String seg : segs) {
			tableBuilder.append((seg.charAt(0) + "").toUpperCase()).append(seg.substring(1, seg.length()));
		}
		return tableBuilder.toString();
	}

	@Test
	public void iabc() {
		System.err.println("$1".getBytes(Charset.forName("")));
	}

}
