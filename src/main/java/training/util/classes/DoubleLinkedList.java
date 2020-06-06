package training.util.classes;

import java.util.NoSuchElementException;

/**
 * Collections which represent a link lists of nodes
 * 
 * @author JoseAlejandro
 *
 * @param <T>
 */
public class DoubleLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;

	private long size;

	public DoubleLinkedList() {
	}

	public DoubleLinkedList(T[] array) {
		for (int index = 0; index < array.length; index++) {
			addElement(array[index]);
		}
	}

	public long getSize() {
		return size;
	}

	/**
	 * Complexity Time O(1)
	 * 
	 * @param value
	 * @return
	 */
	public DoubleLinkedList<T> addElementAtHead(T value) {
		Node<T> newNode = new Node<T>(value);

		if (head != null && tail != null) {
			linkNodeBefore(newNode, head);
			head = newNode;
		} else {
			head = newNode;
			tail = head;

		}
		size++;
		return this;
	}

	/**
	 * Complexity Time O(1)
	 * 
	 * @param value
	 * @return
	 */
	public DoubleLinkedList<T> addElement(T value) {

		Node<T> newNode = new Node<T>(value);
		if (head == null) {
			head = newNode;
			tail = head;
		} else {
			linkNodeAfter(newNode, tail);
			tail = newNode;
		}

		size++;
		return this;
	}

	/**
	 * Complexity Time O(n)
	 * 
	 * @param value
	 * @return
	 */
	public void insertElement(T value, int position) {

		Node<T> newNode = new Node<T>(value);
		if (head == null) {
			head = newNode;
			tail = head;
		} else {
			Node<T> nodePos = getNodeAtPosition(position);
			linkNodeBefore(newNode, nodePos);
			if (position == 0) {
				head = newNode;
			}
		}

		size++;
	}

	/*
	 * Complexity Time O(1)
	 */
	public T getFirst() {
		return getValueIfExists(head);
	}

	/*
	 * Complexity Time O(1)
	 */
	public T getLast() {
		return getValueIfExists(tail);
	}

	public T get(long position) {
		return getValueIfExists(getNodeAtPosition(position));
	}

	/*
	 * Complexity Time O(n)
	 */
	public boolean containsElemet(T value) {
		return findNode(new Node<T>(value)) == null ? false : true;
	}

	/*
	 * Complexity Time O(n)
	 */
	public void remove(long position) {
		Node<T> nodeTemp = getNodeAtPosition(position);
		deleteNode(nodeTemp);
		size--;
	}

	public void remove(T value) {
		Node<T> nodeTemp = findNode(new Node<T>(value));
		deleteNode(nodeTemp);
		size--;
	}

	public String printLinkedList() {
		StringBuilder sb = new StringBuilder();
		Node<T> nodeTemp = head;

		while (nodeTemp != null) {
			sb.append(nodeTemp.toString());
			sb.append("\n");
			nodeTemp = nodeTemp.nextNode;
		}

		return sb.toString();
	}

	private T getValueIfExists(Node<T> nodeTemp) {
		if (nodeTemp == null) {
			throw new NoSuchElementException("Value not found");
		}

		return nodeTemp.getValue();
	}

	private Node<T> getNodeAtPosition(long position) {

		int nodesTransversed = 0;

		Node<T> nodeTemp = head;

		while (nodesTransversed++ < position && nodeTemp != null) {
			nodeTemp = nodeTemp.nextNode;
		}

		return nodeTemp;
	}

	/*
	 * Complexity Time O(n)
	 */
	private Node<T> findNode(Node<T> node) {
		Node<T> nodeTemp = head;

		while (nodeTemp != null) {
			if (nodeTemp.value.equals(node.value)) {
				break;
			}
			nodeTemp = nodeTemp.nextNode;
		}

		return nodeTemp;
	}

	private void deleteNode(Node<T> node) {

		if (node.prevNode != null) {
			node.prevNode.nextNode = node.nextNode;
		} else {
			head = node.nextNode;
		}

		if (node.nextNode != null) {
			node.nextNode.prevNode = node.prevNode;
		} else {
			tail = node.prevNode;
		}

		node = null;
	}

	private void linkNodeBefore(Node<T> node, Node<T> nodeRef) {

		if (nodeRef.prevNode != null) {
			nodeRef.prevNode.nextNode = node;
		}
		node.prevNode = nodeRef.prevNode;
		node.nextNode = nodeRef;
		nodeRef.prevNode = node;

	}

	private void linkNodeAfter(Node<T> node, Node<T> nodeRef) {

		if (nodeRef.nextNode != null) {
			nodeRef.nextNode.prevNode = node;
		}

		node.nextNode = nodeRef.nextNode;
		node.prevNode = nodeRef;
		nodeRef.nextNode = node;

	}

	/**
	 * Node Element representation
	 * 
	 * @author JoseAlejandro
	 *
	 * @param <T>
	 */
	private static class Node<T> {
		private T value;
		private Node<T> prevNode;
		private Node<T> nextNode;

		public Node(T value) {
			this.value = value;
			this.prevNode = null;
			this.nextNode = null;
		}

		public T getValue() {
			return value;
		}

		@Override
		public String toString() {

			String prev = null;
			String next = null;

			if (prevNode != null) {
				prev = prevNode.getValue().toString();
			}
			if (nextNode != null) {
				next = nextNode.getValue().toString();
			}

			return String.format("[value:%s, prevNode:%s, nextNode:%s]", value, prev, next);
		}

	}

}
