package com.loujie.www.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class DemoTest {

	class Course {
		private String name;// 名字
		private Integer price;// 价格

		public Course(String name, Integer price) {
			this.name = name;
			this.price = price;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

	}

	@Test
	public void demo1() {
		Course[] courses = { new Course("西游记", 10), new Course("三国演义", 20), new Course("我是程序员", 30) };
		Arrays.sort(courses, new Comparator<Course>() {
			@Override
			public int compare(Course o1, Course o2) {
				return Integer.compare(o1.price, o2.price);
			}
		});
	}

	@Test
	public void demo12() {
		Course[] courses = { new Course("西游记", 10), new Course("三国演义", 20), new Course("我是程序员", 30) };
		Arrays.sort(courses, (o1, o2) -> {
			System.err.println(this);
			return Integer.compare(o1.price, o2.price);
		}

		);

	}

	@Test
	public void demo2() {
		final String abc = "my name jiege";
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.err.println(this);
				System.out.println(abc);
			}
		}).start();
	}

	@Test
	public void demo22() {

	}

	public void sys(Integer t) {
	}

	interface LL {

		<T> List<T> create();

	}

	public <T> List<T> createList(LL ll, @SuppressWarnings("unchecked") T... ts) {
		List<T> list = ll.create();
		for (T t : ts)
			list.add(t);
		return list;
	}

	@Test
	public void deafultDemo() {

	}

	public interface Lizi1 {

		static void abc() {

		}

		default void a() {
			System.err.println("我会游泳");
		}

	}

	public interface Lizi2 {

		default void a() {
			System.err.println("我可以在地上存活");
		}

	}

	class Ds {
		public void a() {
			System.err.println("我可以在地上存活");
		}
	}

	class Cc extends Ds implements Lizi1, Lizi2 {

	}

}
