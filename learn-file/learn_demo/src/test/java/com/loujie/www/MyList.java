package com.loujie.www;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<E> implements List<E> {

	// list,有序,可重复,应该有一个数组
	private Object[] value = new Object[0];

	public int size() {
		return this.value.length;
	}

	public boolean isEmpty() {
		if (this.value.length == 0) {
			return true;
		}
		return false;
	}

	public boolean contains(Object o) {
		for (int i = 0; i < this.value.length; i++) {
			if (this.oequals(this.value[i], o)) {
				return true;
			}
		}
		return false;
	}

	private boolean oequals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 != null) {
			if (o1.equals(o2)) {
				return true;
			}
		}
		return false;
	}

	public Object[] toArray() {
		return this.toArray(new Object[this.value.length]);
	}

	public <T> T[] toArray(T[] a) {
		System.arraycopy(this.value, 0, a, 0, this.value.length);
		return a;
	}

	public boolean add(E e) {
		Object[] desObject = new Object[this.value.length + 1];
		System.arraycopy(this.value, 0, desObject, 0, this.value.length);
		desObject[this.value.length] = e;
		this.value = desObject;
		return true;
	}

	public boolean remove(Object o) {
		int equalsIndex = -1;
		// 1.找到位置
		for (int i = 0; i < this.value.length; i++) {
			if (this.oequals(this.value[i], o)) {
				equalsIndex = i;
				break;
			}
		}
		// 2.如果不存在，返回false
		if (equalsIndex == -1) {
			return false;
		}
		// 3.拷贝
		Object[] desObject = new Object[this.value.length - 1];
		// 3.1第一个位置
		if (equalsIndex == 0) {
			System.arraycopy(this.value, 1, desObject, 0, this.value.length - 1);
		} else {
			System.arraycopy(this.value, 0, desObject, 0, equalsIndex);
			System.arraycopy(this.value, equalsIndex + 1, desObject, equalsIndex, this.value.length - equalsIndex - 1);
		}
		this.value = desObject;
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		if (c != null && c.size() > 0) {
			// 1.判断长度
			if (this.value.length < c.size()) {
				return false;
			}
			// 2.遍历判断是否都包含了
			for (Object item : c) {
				if (!this.contains(item)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean addAll(Collection<? extends E> c) {
		if (c != null && c.size() > 0) {
			Object[] cs = c.toArray();
			Object[] destObject = new Object[value.length + cs.length];
			System.arraycopy(this.value, 0, destObject, 0, this.value.length);
			System.arraycopy(cs, 0, destObject, this.value.length, cs.length);
			this.value = destObject;
		}
		return true;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		return false;
	}

	public void clear() {

	}

	public E get(int index) {
		return null;
	}

	public E set(int index, E element) {
		return null;
	}

	public void add(int index, E element) {

	}

	public E remove(int index) {
		return null;
	}

	public int indexOf(Object o) {
		return 0;
	}

	public int lastIndexOf(Object o) {
		return 0;
	}

	public ListIterator<E> listIterator() {
		return null;
	}

	public ListIterator<E> listIterator(int index) {
		return null;
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}

	public Iterator<E> iterator() {
		return null;
	}
}
