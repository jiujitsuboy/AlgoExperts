package training.medium.arrays;

/**
 * Determine is an array is monotonic, which means that all there items are
 * sorted in a way, that every next item is greater or lower than the previous.
 * 
 * @author JoseAlejandro
 *
 */
public class MonotonicArray {

	public static void main(String[] args) {
		int[] array = new int[] { -1, -5, -10, -1100, -1100, -1101, -1102, -9001 }; // true
		int[] array2 = new int[] { -1, -5, -4, -1100, -1100, -1101, -1102, -9001 }; // false
		int[] array3 = new int[] { -1, 0, 5, 1100, 1100, 1101, 1102, 9001 }; // true
		int[] array4 = new int[] { -1, 0, 5, 1100, 1100, 1090, 1102, 9001 }; // false
		int[] array5 = new int[] {}; // true
		int[] array6 = new int[] { 1 }; // true
		int[] array7 = new int[] { 1, 1 }; // true

		System.out.println(isArrayMonotinic2(array));
		System.out.println(isArrayMonotinic2(array2));
		System.out.println(isArrayMonotinic2(array3));
		System.out.println(isArrayMonotinic2(array4));
		System.out.println(isArrayMonotinic2(array5));
		System.out.println(isArrayMonotinic2(array6));
		System.out.println(isArrayMonotinic2(array7));
	}

	/**
	 * Loop throw every element in the array comparing the N element to the N-1
	 * element to see if all of them tend to increase or decrease
	 * 
	 * Complexity: Time O(n) Space O(1)
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isArrayMonotinic(int[] array) {

		boolean isMonotonic = true;
		boolean isIncreasing = false;
		boolean isDecrincreasing = false;

		for (int pos = 1; pos < array.length; pos++) {
			if (array[pos - 1] < array[pos]) {
				if (isDecrincreasing) {
					isMonotonic = false;
					break;
				}
				isIncreasing = true;

			}

			else if (array[pos - 1] > array[pos]) {
				if (isIncreasing) {
					isMonotonic = false;
					break;
				}
				isDecrincreasing = true;

			}
		}

		return isMonotonic;
	}

	/**
	 * Loop throw every element in the array comparing the N element to the N-1
	 * element to see if all of them tend to increase or decrease
	 * 
	 * Complexity: Time O(n) Space O(1)
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isArrayMonotinic2(int[] array) {

		boolean isMonotonic = true;
		int direction = 0;

		if (array.length > 2) {
			direction = array[1] - array[0];

			for (int pos = 2; pos < array.length; pos++) {

				if (direction == 0) {
					direction = array[pos] - array[pos - 1];
				}

				if (isChangedDirection(direction, array[pos - 1], array[pos])) {
					isMonotonic = false;
					break;
				}

			}
		}
		return isMonotonic;
	}

	private static boolean isChangedDirection(int direction, int previousValue, int nextValue) {
		boolean changeDirecction = false;

		int difference = nextValue - previousValue;

		if (direction > 0) {
			changeDirecction = difference < 0;
		} else {
			changeDirecction = difference > 0;
		}
		return changeDirecction;
	}

}
