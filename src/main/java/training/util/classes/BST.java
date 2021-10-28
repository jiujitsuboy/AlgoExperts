package training.util.classes;

/**
 * Binary Search Tree
 * insert, delete and search
 * Complexity: O(log(n)
 * Space: iterative => O(1)
 * 
 * @author JoseAlejandro
 *
 * @param <T>
 */
public class BST<T extends Comparable<T>> {

	private TreeNode<T> root;

	public TreeNode<T> getRoot() {
		return root;
	}

	public void InsertNode(T value) {
		TreeNode<T> parent = findPotentialParentInsertionNode(value);
		TreeNode<T> newNode = new TreeNode<T>(value, null, null);

		if (parent == null) {
			root = newNode;
		} else if (value.compareTo(parent.getValue()) < 0) {
			parent.setLeftChild(newNode);
			newNode.setParent(parent);
		} else if (value.compareTo(parent.getValue()) > 0) {
			parent.setRightChild(newNode);
			newNode.setParent(parent);
		}
	}

	public TreeNode<T> searchNode(T value) {

		TreeNode<T> current = root;

		while (current != null) {
			if (value.compareTo(current.getValue()) < 0) {
				current = current.getLeftChild();
			} else if (value.compareTo(current.getValue()) > 0) {
				current = current.getRightChild();
			} else {
				break;
			}
		}
		return current;
	}

	public TreeNode<T> findPotentialParentInsertionNode(T value) {

		TreeNode<T> current = root;
		TreeNode<T> parent = null;

		while (current != null) {
			parent = current;
			if (value.compareTo(current.getValue()) < 0) {
				current = current.getLeftChild();
			} else if (value.compareTo(current.getValue()) > 0) {
				current = current.getRightChild();
			} else {
				break;
			}
		}

		return parent;
	}

	/**
	 * Find the node with the minimum greater value. There are two possible cases:
	 * 
	 * 1 - the refNode has a right child. We take the (find the smallest value,
	 * which going to the left child until we reach null) of this right child.
	 * 
	 * 2 - the refNode has no right child. we go up on the tree using his parent and
	 * go up until we found a node that is the left node of it parent. That parent
	 * is the successor
	 * 
	 * @param refNode node which we want to find it successor
	 * @return successor node
	 */
	public TreeNode<T> findSuccessor(TreeNode<T> refNode) {

		TreeNode<T> current = null;
		TreeNode<T> succesor = null;

		if (refNode != null) {
			current = refNode.getRightChild();
			// case 1 - refNode has a right Child
			if (current != null) {
				while (current != null) {
					succesor = current;
					current = current.getLeftChild();
				}
			}
			// case 2 - refNode has not a right child
			else {
				TreeNode<T> parent = refNode.getParent();
				while (parent != null && parent.getRightChild() == current) {
					current = parent;
					parent = parent.getParent();
				}
				succesor = parent;
			}
		}

		return succesor;
	}

	/**
	 * Delete a node from the BST. There are three cases:
	 * 
	 * 1- When the node is a leaf node. In this case just remove it.
	 * 
	 * 2- When the node has one child. we just remove the node and link the child's
	 * node with the node's parent
	 * 
	 * 3- When the node has two children: We calculate the successor of the node,
	 * swap the node with the successor and apply to the swapped node case 1 or case
	 * 2
	 * 
	 * @param value
	 */
	public void deleteNode(T value) {

		TreeNode<T> current = searchNode(value);

		if (current != null) {

			//Case 3
			if (current.getLeftChild() != null && current.getRightChild() != null) {
				TreeNode<T> succesor = findSuccessor(current);
				if (succesor != null) {

					swap(succesor, current);
					current = succesor;

				}
			}
			//Case 1 or Case 2
			removeNodeWithOneorNoneChilds(current);
		}

	}

	@Override
	public String toString() {

		return genTreeRepresentation(root);
	}

	/**
	 * Complexity space O(log(N))
	 * 
	 * @param current
	 * @return
	 */
	private String genTreeRepresentation(TreeNode<T> current) {

		if (current == null) {

			return "";
		}

		return current.toString().concat("\n").concat(genTreeRepresentation(current.getLeftChild()))
				.concat(genTreeRepresentation(current.getRightChild()));

	}

	/**
	 * Remove a node from the BST depending if the node is a leaf node or if it has
	 * only one child
	 * 
	 * @param current
	 */
	private void removeNodeWithOneorNoneChilds(TreeNode<T> current) {

		if (current != null) {

			// leaf node
			if (current.isLeafNode()) {
				deleteLeafNode(current);
			}
			// node with a left child
			else if (current.getLeftChild() != null && current.getRightChild() == null) {
				deleteNodeWithLeftChild(current);
			}
			// node with a right child
			else if (current.getRightChild() != null && current.getLeftChild() == null) {
				deleteNodeWithRighChild(current);
			}
		}

	}

	private void swap(TreeNode<T> node1, TreeNode<T> node2) {

		T tempValue = node1.getValue();
		TreeNode<T> tempParent = node1.getParent();

		node1.setValue(node2.getValue());
		node1.setParent(node2.getParent());
		node2.setValue(tempValue);
		node2.setParent(tempParent);
	}

	private void deleteNodeWithRighChild(TreeNode<T> node) {
		TreeNode<T> parent = (node != null) ? node.getParent() : null;

		if (parent != null) {
			if (parent.getLeftChild() == node) {
				parent.setLeftChild(node.getRightChild());
			} else {
				parent.setRightChild(node.getRightChild());

			}
		}
	}

	private void deleteNodeWithLeftChild(TreeNode<T> node) {
		TreeNode<T> parent = (node != null) ? node.getParent() : null;

		if (parent != null) {
			if (parent.getLeftChild() == node) {
				parent.setLeftChild(node.getLeftChild());
			} else {
				parent.setRightChild(node.getLeftChild());

			}
		}
	}

	private void deleteLeafNode(TreeNode<T> node) {
		TreeNode<T> parent = (node != null) ? node.getParent() : null;

		if (parent != null) {
			if (parent.getLeftChild() == node) {
				parent.setLeftChild(null);
			} else {
				parent.setRightChild(null);

			}
		}
	}

}
