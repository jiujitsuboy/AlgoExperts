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
	
	public TreeNode<T> getRoot(){
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
	 * Find the node with the minimum greater value
	 * 
	 * @param refNode
	 * @return
	 */
	public TreeNode<T> findSuccessor(TreeNode<T> refNode) {

		TreeNode<T> current = null;
		TreeNode<T> succesor = null;

		if (refNode != null) {
			current = refNode.getRightChild();
			while (current != null) {
				succesor = current;
				current = current.getLeftChild();
			}
			if(current==null) {
				TreeNode<T> parent = refNode.getParent();
				while(parent!= null && parent.getRightChild() == current) {
					current = parent;
					parent = parent.getParent();
				}
				succesor = parent;
			}
		}

		return succesor;
	}

	/**
	 * Delete a node from the BST.
	 * 
	 * @param value
	 */
	public void deleteNode(T value) {

		TreeNode<T> current = searchNode(value);

		if (current != null) {

			if (current.getLeftChild() != null && current.getRightChild() != null) {
				TreeNode<T> succesor = findSuccessor(current);
				if (succesor != null) {

					swap(succesor, current);
					current = succesor;

				}
			}
			removeNodeWithOneorNoneChilds(current);
		}

	}

	@Override
	public String toString() {

		return genTreeRepresentation(root);
	}

	/**
	 *  Complexity space O(log(N))
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
		node1.setValue(node2.getValue());
		node2.setValue(tempValue);
	}

	private void deleteNodeWithRighChild(TreeNode<T> node) {
		TreeNode<T> parent = node.getParent();

		if (parent != null && node != null) {
			if (parent.getLeftChild() == node) {
				parent.setLeftChild(node.getRightChild());
			} else {
				parent.setRightChild(node.getRightChild());

			}
		}
	}

	private void deleteNodeWithLeftChild(TreeNode<T> node) {
		TreeNode<T> parent = node.getParent();

		if (parent != null && node != null) {
			if (parent.getLeftChild() == node) {
				parent.setLeftChild(node.getRightChild());
			} else {
				parent.setRightChild(node.getRightChild());

			}
		}
	}

	private void deleteLeafNode(TreeNode<T> node) {
		TreeNode<T> parent = node.getParent();

		if (parent != null) {
			if (parent.getLeftChild() == node) {
				parent.setLeftChild(null);
			} else {
				parent.setRightChild(null);

			}
		}
	}

}
