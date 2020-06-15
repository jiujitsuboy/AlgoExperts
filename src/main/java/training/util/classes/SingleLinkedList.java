package training.util.classes;

/**
 * Collections which represent a link lists of nodes
 * 
 * @author JoseAlejandro
 *
 * @param <T>
 */
public class SingleLinkedList<T> {
	private Node<T> head;
	private Node<T> tail;

	private long size;

	public SingleLinkedList() {
	}

	public SingleLinkedList(T[] array) {
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
	public SingleLinkedList<T> addElementAtHead(T value) {
		Node<T> newNode = new Node<T>(value);

		if (head != null && tail != null) {
			newNode.nextNode = head;
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
	public SingleLinkedList<T> addElement(T value) {

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
			Node<T> nodePos = getNodeAtPosition(position - 1);
			linkNodeAfter(newNode, nodePos);
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
	public T remove(long position) {
		Node<T> nodeTemp = getNodeAtPosition(position - 1);
		size--;
		return deleteNode(nodeTemp);		
	}

	public T remove(T value) {
		Node<T> nodeTemp = findPreviousNode(new Node<T>(value));
		size--;
		return deleteNode(nodeTemp);		
	}
	
	public T removeFirst() {
		return deleteNode(head);
	}

	public T removeLast() {
		return deleteNode(tail);
	}

	@Override
	public String toString() {
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
		T value = null;
		if (nodeTemp != null) {
			value = nodeTemp.getValue();
		}

		return value;
	}

	private Node<T> getNodeAtPosition(long position) {

		int nodesTransversed = 0;

		Node<T> nodeTemp = (position < 0) ? null : head;

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

	/*
	 * Complexity Time O(n)
	 */
	private Node<T> findPreviousNode(Node<T> node) {
		Node<T> nodeTemp = head;
		Node<T> nodePrev = null;

		while (nodeTemp != null) {
			if (nodeTemp.value.equals(node.value)) {
				break;
			}
			nodePrev = nodeTemp;
			nodeTemp = nodeTemp.nextNode;
		}

		return nodePrev;
	}

	private T deleteNode(Node<T> prevNode) {

		Node<T> nodeToErase = null;

		// erase head
		if (prevNode == null) {
			head = head.nextNode;
		} else {
			nodeToErase = prevNode.nextNode;
		}

		if (nodeToErase != null) {
			prevNode.nextNode = nodeToErase.nextNode;
		} else {
			tail = head;
		}

		return prevNode.getValue();
	}

	private void linkNodeAfter(Node<T> node, Node<T> nodeRef) {

		node.nextNode = nodeRef.nextNode;
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
		private Node<T> nextNode;

		public Node(T value) {
			this.value = value;
			this.nextNode = null;
		}

		public T getValue() {
			return value;
		}

		@Override
		public String toString() {
			String next = null;

			if (nextNode != null) {
				next = nextNode.getValue().toString();
			}

			return String.format("[value:%s, nextNode:%s]", value, next);
		}

	}

}
