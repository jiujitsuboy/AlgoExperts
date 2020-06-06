package training.easy.binarytrees;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Queue;

import training.util.classes.TreeNode;

/**
 * It sum the depth of all the nodes of the tree
 * 
 * @author JoseAlejandro
 *
 */
public class NodeDepths {

	public static void main(String[] args) {
		TreeNode<Double> node6 = new TreeNode<Double>(6d, null, null);
		TreeNode<Double> node7 = new TreeNode<Double>(7d, null, null);
		TreeNode<Double> node3 = new TreeNode<Double>(3d, node6, node7);
		TreeNode<Double> node8 = new TreeNode<Double>(8d, null, null);
		TreeNode<Double> node9 = new TreeNode<Double>(9d, null, null);
		TreeNode<Double> node4 = new TreeNode<Double>(4d, node8, node9);
		TreeNode<Double> node5 = new TreeNode<Double>(5d, null, null);
		TreeNode<Double> node2 = new TreeNode<Double>(2d, node4, node5);
		TreeNode<Double> node1 = new TreeNode<Double>(1d, node2, node3);

		System.out.println(findNodeDepthIterative(node1));
		System.out.println(findNodeDepthRecursive(node1, 0));

	}

	/**
	 * Using a queue (it could be a stack also),we take each node, take it depth, and store it children in the 
	 * queue, and iterate until the queue become empty.
	 * 
	 * Complexity
	 * 
	 * Time 0(n)=> we traverse all the node if the tree
	 * 
	 * Space O(n)=> we store 2 level of the tree in almost all the cases. If a queue were used it would be O(h)
	 * where h is the height of the tree (basically we store the longest branch in the queue).
	 * 
	 * 
	 * @param node
	 * @return
	 */
	public static int findNodeDepthIterative(TreeNode<Double> node) {
		int sumOfDephs = 0;
		int levelValue = 0;

		Queue<SimpleEntry<TreeNode<Double>, Integer>> queue = new LinkedList<AbstractMap.SimpleEntry<TreeNode<Double>, Integer>>();

		queue.add(new SimpleEntry<TreeNode<Double>, Integer>(node, 0));

		while (!queue.isEmpty()) {
			SimpleEntry<TreeNode<Double>, Integer> currentEntry = queue.poll();
			TreeNode<Double> currentNode = currentEntry.getKey();
			levelValue = currentEntry.getValue();
			sumOfDephs += levelValue;
			if (currentNode.getLeftChild() != null) {
				queue.add(new SimpleEntry<TreeNode<Double>, Integer>(currentNode.getLeftChild(), levelValue + 1));
			}
			if (currentNode.getRightChild() != null) {
				queue.add(new SimpleEntry<TreeNode<Double>, Integer>(currentNode.getRightChild(), levelValue + 1));
			}
		}

		return sumOfDephs;
	}

	/**
	 * We call the function for every child node, incrementing the level value, until we reach leafs
	 * 
	 * Complexity
	 * 
	 * Time 0(n)=> we traverse all the node if the tree
	 * 
	 * Space O(h)=> we store frame in the stack for each recursive call. h is the height of the tree
	 * 
	 * @param node
	 * @param levelValue
	 * @return
	 */
	public static int findNodeDepthRecursive(TreeNode<Double> node, int levelValue) {

		if(node==null) {
			return 0;
		}
		return levelValue + findNodeDepthRecursive(node.getLeftChild(), levelValue + 1)
				+ findNodeDepthRecursive(node.getRightChild(), levelValue + 1);
	}

}
