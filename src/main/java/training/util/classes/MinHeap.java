package training.util.classes;

import java.util.List;

/**
 * Min Heap is like a priority queue, where using an array as a underlying
 * structure, we keep the smallest value in the top. The heap logically looks
 * like a tree where every node is lesser that his children. But the whole tree
 * is not sorted or ordered, just every node is lesser that his children.
 * 
 * @author JoseAlejandro
 *
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> {
	private List<T> heap;

	public MinHeap(List<T> heap) {
		this.heap = heap;
		buildHeap();
	}

	/**
	 * taking a list, and converted into a min heap by applying siftDown at the last parent and then
	 * repeating the process to every element behind the last parent of the list. We use SiftDown, instead
	 * SiftUp, because SiftDown offer better performance because the majority of the elements are at the
	 * bottom of the tree, and those take O(1) in swapping with there parents, in contrast to doing this
	 * using SiftUp which start at the root and need to take the value down all the way, so the advantage 
	 * is that because the majority of nodes are at the bottom, and the operation at that level takes O(1) time
	 * the complexity convey to O(n) instead O(n log(n)), which is the time it takes with SiftUp (N nodes per 
	 * log n, which is the time it takes to do a shiftUp or shiftDown operation )
	 * 
	 * Complexity 
	 * 
	 * Time O(n) => because we use SiftDown instead SiftUp
	 * 
	 * space O(1) => no extra store used
	 * 
	 * 
	 */
	public void buildHeap() {
		if (heap.size() > 0) {
			int indexParent = getParentIndex(heap.size() - 1);
			for (int index = indexParent; index >= 0; index--) {
				siftDown(index);
			}
		}
	}

	/**
	 * take the element at the specified index and goes up, using his parent until
	 * he locate the spot where this element is lesser that they respective
	 * ancestors.
	 * 
	 * Complexity
	 * 
	 * Time: O(log(n))=> in very step we only traverse half of the nodes (taking his
	 * parents)
	 * 
	 * Space 0(1)=> no additional structure used related to the input
	 * 
	 * 
	 * @param currIndex
	 */
	public void siftUp(int currIndex) {
		int indexParent = getParentIndex(currIndex);

		while (isIndexInRange(indexParent) && heap.get(currIndex).compareTo(heap.get(indexParent)) < 0) {
			swap(currIndex, indexParent);
			currIndex = indexParent;
			indexParent = getParentIndex(currIndex);
		}
	}

	/**
	 * take the element at the specified index and compare it with his lesser child
	 * and if his child is lesser than him, we swap it, and continue doing this
	 * until we can't find a lesser child or reach the leaf of the tree.
	 * 
	 * Time: O(log(n))=> in every step we only traverse half of the nodes (taking
	 * lesser child)
	 * 
	 * 
	 * Space 0(1)=> no additional structure used related to the input
	 * 
	 * 
	 * @param currIndex
	 */
	public void siftDown(int currIndex) {

		int indexFirstChild = getFirstChildIndex(currIndex);
		int indexSecondChild = getSecondChildIndex(currIndex);
		int indexSwap = -1;

		while (isIndexInRange(indexFirstChild)) {

			if (isIndexInRange(indexSecondChild) && heap.get(indexSecondChild).compareTo(heap.get(indexFirstChild)) < 0) {
				indexSwap = indexSecondChild;
			} else {
				indexSwap = indexFirstChild;
			}

			if (heap.get(currIndex).compareTo(heap.get(indexSwap)) > 0) {
				swap(currIndex, indexSwap);
				currIndex = indexSwap;
				indexFirstChild = getFirstChildIndex(currIndex);
				indexSecondChild = getSecondChildIndex(currIndex);
			} else {
				return;
			}

		}

	}

	/**
	 * Retrieve without removing the top element of the heap
	 * 
	 * Complexity
	 * 
	 * Time O(1) Space O(1)
	 * 
	 * @return
	 */
	public T peek() {
		T min = null;
		if (heap.size() > 0) {
			min = heap.get(0);
		}
		return min;
	}

	/**
	 * Remove the top element of the heap, it first swap it with the last element,
	 * then SiftDown the new top element until it finds his correct position.
	 * 
	 * Time O(log(n)) Space O(1)
	 * 
	 * @return
	 */
	public T remove() {
		T min = peek();
		if (min != null) {
			int firstIndex = 0;
			int lastIndex = heap.size() - 1;
			
			swap(firstIndex, lastIndex);
			heap.remove(lastIndex);
			siftDown(firstIndex);
			
		}
		return min;
	}

	/**
	 * 
	 * Insert the element at the end of the array and then locate it in the correct
	 * position using SiftUp
	 * 
	 * Time O(log(n)) Space O(1)
	 * 
	 * @param value
	 */
	public void insert(T value) {
		heap.add(value);
		siftUp(heap.size() - 1);
	}

	/**
	 * Interchange the position of two elements in the heap
	 * 
	 * Time O(1) Space O(1)
	 * 
	 * @param index1
	 * @param index2
	 */
	public void swap(int index1, int index2) {

		if (isIndexInRange(index1) && isIndexInRange(index2)) {
			T temp = heap.get(index1);
			heap.set(index1, heap.get(index2));
			heap.set(index2, temp);
		}

	}

	private boolean isIndexInRange(int index) {
		return index >= 0 && index < heap.size();
	}

	private int getParentIndex(int index) {
		return (index - 1) / 2;
	}

	private int getFirstChildIndex(int index) {
		return (index * 2) + 1;
	}

	private int getSecondChildIndex(int index) {
		return (index * 2) + 2;
	}

	@Override
	public String toString() {
		return heap.toString();
	}
}