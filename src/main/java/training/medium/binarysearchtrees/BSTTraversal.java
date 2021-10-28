package training.medium.binarysearchtrees;

import java.util.ArrayList;
import java.util.List;

import training.util.classes.BST;
import training.util.classes.TreeNode;

/**
 * Print a BST in tree options InOrder PreOrder PostOrder
 * 
 * @author JoseAlejandro
 *
 */
public class BSTTraversal {

	public static void main(String[] args) {
		BST<Double> bst = new BST<Double>();

		bst.InsertNode(10d);
		bst.InsertNode(5d);
		bst.InsertNode(15d);
		bst.InsertNode(2d);
		bst.InsertNode(7d);
		bst.InsertNode(12d);
		bst.InsertNode(13d);
		bst.InsertNode(20d);
		bst.InsertNode(21d);

		System.out.println(bst);

		List<Double> values = new ArrayList<>();
		inOrderTransversal(bst.getRoot(), values);
		System.out.print("In Order: ");
		System.out.println(values);
		values = new ArrayList<>();
		preOrderTransversal(bst.getRoot(), values);
		System.out.print("Pre Order: ");
		System.out.println(values);
		values = new ArrayList<>();
		postOrderTransversal(bst.getRoot(), values);
		System.out.print("Post Order: ");
		System.out.println(values);
	}

	/**
	 * Print elements in the order from lower to greater
	 * branch) Complexity Time O(n) Space O(d) => d is the length of the most
	 * largest branch
	 * 
	 * @param <T>
	 * @param tree
	 * @param values
	 */
	private static <T> void inOrderTransversal(TreeNode<T> tree, List<T> values) {
		if (tree != null) {
			inOrderTransversal(tree.getLeftChild(), values);
			values.add(tree.getValue());
			inOrderTransversal(tree.getRightChild(), values);
		}
	}

	
	
	/**
	 * Print elements in the order that they are traverse (root -left branch, right
	 * branch) Complexity Time O(n) Space O(d) => d is the length of the most
	 * largest branch
	 * 
	 * @param <T>
	 * @param tree
	 * @param values
	 */
	private static <T> void preOrderTransversal(TreeNode<T> tree, List<T> values) {
		if (tree != null) {
			values.add(tree.getValue());
			preOrderTransversal(tree.getLeftChild(), values);
			preOrderTransversal(tree.getRightChild(), values);

		}
	}

	/**
	 * Print first the left branch, then the right one and at last the root
	 * branch) Complexity Time O(n) Space O(d) => d is the length of the most
	 * largest branch
	 * 
	 * @param <T>
	 * @param tree
	 * @param values
	 */
	private static <T> void postOrderTransversal(TreeNode<T> tree, List<T> values) {
		if (tree != null) {
			postOrderTransversal(tree.getLeftChild(), values);
			postOrderTransversal(tree.getRightChild(), values);
			values.add(tree.getValue());

		}
	}

}
