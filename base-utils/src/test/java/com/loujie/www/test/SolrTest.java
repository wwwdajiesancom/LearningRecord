package com.loujie.www.test;

import org.junit.Test;

public class SolrTest {

	@Test
	public void solr1() {
		this.li();
	}

	public void li() {
		this.className();
		System.err.println("----------------end");
	}

	private String className() {
		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		for (StackTraceElement item : stack) {
			String[] classNames = item.getClassName().split("\\.");
			System.err.println(classNames[classNames.length - 1]);
			System.err.println(item.getMethodName());
		}
		return null;
	}

}
