package training.easy.searching;

import java.util.Arrays;

/**
 * Find the sum of 3 elements within  the array that represent the amount seeking
 * 
 * @author JoseAlejandro
 *
 */
public class FindThreeLargestNumbers {

	public static void main(String[] args) {

		int[] nums = new int[] { 141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7 };

		int[] largestNum = getThreeLargestNumbers(nums);
		
		System.out.println(Arrays.toString(largestNum));

	}

	//O(n)Time
	//O(1) Space
	public static int[] getThreeLargestNumbers(int[] numbers) {

		int[] largestNum = new int[3];

		for (int num = 0; num < numbers.length; num++) {
			evaluateIfIsLargestShifting(largestNum, numbers[num]);
		}

		return largestNum;
	}

	private static void evaluateIfIsLargest(int[] largestNum, int value) {
		int temp;
		for (int num = largestNum.length-1; num >= 0; num--) {
			if (value > largestNum[num]) {
				temp = largestNum[num];
				largestNum[num] = value;
				value = temp;
			}
		}
	}
	
	private static void evaluateIfIsLargestShifting(int[] largestNum, int value) {		
		for (int num = largestNum.length-1; num >= 0; num--) {
			if (value > largestNum[num]) {
				shiftPosition(largestNum,value,num);
				break;
			}
		}
	}
	
	private static void shiftPosition(int[] largestNum,int value,int position) {		
		for (int num = 0; num <= position; num++) {
			if(num==position) {
				largestNum[num] = value;
			}
			else {
				largestNum[num]=largestNum[num+1];
			}			
		}
	}

}
