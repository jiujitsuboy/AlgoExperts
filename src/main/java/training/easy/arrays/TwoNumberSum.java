package training.easy.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Find two numbers within the array that there sum is equal to the target value
 * @author JoseAlejandro
 *
 */
public class TwoNumberSum {

	public static void main(String[] args) {
		int[] numbers = new int[] { 1, -5, 8, -9, 3, 5, -3, 2, -20 };
		int target = -4;
		int[] result1 = findTwoNumbers1(numbers, target);
		System.out.println(String.format("%d + %d = %d", result1[0], result1[1], target));

		int[] result2 = findTwoNumbers2(numbers, target);
		System.out.println(String.format("%d + %d = %d", result2[0], result2[1], target));
		
		
		int[] result3 = findTwoNumbers3(numbers, target);
		System.out.println(String.format("%d + %d = %d", result3[0], result3[1], target));

	}

	// 0(n^2) time complexity
	// O(1)
	private static int[] findTwoNumbers1(int[] numbers, int target) {
		int[] result = new int[2];
		
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i+1; j < numbers.length; j++) {
				if ((numbers[i] + numbers[j]) == target) {
					result[0] = numbers[i];
					result[1] = numbers[j];
					i=numbers.length;
					break;
				}
			}
		}
		return result;
	}

	// 0(n) time complexity
	// O(n)
	private static int[] findTwoNumbers2(int[] numbers, int target) {
		int[] result = new int[2];
		Map<Integer, Integer> numberStore = new HashMap<Integer, Integer>();

		for (int i = 0; i < numbers.length; i++) {
			numberStore.put(numbers[i], numbers[i]);
		}

		int equation = 0;
		for (int i = 0; i < numbers.length; i++) {
			equation = target - numbers[i];
			if (numberStore.containsValue(equation) && numberStore.get(equation)!=numbers[i]) {
				result[0] = numbers[i];
				result[1] = numberStore.get(equation);
				break;
			}
		}

		return result;
	}

	// 0(nlog(n)) time complexity
	// O(1)
	private static int[] findTwoNumbers3(int[] numbers, int target) {
		int[] result = new int[2];
		int leftPointer = 0;
		int rightPointer = numbers.length-1;
		 
		Arrays.sort(numbers);

		while (leftPointer != rightPointer) {
			int sum = numbers[leftPointer] + numbers[rightPointer];

			if (sum == target) {
				result[0] = numbers[leftPointer];
				result[1] = numbers[rightPointer];
				break;
			} 
			else if(sum<target) {
				leftPointer++;
			}
			else {
				rightPointer--;
			}
			
		}

		return result;
	}
}
