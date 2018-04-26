package com.loujie.www.test;

public class MyLinkedList {

	private Node first;// 第一个节点
	private Node last;// 最后一个节点
	private int size = 0;// 集合长度

	public void addFirst(Object eleData) {
		Node tempFirst = new Node(eleData);
		if (size == 0) {
			this.first = tempFirst;
			this.last = tempFirst;
		} else {
			tempFirst.next = this.first;
			this.first.prev = tempFirst;
			this.first = tempFirst;
		}
		this.sizePlusPlus();
	}

	public void addLast(Object eleData) {
		Node tempLast = new Node(eleData);
		if (size == 0) {
			this.first = tempLast;
			this.last = tempLast;
		} else {
			tempLast.prev = this.last;
			this.last.next = tempLast;
			this.last = tempLast;
		}
		this.sizePlusPlus();
	}

	public void insert(int indexPostion, Object eleData) {
		this.checkIndex(indexPostion);

		if (indexPostion == size) {
			this.addLast(eleData);
		} else {
			// 比较大小
			Node tempNode = null;
			if ((size >> 1) > indexPostion) {
				tempNode = this.first;
				tempNode = tempNode.getNodeIndexAsc(indexPostion);
			} else {
				tempNode = this.last;
				tempNode = tempNode.getNodeIndexDesc(indexPostion);
			}

			Node tempInsertNode = new Node(eleData);
			tempInsertNode.next = tempNode;
			tempInsertNode.prev = tempNode.prev;
			tempNode.prev = tempInsertNode;
			tempInsertNode.prev.next = tempInsertNode;
			this.sizePlusPlus();
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node temp = this.first;
		if (temp != null) {
			sb.append(temp.eleData);
		}
		for (int i = 1; i < getSize(); i++) {
			temp = temp.next;
			sb.append(",");
			sb.append(temp.eleData);
		}
		sb.append("]");
		return sb.toString();
	}

	private void checkIndex(int indexPostion) {
		if (indexPostion < 0 || indexPostion > getSize()) {
			throw new IllegalArgumentException("参数不合法");
		}
	}

	private void sizePlusPlus() {
		size++;
	}

	private int getSize() {
		return this.size;
	}

	class Node {
		Node prev;// 前一个节点
		Node next;// 下一个节点

		Object eleData;// 元素数据

		public Node(Object eleData) {
			this.eleData = eleData;
		}

		public Node getNodeIndexDesc(int indexPostion) {
			Node temp = this;
			for (int i = getSize() - 1; i > indexPostion; i--) {
				temp = temp.prev;
				if (temp == null) {
					throw new NullPointerException("空指针异常,链表没有那么长");
				}
			}
			return temp;
		}

		public Node getNodeIndexAsc(int indexPostion) {
			Node temp = this;
			for (int i = 1; i <= indexPostion; i++) {
				temp = temp.next;
				if (temp == null) {
					throw new NullPointerException("空指针异常,链表没有那么长");
				}
			}
			return temp;
		}
	}

}
