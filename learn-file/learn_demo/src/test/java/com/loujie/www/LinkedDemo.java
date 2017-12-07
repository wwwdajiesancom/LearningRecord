package com.loujie.www;

public class LinkedDemo {

	public static void main(String[] args) {
		
		// add,remove,update,get
		
		
				
	}
	
	enum Atest {

		A("abc"), B("bcd");

		private Atest(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return this.name;
		}

	}
}

class LinkedJie {

	private Node first;

	private Node last;

	private int size = 0;

	/**
	 * 添加节点
	 * 
	 * @param content
	 */
	public boolean add(Object content) {
		// 这个是需要增加的节点
		Node addNode = new Node(content);

		// 1.如果第一个没有的话,说明为空
		if (this.first == null) {
			this.first = addNode;
			this.last = this.first;
			size++;
			return true;
		}
		// 2.第一个存在
		// 2.1需要向最后一个追加
		this.last.addNext(addNode);
		this.last = addNode;
		size++;
		return true;
	}

	public boolean remove(Object content) {
		if (size == 0) {
			return false;
		}

		return true;
	}

	class Node {

		private Node prev;

		private Object content;

		private Node next;

		public Node() {
		}

		public Node(Object content) {
			this.content = content;
		}

		public Node getPrev() {
			return prev;
		}

		public Object getContent() {
			return content;
		}

		public Node getNext() {
			return next;
		}

		public void addNext(Node next) {
			this.next = next;
			next.prev = this;
		}

	}

}
