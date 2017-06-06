package com.loujie.www.designpattern.create.signleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class SignletonDemo {

	@Test
	public void threadTest() throws InterruptedException {
		for (int n = 0; n < 150; n++) {
			int count = 1000;
			final CountDownLatch cdl = new CountDownLatch(count);
			long start = System.currentTimeMillis();
			for (int j = 0; j < count; j++) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						for (int i = 0; i < 100; i++) {
							@SuppressWarnings("unused")
							EnumObject eObject = EnumObject.INSTANCE;
						}
						cdl.countDown();
					}
				}).start();
			}
			cdl.await();
			long end = System.currentTimeMillis();
			System.err.println("list.add(" + (end - start) + ");");
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void reflect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {
		Class<HungryManObject> class1 = (Class<HungryManObject>) Class.forName(HungryManObject.class.getName());
		Constructor<HungryManObject> constructor = class1.getDeclaredConstructor(null);
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	@Test
	public void timeTest() {
		List<Integer> list = new ArrayList<>();

		Collections.sort(list);

		long total = 0;
		for (int i = 10; i < list.size() - 10; i++) {
			total += list.get(i);
		}
		System.err.println("avg:" + total / (list.size() - 20));

	}

}
