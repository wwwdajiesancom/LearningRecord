package com.loujie.www.test;

import java.util.Comparator;

public class TestBImpl {

	interface Name {
		String getName();
	}

	public String get() {
		Comparator<Integer> c = Integer::compare;
		Name name = () -> {
			System.err.println(this);
			return this + "";
		};
		return name.getName();
	}

}
