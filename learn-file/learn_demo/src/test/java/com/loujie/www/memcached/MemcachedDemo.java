package com.loujie.www.memcached;

import org.junit.Test;

public class MemcachedDemo {

	@Test
	public void setDemo1() {
		System.err.println(MemcachedUtils.Store.set("jiege", "杰哥", 0));
		System.err.println(MemcachedUtils.Store.set("weiwei", "女", 0));
	}

	@Test
	public void addDemo() {
		System.err.println(MemcachedUtils.Store.add("jiege", "loujie", 456));
	}

	@Test
	public void getDemo() {
		System.err.println(MemcachedUtils.Query.get("ji"));
	}

	@Test
	public void getsDemo() {
		try {
			MemcachedUtils.Store.set("value", 10, 0);
			System.err.println(MemcachedUtils.Query.get("value"));
			MemcachedUtils.Store.set("value", 10, 0);
			System.err.println(MemcachedUtils.Query.get("value"));
			MemcachedUtils.Store.set("value", 10, 0);
			System.err.println(MemcachedUtils.Query.get("value"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
