package training.easy.sorting;

import java.util.Arrays;

/**
 * Sorting algorithm, which pass N times on the collection. In any iteration,
 * the algorithm select the smallest value and swap it to the next greater value
 * form left to right
 *
 * @author JoseAlejandro
 *
 */
public class SelectionSort {

	public static void main(String args[]) {
		Integer[] arr = new Integer[] { 0, 1, 2, 5, 6, 10, 23 };
//		Integer[] arr = new Integer[] { 10, 5, 23, 6, 2, 1, 0 };
//		String[] arr = new String[] { "10", "5", "23", "6", "2", "1","0"};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Sorting method (implemented with a While Loop and a inner For Loop),which
	 * complexity is: O(n^2) Time O(1) Space
	 * 
	 * @param <T>   type of the elements of the array
	 * @param array array to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {

		int lowerValueIndex;

		for (int nextAvailableIndex = 0; nextAvailableIndex < array.length - 1; nextAvailableIndex++) {
			lowerValueIndex = nextAvailableIndex;

			for (int pos = nextAvailableIndex + 1; pos < array.length; pos++) {
				if (array[pos].compareTo(array[lowerValueIndex]) < 0) {
					lowerValueIndex = pos;
				}
			}
			if (lowerValueIndex <= 0) {
				break;
			} else {
				swap(nextAvailableIndex, lowerValueIndex, array);
			}
		}
	}

	private static <T> void swap(int minorIndex, int mayorIndex, T[] array) {
		T temp = array[minorIndex];
		array[minorIndex] = array[mayorIndex];
		array[mayorIndex] = temp;
	}

}
