package training.medium.arrays;

import java.util.Arrays;

/**
 * Move to the very left all the occurrence of the target value in the array
 * 
 * @author JoseAlejandro
 *
 */
public class MoveElementToEnd {

	public static void main(String[] args) {
		int[] array = new int[] { 2, 1, 2, 2, 2, 3, 4, 2 };

		System.out.println(Arrays.toString(array));
		moveToEndTarget(array, 2);
		System.out.println(Arrays.toString(array));
	}

	/**
	 * Using two pointer, we scan from left to right seeking the target number, every coincidence, will be 
	 * swapped using the second pointer, which is going to be located on the most right location which not
	 * contains the target number.
	 * 
	 * Complexity: Time 0(n) => we transverse the array once. If we sort the array the complexity goes to 0(nlog(n))
	 *             Space O(1)
	 * @param array
	 * @param target
	 */
	public static void moveToEndTarget(int[] array, int target) {

		int seekingPointer = 0;
		int availSlotPointer = array.length - 1;

		availSlotPointer = findNextAvailableSlot(seekingPointer, availSlotPointer, target, array);

		while (seekingPointer < availSlotPointer) {
			if (array[seekingPointer] == target) {
				swap(seekingPointer, availSlotPointer, array);
				availSlotPointer =findNextAvailableSlot(seekingPointer, availSlotPointer, target, array);
			}
			seekingPointer++;
		}

	}

	private static void swap(int num1, int num2, int[] array) {
		int temp = array[num1];
		array[num1] = array[num2];
		array[num2] = temp;
	}

	private static int findNextAvailableSlot(int seekingPointer, int currentSlot, int target, int[] array) {

		while (array[currentSlot] == target && currentSlot > seekingPointer) {
			currentSlot--;
		}

		return currentSlot;
	}

}
