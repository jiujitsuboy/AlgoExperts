package training.easy.binarysearchtrees;

import training.util.classes.TreeNode;

public class FindClosestValueInBST {

	public static void main(String[] args) {

		TreeNode<Double> node15 = new TreeNode<Double>(15d, null, null);
		TreeNode<Double> node11 = new TreeNode<Double>(11d, null, null);
		TreeNode<Double> node13 = new TreeNode<Double>(13d, node11, node15);
		TreeNode<Double> node1 = new TreeNode<Double>(1d, null, null);
		TreeNode<Double> node7 = new TreeNode<Double>(7d, null, null);
		TreeNode<Double> node5 = new TreeNode<Double>(5d, node1, node7);
		TreeNode<Double> node10 = new TreeNode<Double>(10d, node5, node13);

		double targetValue = 12;
		double closestValue = findClosestValue(node10, targetValue, Double.MAX_VALUE);

		System.out.println(String.format("The closest value to %.0f is %.0f", targetValue, closestValue));

	}

	private static double findClosestValue(TreeNode<Double> tree, double targetValue, double closestValue) {

		TreeNode<Double> currentNode = tree;

		while (currentNode != null) {
			if (currentNode.getValue() == targetValue) {
				return targetValue;
			}

			if (Math.abs(targetValue - closestValue) > Math.abs(targetValue - currentNode.getValue())) {
				closestValue = currentNode.getValue();
			}

			if (currentNode.getValue() > targetValue) {
				currentNode = currentNode.getLeftChild();
			} else {
				currentNode = currentNode.getRightChild();
			}
		}

		return closestValue;

	}

}
