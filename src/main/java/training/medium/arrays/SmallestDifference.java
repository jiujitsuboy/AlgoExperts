package training.medium.arrays;

import java.util.Arrays;

/**
 * Taking two array, find the two numbers (each one from each array) that their sum is the less sum available
 * 
 * @author JoseAlejandro
 *
 */
public class SmallestDifference {

	public static void main(String[] args) {
		int[] arrayA = new int[] { -1, 5,10, 20, 28, 3 };
		int[] arrayB = new int[] { 26, 134, 135, 15, 17 };
		
		int[] result = obtainSmallestDifference(arrayA, arrayB);
		
		System.out.println(Arrays.toString(result));
	}

	/**
	 * Using two pointers starting in index 0 of both array, substract the pointed values and move the pointers
	 * to the right according to which next number on the two arrays is going to produce a lower result than the
	 * previous one.
	 * 
	 *  Complexity: Time O(N(log(N) + M(log(M)) => due to sorting the arrays, we have O(Nlog(N)) and O(Mlog(M)) 
	 *  										  respectivily. Transversing the arrays is O(N) and O(M), but this
	 *  										  value is descarted because is lower than O(Nlog(N)) and O(Mlog(M)) .
	 *              Space O(1) => one array of two positions.
	 * @param arrayA
	 * @param arrayB
	 * @return
	 */
	public static int[] obtainSmallestDifference(int[] arrayA, int[] arrayB) {
		int[] result = new int[2];

		Arrays.sort(arrayA);
		Arrays.sort(arrayB);

		int pointerA = 0;
		int pointerB = 0;
		int lessSum = Integer.MAX_VALUE;

		while (pointerA < arrayA.length && pointerB < arrayB.length) {
			int sum = Math.abs(arrayA[pointerA] - arrayB[pointerB]);
			if (sum < lessSum) {
				lessSum = sum;
				updateResultArray(arrayA[pointerA], arrayB[pointerB], result);
			}
			if (arrayA[pointerA] == arrayB[pointerB]) {
				break;
			} else if (arrayA[pointerA] < arrayB[pointerB]) {
				pointerA++;
			} else {
				pointerB++;
			}

		}

		return result;
	}

	private static void updateResultArray(int a, int b, int[] result) {
		result[0] = a;
		result[1] = b;
	}
}
