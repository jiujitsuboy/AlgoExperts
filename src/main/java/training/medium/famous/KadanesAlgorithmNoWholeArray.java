package training.medium.famous;

import java.util.HashMap;
import java.util.Map;

/**
 * Calculates the contiguous inner sub array (not considering the whole array as an subarray), which their sum represent the
 * largest number possible
 * 
 * @author JoseAlejandro
 *
 */
public class KadanesAlgorithmNoWholeArray {

	public static void main(String[] args) {
//		int[] array = new int[] { 3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4,5,6,9,8,8,8,7,9,10,11,11,23,-98,8,9};
//		int[] array = new int[] { 1, 2, 3, 4 };
		int[] array = new int[] { 3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4};

		long start, end;
		start = System.currentTimeMillis();
		System.out.println(obtainSubArrayMaxSumIterative(array));
		end = System.currentTimeMillis();
		System.out.println("Execution time: " + (end-start)/100d);
		
		start = System.currentTimeMillis();
		System.out.println(obtainSubArrayMaxSumRecursiveCache(array));
		end = System.currentTimeMillis();
		System.out.println("Execution time: " + (end-start)/100d);
		
		start = System.currentTimeMillis();
		System.out.println(obtainSubArrayMaxSumRecursive(array));
		end = System.currentTimeMillis();
		System.out.println("Execution time: " + (end-start)/100d);
	}

	/**
	 * Sum every possible combinations of sub arrays which has adjacent elements
	 * 
	 * 									1,2,3,4
	 *						    1,2,3	         	    2,3,4
	 *					1,2			2,3             2,3        3,4
	 *				 1      2    2       3       2	    3    3      4
	 * 
	 * 
	 * Complexity
	 * 
	 * Time O(2^n) => for every sub array processed, we traverse the array and
	 * launch two more calls to the recursive method (n + 2^n) = 2^n
	 * 
	 * Space O(n) => the length of the calls tree is equal to the array length -1
	 * 
	 * @param array
	 * @return
	 */
	public static int obtainSubArrayMaxSumRecursive(int[] array) {

		int start = 0;
		int end = array.length - 1;

		return obtainSubArrayMaxSumAux(array, start, end);
	}

	private static int obtainSubArrayMaxSumAux(int[] array, int start, int end) {

		int sum = 0;

		if (start >= 0 && start <= end && end < array.length) {
			if (start > 0 || end < array.length - 1) {
				sum = getArraySum(array, start, end);
			}
//			printArray(array, start, end, sum);
			int part1 = obtainSubArrayMaxSumAux(array, start, end - 1);
			int part2 = obtainSubArrayMaxSumAux(array, start + 1, end);
			sum = Math.max(sum, Math.max(part1, part2));
		}

		return sum;
	}

	/**
	 * By using caching for the values computed, we reduce the number of calls to
	 * the recursive method
	 * 
	 * 
	 * 									1,2,3,4
	 *						    1,2,3	         	    2,3,4
	 *					1,2			2,3                        3,4
	 *				 1      2            3                         4
	 * 
	 * 
	 * Complexity
	 * 
	 * Time O() Space O()
	 * 
	 * @param array
	 * @return
	 */
	public static int obtainSubArrayMaxSumRecursiveCache(int[] array) {

		int start = 0;
		int end = array.length - 1;
		Map<String, Integer> cache = new HashMap<String, Integer>();

		return obtainSubArrayMaxSumCacheAux(array, start, end, cache);
	}

	private static int obtainSubArrayMaxSumCacheAux(int[] array, int start, int end, Map<String, Integer> cache) {

		int sum = 0;

		if (start >= 0 && start <= end && end < array.length) {
			if (start > 0 || end < array.length - 1) {
				sum = getArraySum(array, start, end);
			}
//			printArray(array, start, end, sum);

			String key1 = String.format("%s%s", start, end - 1);
			String key2 = String.format("%s%s", start + 1, end);

			Integer part1 = cache.get(key1);
			Integer part2 = cache.get(key2);

			if (part1 == null) {
				part1 = obtainSubArrayMaxSumCacheAux(array, start, end - 1, cache);
				cache.put(key1, part1);

			}
			if (part2 == null) {
				part2 = obtainSubArrayMaxSumCacheAux(array, start + 1, end, cache);
				cache.put(key2, part2);
			}

			sum = Math.max(sum, Math.max(part1, part2));
		}

		return sum;
	}

	private static int getArraySum(int[] array, int start, int end) {
		int sum = 0;

		for (int index = start; index <= end; index++) {
			sum += array[index];
		}

		return sum;
	}

	private static void printArray(int[] array, int start, int end, int sum) {
		for (int index = start; index <= end; index++) {
			System.out.print(array[index]);
			if (index <= end - 1) {
				System.out.print(",");
			}
		}
		System.out.print(" = " + sum);
		System.out.println();
	}

	/**
	 * We traverse the array twice, one from left to right (0 to length-1) and one
	 * from right to left (length-1 to 1) in every step we compare the current value
	 * accumulated plus adding the next position, that way we only calculate the
	 * array sum play adding one number instead of traversing the whole sub array
	 * every time.
	 * 
	 * Complexity
	 * 
	 * Time O(n*n) => iterate the array 
	 * Space O(1) => we just store the accumulating value and the max value.
	 * 
	 * @param array
	 * @return
	 */
	public static int obtainSubArrayMaxSumIterative(int[] array) {
		int maxSum = Integer.MIN_VALUE;
		int[] sums = new int[array.length];

		for (int round = 0; round < array.length - 1; round++) {
			for (int index = 0; index < array.length - round; index++) {
				sums[index] += array[index + round];
				if (sums[index] > maxSum) {
					maxSum = sums[index];
				}
			}
		}

		return maxSum;
	}

}
