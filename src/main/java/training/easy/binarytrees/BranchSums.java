package training.easy.binarytrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import training.util.classes.TreeNode;

public class BranchSums {

	public static void main(String[] args) {
		TreeNode<Double> node15 = new TreeNode<Double>(15d, null, null);
		TreeNode<Double> node11 = new TreeNode<Double>(11d, null, null);
		TreeNode<Double> node13 = new TreeNode<Double>(13d, node11, node15);
		TreeNode<Double> node1 = new TreeNode<Double>(1d, null, null);
		TreeNode<Double> node7 = new TreeNode<Double>(7d, null, null);
		TreeNode<Double> node5 = new TreeNode<Double>(5d, node1, node7);
		TreeNode<Double> node10 = new TreeNode<Double>(10d, node5, node13);

		List<Double> branchSums = new ArrayList<Double>();
		getBranchSums(node10, 0, branchSums);
		System.out.println(branchSums);

		System.out.println(getBranchSumsIterative(node10));

	}

	// Recursive approach
	// Time = O(n)
	// Space = O(n)
	private static void getBranchSums(TreeNode<Double> tree, double sum, List<Double> branchSums) {

		if (tree != null) {

			sum += (double)tree.getValue();

			TreeNode<Double> nodeLeft = tree.getLeftChild();
			TreeNode<Double> nodeRight = tree.getRightChild();

			if (nodeLeft == null && nodeRight == null) {
				branchSums.add(sum);
			}
			getBranchSums(nodeLeft, sum, branchSums);
			getBranchSums(nodeRight, sum, branchSums);
		}
		return;

	}

	private static List<Double> getBranchSumsIterative(TreeNode<Double> tree) {

		double sum = 0d;
		List<Double> branchSums = new ArrayList<Double>();
		Stack<NodeSnapshot> nodesStack = new Stack<NodeSnapshot>();

		NodeSnapshot snapShot = new NodeSnapshot(tree, sum);

		while (snapShot != null) {

			if (snapShot.getNode().getLeftChild() != null && !snapShot.leftNodeScan) {
				snapShot.setLeftNodeScanned();
				nodesStack.push(snapShot);
				snapShot = new NodeSnapshot(snapShot.getNode().getLeftChild(),
						sum + (double) snapShot.getNode().getValue() + snapShot.getTotalAcummulated());

			} else if (snapShot.getNode().getRightChild() != null && !snapShot.rightNodeScan) {
				snapShot.setRightNodeScanned();
				nodesStack.push(snapShot);
				snapShot = new NodeSnapshot(snapShot.getNode().getRightChild(),
						sum + (double) snapShot.getNode().getValue() +  snapShot.getTotalAcummulated());
			}

			else if (!nodesStack.isEmpty()) {
				if (snapShot.getNode().getLeftChild() == null && snapShot.getNode().getRightChild() == null) {
					branchSums.add((double)snapShot.getNode().getValue() + snapShot.getTotalAcummulated());
				}
				snapShot = nodesStack.pop();
			} else {
				snapShot = null;
			}
		}
		return branchSums;
	}

	// Recursive approach
	// Time = O(n)
	// Space = O(n)
	private static List<Double> getBranchSumsIterative2(TreeNode<Double> tree) {

		double sum = 0d;
		List<Double> branchSums = new ArrayList<Double>();
		Stack<NodeSnapshot> nodesStack = new Stack<NodeSnapshot>();

		NodeSnapshot snapShot = null;

		while (tree != null) {

			sum += (double)tree.getValue();

			TreeNode<Double> nodeLeft = tree.getLeftChild();
			TreeNode<Double> nodeRight = tree.getRightChild();

			if (nodeLeft == null && nodeRight == null) {
				branchSums.add(sum);
				snapShot = nodesStack.pop();
				tree = snapShot.getNode();
				sum = snapShot.getTotalAcummulated();

				nodeLeft = tree.getLeftChild();
				nodeRight = tree.getRightChild();

			} else if (snapShot == null) {
				snapShot = new NodeSnapshot(tree, sum);
			}

			if (nodeLeft != null && !snapShot.isLeftNodeScan()) {
				snapShot.setLeftNodeScanned();
				nodesStack.push(snapShot);
				snapShot = null;
				tree = nodeLeft;
			} else if (nodeRight != null && !snapShot.isRightNodeScan()) {
				snapShot.setRightNodeScanned();
				nodesStack.push(snapShot);
				snapShot = null;
				tree = nodeRight;
			} else if (!nodesStack.empty()) {
				snapShot = nodesStack.pop();
				tree = snapShot.getNode();
				sum = snapShot.getTotalAcummulated() - (double)tree.getValue();
			} else {
				tree = null;
			}
		}
		return branchSums;
	}

	private static class NodeSnapshot {
		private TreeNode<Double> node;
		private double totalAcummulated;
		private boolean leftNodeScan;
		private boolean rightNodeScan;

		public NodeSnapshot(TreeNode<Double> node, double currentValue) {
			this.node = node;
			this.totalAcummulated = currentValue;
		}

		public TreeNode<Double> getNode() {
			return node;
		}

		public void setNode(TreeNode<Double> node) {
			this.node = node;
		}

		public double getTotalAcummulated() {
			return totalAcummulated;
		}

		public void setTotalAcummulated(double totalAcummulated) {
			this.totalAcummulated = totalAcummulated;
		}

		public boolean isLeftNodeScan() {
			return leftNodeScan;
		}

		public void setLeftNodeScanned() {
			this.leftNodeScan = true;
		}

		public boolean isRightNodeScan() {
			return rightNodeScan;
		}

		public void setRightNodeScanned() {
			this.rightNodeScan = true;
		}

	}

}
