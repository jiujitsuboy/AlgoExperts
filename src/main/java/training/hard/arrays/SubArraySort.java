package training.hard.arrays;

import java.util.Arrays;

/**
 * Find the sub array (starting and ending indexes in the whole array) that need
 * to be sorted, that makes the whole array become sorted
 * 
 * @author JoseAlejandro
 *
 */
public class SubArraySort {

	public static void main(String[] args) {
//		int[] array = new int[] { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
		int[] array = new int[] { 1, 2, 4, 7, 10, 1, 12, 16, 18, 19 };
		int[] subarray = findIndexSubArray(array);
		System.out.println(Arrays.toString(subarray));
	}

	/**
	 * We iterate over the full array, checking if the current number is in order
	 * (we compare to the previous and next value),if the value is out of order, we
	 * keep track of the minimum and maximum value of this subset of unordered
	 * numbers. At the end we take the minimum and maximum values and find their
	 * respective position in the array where they become in order, and those two
	 * positions resemble the sub array indexes that represent the sub array to sort
	 * to make the whole array sorted.
	 * 
	 * Complexity
	 *
	 * Time O(n) => we just traverse the array once, and then traverse part the
	 * array from the beginning and then part from the end, leading to a 3N, which
	 * become N.
	 * 
	 * Space O(1)=> we only store the two indexes, so the storage don't depends on
	 * the size of the input.
	 * 
	 * @param array
	 * @return
	 */
	public static int[] findIndexSubArray(int[] array) {

		int[] indexes = new int[] { -1, -1 };
		int minValueOutOfOrder = Integer.MAX_VALUE;
		int maxValueOutOfOrder = Integer.MIN_VALUE;

		for (int index = 0; index < array.length; index++) {
			if (isOutOfOrder(index, array)) {
				minValueOutOfOrder = Math.min(minValueOutOfOrder, array[index]);
				maxValueOutOfOrder = Math.max(maxValueOutOfOrder, array[index]);
			}
		}

		if (minValueOutOfOrder != Integer.MAX_VALUE) {

			int lowerIndex = 0;
			int higherIndex = array.length - 1;

			while (lowerIndex < array.length && minValueOutOfOrder > array[lowerIndex]) {
				lowerIndex++;
			}

			while (higherIndex > -1 && maxValueOutOfOrder < array[higherIndex]) {
				higherIndex--;
			}

			indexes[0] = lowerIndex;
			indexes[1] = higherIndex;
		}

		return indexes;
	}

	/**
	 * Check if the number at the specified index is in order regarding the previous
	 * and next numbers in the array
	 * 
	 * @param index element in the array to check
	 * @param array all elements
	 * @return <value>boolean</value>
	 */
	private static boolean isOutOfOrder(int index, int[] array) {

		boolean isInOrder = false;

		if (index > 0 && index < array.length - 1) {

			isInOrder = array[index] > array[index + 1] || array[index - 1] > array[index];
		}

		else if (index == 0) {
			isInOrder = array[index] > array[index + 1];
		}

		else if (index == array.length - 1) {
			isInOrder = array[index - 1] > array[index];
		}

		return isInOrder;
	}

}
