package training.easy.linkedlists;

import training.util.classes.DoubleLinkedList;

/**
 * Use a custom build in Linked List
 * 
 * @author JoseAlejandro
 *
 */
public class LinkedListConstructor {

	public static void main(String[] args) {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();

		System.out.println(list);
		System.out.println(list.getSize());

		list.addElement(1);
		System.out.println(list);
		System.out.println();
		list.addElement(2);
		System.out.println(list);
		System.out.println();
		list.addElement(3);
		System.out.println(list);
		System.out.println();

		list.addElementAtHead(0);
		System.out.println(list);
		System.out.println();

		System.out.println(list.getSize());

		System.out.println(list.containsElemet(4));

		list.insertElement(4, 2);

		System.out.println(list);
		System.out.println();

		System.out.println(list.containsElemet(4));

		System.out.println(list);

		list.remove(3);
		System.out.println(list.getSize());
		System.out.println(list);
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		System.out.println();
		list.remove(list.get(4));

	}
}
