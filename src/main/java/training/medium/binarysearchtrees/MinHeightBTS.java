package training.medium.binarysearchtrees;

import com.sun.source.tree.Tree;

import training.util.classes.BST;
import training.util.classes.TreeNode;

/**
 * Create a BST from an order array, with the minimum length possible for the
 * tree (balanced tree)
 * 
 * @author JoseAlejandro
 *
 */
public class MinHeightBTS {

	public static void main(String[] args) {
		Integer[] array = new Integer[] { 1, 2, 5, 7, 10, 13, 14, 15, 22 };

		BST<Integer> bst = new BST<Integer>();

		insertElementRecursive(array, bst);
//		TreeNode<Integer> tree = insertElementRecursive(array);

//		System.out.println(tree.toString());

		System.out.println(bst.toString());

	}

	public static <T extends Comparable<T>> void insertElementRecursive(T[] list, BST<T> tree) {

		int initialIndex = 0;
		int lastIndex = list.length - 1;

		insertElementHelper(list, tree, initialIndex, lastIndex);
	}

	/**
	 * Recursive method that perform a binary traverse of the array to insert the
	 * elements in the BST in that order 
	 * Complexity: Time O(Nlog(N)) => insert all the array elements, and every insertion takes log(n) 
	 * 			   Space O(N) => because we
	 * are storing the whole array as a BTS
	 * 
	 * @param <T>
	 * @param list
	 * @param tree
	 * @param initialIndex
	 * @param lastIndex
	 */
	public static <T extends Comparable<T>> void insertElementHelper(T[] list, BST<T> tree, int initialIndex,
			int lastIndex) {

		if (initialIndex <= lastIndex) {

			int compareIndex = initialIndex + (lastIndex - initialIndex) / 2;

			tree.InsertNode(list[compareIndex]);
			insertElementHelper(list, tree, initialIndex, compareIndex - 1);
			insertElementHelper(list, tree, compareIndex + 1, lastIndex);
		}

	}

	public static <T extends Comparable<T>> TreeNode<T> insertElementRecursive(T[] list) {

		int initialIndex = 0;
		int lastIndex = list.length - 1;

		TreeNode<T> tree = null;

		tree = insertElementHelper(list, tree, initialIndex, lastIndex);

		return tree;
	}

	/**
	 * Recursive method that perform a binary traverse of the array to insert the
	 * elements as the left or right node of the parent node supplied Complexity:
	 * Time O(N) => insert all the array elements, using a constant time operation
	 * and not the log(n) BST insert method Space O(N) => because we are storing the
	 * whole array as a BTS
	 * 
	 * @param <T>
	 * @param list
	 * @param tree
	 * @param initialIndex
	 * @param lastIndex
	 */
	public static <T extends Comparable<T>> TreeNode<T> insertElementHelper(T[] list, TreeNode<T> tree,
			int initialIndex, int lastIndex) {

		if (initialIndex <= lastIndex) {

			TreeNode<T> nextNode = null;

			int compareIndex = initialIndex + (lastIndex - initialIndex) / 2;

			if (tree == null) {
				tree = new TreeNode<T>(list[compareIndex], null, null);
				nextNode = tree;
			} else if (list[compareIndex].compareTo(tree.getValue()) < 0) {
				tree.setLeftChild(new TreeNode<T>(list[compareIndex], null, null));
				nextNode = tree.getLeftChild();
			} else {
				tree.setRightChild(new TreeNode<T>(list[compareIndex], null, null));
				nextNode = tree.getRightChild();
			}

			insertElementHelper(list, nextNode, initialIndex, compareIndex - 1);
			insertElementHelper(list, nextNode, compareIndex + 1, lastIndex);
		}

		return tree;

	}

	public static <T extends Comparable<T>> TreeNode<T> insertElementRecursive2(T[] list) {

		int initialIndex = 0;
		int lastIndex = list.length - 1;

		return insertElementHelper(list, initialIndex, lastIndex);

	}

	/** REFACTOR
	 * Recursive method that perform a binary traverse of the array to insert the
	 * elements as the left or right node of the parent node supplied. 
	 * Complexity:  Time O(N) => insert all the array elements, using a constant time operation
	 * 							 and not the log(n) BST insert method 
	 * 				Space O(N) => because we are storing the whole array as a BTS
	 * 
	 * @param <T>
	 * @param list
	 * @param tree
	 * @param initialIndex
	 * @param lastIndex
	 */
	public static <T extends Comparable<T>> TreeNode<T> insertElementHelper(T[] list, int initialIndex, int lastIndex) {

		TreeNode<T> tree  = null;
		
		if (initialIndex <= lastIndex) {

			int compareIndex = initialIndex + (lastIndex - initialIndex) / 2;

			tree = new TreeNode<T>(list[compareIndex], null, null);

			tree.setLeftChild(insertElementHelper(list, initialIndex, compareIndex - 1));
			tree.setRightChild(insertElementHelper(list, compareIndex + 1, lastIndex));
		}

		return tree;

	}

}
