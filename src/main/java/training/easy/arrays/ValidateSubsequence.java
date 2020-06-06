package training.easy.arrays;

/**
 * Search if the sub array exists with the array, conserving their relative
 * position.
 * 
 * @author JoseAlejandro
 *
 */
public class ValidateSubsequence {

	public static void main(String[] args) {
		int[] array = new int[] { 5, 1, 22, 25, 6, -1, 8, 10, 11, 12 };
		int[] subArray = new int[] { 1, 6, -1, 10 ,12};

		System.out.println(checkIfIsSubArray(array, subArray));
	}

	/**
	 * We iterate over the big array searching the first element of the sub array, when we find a coincidence
	 * we switch to search the next element in the sub array from the position where the previous match occurs.
	 * 
	 * Complexity
	 * 
	 * Time O(n) => we traverse the whole or partially the big array, and because the length of the sub array
	 * is always equal or less than the original array, and also because we only use one circle of iteration, the
	 * sub array traverse is insignificant 
	 * 
	 *  Space O(1)=> we don't use any additional data structure based on the input of the method
	 *  
	 *  
	 * @param array
	 * @param subArray
	 * @return
	 */
	public static boolean checkIfIsSubArray(int[] array, int[] subArray) {

		int subArrayIndex = 0;
		for (int arrayIndex = 0; arrayIndex < array.length && subArrayIndex < subArray.length; arrayIndex++) {

			if (subArray[subArrayIndex] == array[arrayIndex]) {
				subArrayIndex++;
			}
		}

		return subArrayIndex == subArray.length;
	}

}
