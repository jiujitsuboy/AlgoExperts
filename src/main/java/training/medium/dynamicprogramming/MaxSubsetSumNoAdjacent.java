package training.medium.dynamicprogramming;

/**
 * Calculates the max sum of the numbers no adjacent in the array
 * 
 * @author JoseAlejandro
 *
 */
public class MaxSubsetSumNoAdjacent {

	public static void main(String[] args) {

//		int[] array = new int[] {7, 10, 12, 7, 9, 14};
		int[] array = new int[] { 14, 9, 7, 12, 10, 7 };

		System.out.println(calculateMaxSum(array));

	}

	/**
	 * We use dynamic programming to solve this issue (basically we found solutions
	 * for smaller problems and build up from those to solve the biggest), So we
	 * start to find the max sum for first number in the array, which is it self,
	 * them we found the max number between array[0] and array[1] (the max, nor the
	 * sum, because they are adjacent, so we can´t sum them), and from 2 onwards, we
	 * use the following formula:
	 * 
	 * _ maxSum[i]= | | maxSum[i-1] | maxSum[i-2] + array[i] |_
	 * 
	 * So instead of manually find all the possible combinations of no adjacent
	 * numbers in the sub arrays we are building from length 1 to array length, we
	 * apply the above formula to see which sum is greater and pick that one.
	 * 
	 * Complexity: Time O(n) => because we traverse the whole array 
	 *             Space O(1) => we use only 3 variables which are consider constant regarding the input
	 * 
	 * 
	 * @param array
	 * @return
	 */
	public static int calculateMaxSum(int[] array) {
		int firstValue = 0;
		int secondValue = 0;
		int currentValue = 0;

		if (array != null) {

			if (array.length > 1) {
				firstValue = array[0];
				secondValue = Math.max(firstValue, array[1]);

				for (int pos = 2; pos < array.length; pos++) {
					currentValue = Math.max(secondValue, firstValue + array[pos]);
					firstValue = secondValue;
					secondValue = currentValue;

				}
			} else if (array.length == 1) {
				currentValue = array[0];
			}
		}
		return currentValue;
	}

	/**
	 * This solution is worst that the above regarding the space complexity because
	 * we are storing a whole new array of the pre solutions and as showed above, we
	 * only required the last two values relative to the current operation.
	 * Complexity: Time O(n) => because we traverse the whole array 
	 *             Space O(n) => we create a new array of length N
	 *             
	 * @param array
	 * @return
	 */
	public static int calculateMaxSum2(int[] array) {

		int maxSum = 0;

		if (array != null) {

			if (array.length > 1) {
				int[] solArray = new int[array.length];
				solArray[0] = array[0];
				solArray[1] = Math.max(solArray[0], array[1]);

				for (int pos = 2; pos < array.length; pos++) {
					solArray[pos] = Math.max(solArray[pos - 1], solArray[pos - 2] + array[pos]);
				}
				maxSum = solArray[solArray.length - 1];
			} else if (array.length == 1) {
				maxSum = array[0];
			}
		}
		return maxSum;
	}

}
