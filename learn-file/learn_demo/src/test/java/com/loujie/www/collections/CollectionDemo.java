package com.loujie.www.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * 容器
 * 
 * @author loujie
 *
 */
public class CollectionDemo {

	@Test
	public void collectionDemo() {
		// 顶层容器
		Set<String> ses = new HashSet<>();
		for (int i = 1; i < 10; i++) {
			ses.add("" + i);
		}
		List<Object> list = new ArrayList<>();
		list.add(1234);

		list.remove(0);
		Set<String> ses2 = new HashSet<>();
		ses2.add("5");
		ses2.add("9");

		ses.retainAll(ses2);
		System.err.println(Arrays.toString(ses.toArray()));
		System.err.println(Arrays.toString(ses2.toArray()));

	}

	@Test
	public void linkedDemo() {
		LinkedList<Object> ll = new LinkedList<>();

		ll.add(new Object());
		ll.remove(new Object());
		// 向后追加

	}

	@Test
	public void setsDemo() {
		Iterator<Object> iterators = null;
		Enumeration<Object> enumeration = null;
	}

	@Test
	public void mapDemo() {
		String abc = new String("abc");
		System.err.println(abc.equals("abc"));
		
	}

	public void setDemo() {

	}

	class Node {
		Node prev;
		Object item;
		Node next;

		public Node(Node prev, Object item, Node next) {
			super();
			this.prev = prev;
			this.item = item;
			this.next = next;
		}

	}

	@Test
	public void arrayDemo() {
		int index = 2;// c去掉
		int size = 9;// 内容长度
		String[] abc = {"a", "b", "c", "d", "e", null, null, null, "f", null};
		System.err.println(Arrays.toString(abc));
		System.arraycopy(abc, index + 1, abc, index, size - index - 1);
		abc[--size] = null;
		System.err.println(Arrays.toString(abc));

	}

}
