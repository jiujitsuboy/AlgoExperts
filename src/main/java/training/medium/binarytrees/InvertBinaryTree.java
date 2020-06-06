package training.medium.binarytrees;

import java.util.LinkedList;
import java.util.List;

import training.util.classes.BST;
import training.util.classes.TreeNode;

/**
 * interchange sibling nodes on a BST
 * 
 * @author JoseAlejandro
 *
 */
public class InvertBinaryTree {

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
		TreeNode<Double> tree = bst.getRoot();

		invertTreeRecursive(tree);
		invertTreeIterative(tree);
		
		System.out.println(bst);
		

	}

	/**
	 * Interchange the children node of the current node and call again the method
	 * for each child Complexity: Time O(N) Space O(d)=> d is the length of the
	 * longest branch we traverse. d = log(n) -> because this is the length of a
	 * balanced binary tree
	 * 
	 * @param <T>
	 * @param tree
	 */

	public static <T> void invertTreeRecursive(TreeNode<T> tree) {
		if (tree != null) {
			TreeNode<T> tempNode = tree.getLeftChild();
			tree.setLeftChild(tree.getRightChild());
			tree.setRightChild(tempNode);
			invertTreeRecursive(tree.getLeftChild());
			invertTreeRecursive(tree.getRightChild());
		}
	}

	public static <T> void invertTreeIterative(TreeNode<T> tree) {

		LinkedList<TreeNode<T>> queue = new LinkedList<>();
		queue.add(tree);

		while (!queue.isEmpty()) {

			TreeNode<T> current = queue.poll();

			if (current != null) {
				TreeNode<T> tempNode = current.getLeftChild();
				current.setLeftChild(current.getRightChild());
				current.setRightChild(tempNode);
				queue.add(current.getLeftChild());
				queue.add(current.getRightChild());
			}

		}

	}
}
