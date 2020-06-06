package training.medium.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Calculate all the three numbers combinations that sum the target value
 * @author JoseAlejandro
 *
 */
public class ThreeNumberSum {

	public static void main(String[] args) {
		int[] nums = new int[] { -6, -1, -2, 2, 3, 4, 5, 6 };
		List<Integer[]> triplets = findAllThreeNumber2(nums, 0);
		triplets.stream().forEach(arr -> System.out.println(Arrays.toString(arr)));
	}

	/**
	 * three loops for comparing all the combinations of 3 numbers to find the sum
	 * equal the target Complexity: Time O(n^3) Space O(n)
	 * 
	 * @param array
	 * @param target
	 * @return
	 */
	public static List<Integer[]> findAllThreeNumber(int[] array, int target) {

		List<Integer[]> triplet = new ArrayList<>();

		for (int num1 = 0; num1 < array.length - 2; num1++) {
			for (int num2 = num1 + 1; num2 < array.length - 1; num2++) {
				for (int num3 = num2 + 1; num3 < array.length; num3++) {
//					System.out.println(String.format("[%d,%d,%d]", array[num1], array[num2], array[num3]));
					if (array[num1] + array[num2] + array[num3] == target) {
						triplet.add(new Integer[] { array[num1], array[num2], array[num3] });
					}
				}
			}
		}
		return triplet;
	}

	/**
	 * Use 3 pointer, one starting in the most left corner and two others, which come across together depending
	 * if the result of the sum is greater or lesser that the targer
	 * Complexity: Time O(n^2)
	 *             Space O(n)
	 * @param array
	 * @param target
	 * @return
	 */
	public static List<Integer[]> findAllThreeNumber2(int[] array, int target) {

		List<Integer[]> triplet = new ArrayList<>();

		int num1 = 0;
		int num2 = num1 + 1;
		int num3 = array.length - 1;
		int sum = 0;
		
		Arrays.sort(array); // O(n)
		
		while (num1 < array.length - 2) {
			
			sum = array[num1] + array[num2] + array[num3]; 
			if ( sum == target) {
				triplet.add(new Integer[] { array[num1], array[num2], array[num3] });
				num2++;
				num3--;
			}
			
			if (sum<target) {
				num2++;	
			}
			else if (sum>target) {
				num3--;
			}
			
			if (num2>=num3) {
				num1++;
				num2 = num1+1;
				num3 = array.length-1;
			}
		
		}

		return triplet;
	}

}
