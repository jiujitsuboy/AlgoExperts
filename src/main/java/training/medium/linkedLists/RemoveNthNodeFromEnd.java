package training.medium.linkedLists;

import training.util.classes.SingleLinkedList;

/**
 * Using a raw single linked list (no pointer to previous element), remove the
 * nth element from the list (counting from tail to back).
 * 
 * @author JoseAlejandro
 *
 */
public class RemoveNthNodeFromEnd {

	public static void main(String[] args) {

		try {
			Node<Integer> node = init();
			System.out.println(removeNthElementFromEnd(node, 10));
			node = init();
			System.out.println(removeNthElementFromEnd(node, 9));
			node = init();
			System.out.println(removeNthElementFromEnd(node, 8));
			node = init();
			System.out.println(removeNthElementFromEnd(node, 7));
			node = init();
			System.out.println(removeNthElementFromEnd(node, 6));
			node = init();
			System.out.println(removeNthElementFromEnd(node, 1));
			node = init();
			System.out.println(removeNthElementFromEnd(node, 11));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Node<Integer> init() {
		Node<Integer> node0 = new Node<Integer>(0);
		Node<Integer> node1 = new Node<Integer>(1);
		Node<Integer> node2 = new Node<Integer>(2);
		Node<Integer> node3 = new Node<Integer>(3);
		Node<Integer> node4 = new Node<Integer>(4);
		Node<Integer> node5 = new Node<Integer>(5);
		Node<Integer> node6 = new Node<Integer>(6);
		Node<Integer> node7 = new Node<Integer>(7);
		Node<Integer> node8 = new Node<Integer>(8);
		Node<Integer> node9 = new Node<Integer>(9);

		node0.setNextNode(node1);
		node1.setNextNode(node2);
		node2.setNextNode(node3);
		node3.setNextNode(node4);
		node4.setNextNode(node5);
		node5.setNextNode(node6);
		node6.setNextNode(node7);
		node7.setNextNode(node8);
		node8.setNextNode(node9);

		return node0;
	}

	/**
	 * Because we don't have a way to navigate from tail to head. we use two points,
	 * one which start with an offset of the position to find backward and another
	 * which start from zero. Once the offset gets out of the size of the array, we
	 * found the position we need to remove. If offset is bigger that array size an
	 * exception is throw.
	 * 
	 * Complexity
	 * 
	 * Time O(n) => we need to traverse the array until we go outside this
	 * boundaries
	 * 
	 * Space O(1) => we used only two pointers regarding the size of the input.
	 * 
	 * 
	 * @param list
	 * @param nthElement
	 * @throws Exception
	 */
	public static Node<Integer> removeNthElementFromEnd(Node<Integer> head, int nthElement) throws Exception {
		Node<Integer> currentNode = head;
		Node<Integer> previousNode = null;
		Node<Integer> offsetNode = head;

		int nroIterations = 1;

		while (offsetNode != null) {
			if (nroIterations > nthElement) {
				previousNode = currentNode;
				currentNode = currentNode.getNextNode();
			}
			offsetNode = offsetNode.getNextNode();
			nroIterations++;
		}
		if (currentNode == null) {
			throw new Exception("nthElement less than 1");
		} else if (currentNode.equals(head) && (nthElement - (nroIterations - 1)) != 0) {
			throw new Exception("nthElement greater than linked list size");
		} else if (currentNode.equals(head)) {
			previousNode = currentNode;
			head = head.nextNode;
			previousNode = null;
		} else {
			previousNode.setNextNode(currentNode.getNextNode());
			currentNode = null;
		}
		
		return head;
	}

	public static void removeNthElementFromEnd(SingleLinkedList<Integer> list, int nthElement) throws Exception {
		int index = 0;
		int offset = nthElement;

		while (offset < list.getSize()) {
			offset++;
			index++;

		}

		if (nthElement <= list.getSize()) {
			list.remove(index);
		} else {
			throw new Exception("nthElement greater than linked list size");
		}
	}

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

		public Node<T> getNextNode() {
			return nextNode;
		}

		public void setNextNode(Node<T> node) {
			nextNode = node;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			Node<T> nodeTemp = this;

			while (nodeTemp != null) {
				sb.append(nodeTemp.getValue());
				nodeTemp = nodeTemp.nextNode;
				if (nodeTemp != null) {
					sb.append("->");
				}
			}

			return sb.toString();
		}

	}
}
