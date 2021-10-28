package training.medium.binarysearchtrees;

import training.util.classes.BST;

/**
 * BTS implementation. 
 * Operations available:
 * 						insert node
 * 						search node
 * 						delete node
 * @author JoseAlejandro
 *
 */
public class BSTConstruction {

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
		System.out.println("");
		
		bst.deleteNode(10d);
		bst.deleteNode(15d);

		System.out.println(bst);

	}	
}
