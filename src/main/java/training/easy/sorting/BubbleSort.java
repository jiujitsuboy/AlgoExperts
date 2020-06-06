package training.easy.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;

/***
 * Sorting algorithm, which pass N times on the collection, comparing the N-n to
 * N-n+1 element and swapping they if N-n is lower than N-n+1. So on any loop
 * iteration, the mayor valur N is left in the upper index of the array.
 * 
 * @author JoseAlejandro
 *
 */
public class BubbleSort {

	public static void main(String[] args) {

		Integer[] arr = new Integer[] { 10, 5, 23, 6, 2, 1, 0 };
//		String[] arr = new String[] { "10", "5", "23", "6", "2", "1","0"};
//		sort(arr, (obj1, obj2)->obj1.compareTo(obj2));
		sort2(arr, (obj1, obj2) -> obj1.compareTo(obj2));
		System.out.println(Arrays.toString(arr));
	}

	/***
	 * Sorting method (implemented with a While Loop and a inner For Loop),which
	 * complexity is: O(n^2) Time 
	 * 				  O(1) Space
	 * 
	 * @param <T>   type of the elements of the array
	 * @param array array to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		boolean isSorted = false;
		int offset = 1;
		T temp;

		while (!isSorted) {

			isSorted = true;

			for (int pos = 0; pos < (array.length - offset); pos++) {
				if (array[pos].compareTo(array[pos + 1]) > 0) {
					isSorted = false;
					temp = array[pos];
					array[pos] = array[pos + 1];
					array[pos + 1] = temp;
				}
			}
			offset++;
		}
	}

	/***
	 * Sorting method (implemented with a two For Loops),which complexity is: O(n^2)
	 * Time O(1) Space
	 * 
	 * @param <T>   type of the elements of the array
	 * @param array array to sort
	 */
	public static <T extends Comparable<T>> void sort2(T[] array) {
		boolean isSorted = false;
		T temp;

		for (int offset = 1; offset < array.length && !isSorted; offset++) {

			isSorted = true;

			for (int pos = 0; pos < (array.length - offset); pos++) {
				if (array[pos].compareTo(array[pos + 1]) > 0) {
					isSorted = false;
					temp = array[pos];
					array[pos] = array[pos + 1];
					array[pos + 1] = temp;
				}
			}
		}
	}

	public static <T extends Comparable<T>> void sort(T[] array, Comparator<T> comp) {
		boolean isSorted = false;
		int offset = 1;
		T temp;

		while (!isSorted) {

			isSorted = true;

			for (int pos = 0; pos < (array.length - offset); pos++) {
				if (comp.compare(array[pos], array[pos + 1]) > 0) {
					isSorted = false;
					temp = array[pos];
					array[pos] = array[pos + 1];
					array[pos + 1] = temp;
				}
			}
			offset++;
		}
	}

	public static <T extends Comparable<T>> void sort2(T[] array, BiFunction<T, T, Integer> comp) {
		boolean isSorted = false;
		int offset = 1;
		T temp;

		while (!isSorted) {

			isSorted = true;

			for (int pos = 0; pos < (array.length - offset); pos++) {
				if (comp.apply(array[pos], array[pos + 1]) > 0) {
					isSorted = false;
					temp = array[pos];
					array[pos] = array[pos + 1];
					array[pos + 1] = temp;
				}
			}
			offset++;
		}
	}

}
