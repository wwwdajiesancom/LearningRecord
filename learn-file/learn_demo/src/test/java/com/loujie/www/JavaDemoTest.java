package com.loujie.www;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

public class JavaDemoTest {

	@Test
	public void abc() {

		Collection collection = new ArrayList();

		collection.add(1);
		collection.add(1);
		collection.add(1);
		System.err.println(collection.contains(1));
		System.err.println(collection.size());
		collection.remove(1);
		System.err.println(collection.size());

		Iterator iterator = collection.iterator();
		for (int i = 0; i < 12; i++) {
			System.err.println(iterator.hasNext());
		}

	}

	@Test
	public void copy() {
		System.err.println();

	}

}

abstract class Atest {
	String IP = "192.168.0.123";

	abstract void get();

	interface Bx {
		String PI = "3.1415926";
	}

	class Persion {

		String name = "jiege";

		public String getName() {
			return name;
		}
	}
}

class Nb extends Atest {

	@Override
	void get() {
		
	}
}