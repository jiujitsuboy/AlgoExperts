package training.medium.famous;

/**
 * Calculates the contiguous inner sub array (considering the whole array as an
 * sub array), which their sum represent the largest number possible
 * 
 * @author JoseAlejandro
 *
 */
public class KadanesAlgorithm {

	public static void main(String[] args) {
//		int[] array = new int[] { 3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4};
		int[] array = new int[] { -1, 2, 3, 4 };

		long start, end;
		start = System.currentTimeMillis();
		System.out.println(obtainSubArrayMaxSum(array));
		end = System.currentTimeMillis();
		System.out.println("Execution time: " + (end - start) / 100d);

	}

	/**
	 * we just sum elements from left to  right starting with just the first element. So the max value will be
	 * summing all the values of the array, unless they are negative values present, so in that case when we
	 * traverse the array and get the accumulation, they can be cases that sum one negative value, will cancelate
	 * the whole accumulation, so that implies that we can skip that portion of the array. That's why we
	 * evaluate the max value between the accumulation and the current value.
	 * 
	 * Complexity
	 * 
	 * Time  O(n) => we just traverse the array only once
	 * Space O(1)=> we only store the accumulated value and the maxSum
	 * 
	 * 
	 * @param array
	 * @return
	 */
	public static int obtainSubArrayMaxSum(int[] array) {
		int maxSum = 0;
		int maxSumSoFar = 0;

		if (array.length > 0) {
			maxSum = array[0];
			maxSumSoFar = maxSum;

			for (int index = 1; index < array.length; index++) {
				maxSumSoFar = Math.max(maxSumSoFar + array[index], array[index]);
				if (maxSumSoFar > maxSum) {
					maxSum = maxSumSoFar;
				}
			}
		}

		return maxSum;

	}

}
