package training.hard.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Calculate all the four numbers combinations that sum the target value
 * 
 * @author JoseAlejandro
 *
 */
public class FourNumberSum {

	public static void main(String[] args) {
		int[] numbers = new int[] { 7, 6, 4, -1, 1, 2 };
		int target = 16;
		List<Integer[]> cuadruplets = findAllFourNumbersSumBruteForce(numbers, target);

		for (Integer[] cuadruplet : cuadruplets) {
			System.out.println(Arrays.toString(cuadruplet));
		}
	}

	/**
	 * four loops for comparing all the combinations of 4 numbers to find the sum
	 * equal the target Complexity: Time O(n^4) Space O(n)
	 * @param array
	 * @param target
	 * @return
	 */
	public static List<Integer[]> findAllFourNumbersSumBruteForce(int[] array, int target) {

		List<Integer[]> cuadruplets = new ArrayList<>();

		for (int num1 = 0; num1 < array.length - 2; num1++) {
			for (int num2 = num1 + 1; num2 < array.length - 1; num2++) {
				for (int num3 = num2 + 1; num3 < array.length; num3++) {
					for (int num4 = num3 + 1; num4 < array.length; num4++) {
//					System.out.println(String.format("[%d,%d,%d]", array[num1], array[num2], array[num3]));
						if (array[num1] + array[num2] + array[num3] + +array[num4] == target) {
							cuadruplets.add(new Integer[] { array[num1], array[num2], array[num3], array[num4] });
						}
					}
				}
			}
		}
		return cuadruplets;
	}

	/**
	 * We use 3 loops for:
	 * 
	 * loop 1 = traverse the array from position 1 to position n-1. for each
	 * position we run the other two loops
	 * 
	 * loop2 = goes from current + 1 to n. This one sum the number from loop 1 to
	 * every number after it and gets the difference of that sum - target, so we can
	 * look for that difference in the Map. if the difference exists, we found a
	 * valid cuadruplet and we store it in the cuadruplet list.
	 * 
	 * loop3 = goes from 0 to the number before the number from loop 1. we use this
	 * loop to generate all the pairs combinations between the loop 1 number and
	 * every number before it. we sum both numbers and store the sum and the numbers
	 * in the Map. We did this pairs storage here as a way to avoid to store
	 * duplicate pairs (what would happen if we store the pairs during the
	 * comparison made by the loop 2)
	 * 
	 * 
	 * Complexity
	 * 
	 * Time O(n^2)=> we use one loop to traverse the array, and two order loops per
	 * every iteration of the first loop. So we have N*(2N)=> N^2. In the worst case
	 * scenario, were we have lots of numbers that yield the same sum
	 * (-4,4),(-3,3),(-2,2)......, we could end with a list of pairs in the Map as
	 * long as half the original number array. So in this case we should traverse
	 * that list of pair, adding another N to the complexity, ending with O(N^3).
	 * 
	 * Space O(n^2)=> we create a pair of all the possible elements from the
	 * original array, so with end with n^2 combinations in the Map.
	 * 
	 * @param array
	 * @param target
	 * @return
	 */
	private static List<Integer[]> findAllFourNumbersSum(int[] array, int target) {
		final int CUADRUPLET_ARRAY_LENGTH = 4;
		List<Integer[]> cuadruplets = new ArrayList<>();
		Map<Integer, List<Integer[]>> pairs = new HashMap<>();

		for (int current = 1; current < array.length - 1; current++) {
			// Check all the number after the current one, with the current one
			for (int after = current + 1; after < array.length; after++) {

				int difference = target - (array[current] + array[after]);
				List<Integer[]> samePairs = pairs.get(difference);

				// If the difference (which is a pair) is already stored in the map
				if (samePairs != null) {

					// for each of the pairs which it sum yield the difference
					for (Integer[] pair : samePairs) {
						Integer[] cuadruplet = Arrays.copyOf(pair, CUADRUPLET_ARRAY_LENGTH);

						cuadruplet[CUADRUPLET_ARRAY_LENGTH - 2] = array[current];
						cuadruplet[CUADRUPLET_ARRAY_LENGTH - 1] = array[after];

						cuadruplets.add(cuadruplet);
					}

				}
			}

			// Check all the number before the current one, with the current one
			for (int before = 0; before < current; before++) {
				int sum = array[current] + array[before];
				List<Integer[]> samePairs = pairs.get(sum);

				if (samePairs == null) {
					samePairs = new ArrayList<>();
					pairs.put(sum, samePairs);
				}

				samePairs.add(new Integer[] { array[before], array[current] });
			}
		}

		return cuadruplets;
	}

}
