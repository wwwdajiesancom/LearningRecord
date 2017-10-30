package com.dajiesan.www.test;

import java.nio.charset.Charset;

import org.junit.Test;

public class JunitTestJie {

	@Test
	public void date_test() {
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
