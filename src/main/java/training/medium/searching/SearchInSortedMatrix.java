package training.medium.searching;

import java.util.Arrays;

/**
 * Finds the position of a number in a sorted matrix (where every row and column
 * are sort)
 * 
 * @author JoseAlejandro
 *
 */
public class SearchInSortedMatrix {

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 1, 4, 7, 12, 15, 1000 }, { 2, 5, 19, 31, 32, 1001 },
				{ 3, 8, 24, 33, 35, 1002 }, { 40, 41, 42, 44, 45, 1003 }, { 99, 100, 103, 106, 128, 1004 } };

		System.out.println(String.format("Position: %s", Arrays.toString(findValueInSortedMatrix(matrix, -1))));

	}

	/**
	 * We start searching on the last column of the first row of the matrix. From
	 * there we go down or left according to the current value regarding if it is
	 * greater or smaller that the seeking number.
	 * 
	 * Complexity
	 * 
	 * Time O(N+M) => We in the worst case traverse a whole row and a whole column
	 * (on average we do zigzag traversal)
	 * 
	 * Space O(1) => no extra data stored required, we only use two indexers to
	 * traverses the matrix and a two length array as result
	 * 
	 * @param matrix
	 * @param value
	 * @return
	 */
	public static int[] findValueInSortedMatrix(int[][] matrix, int value) {
		int[] result = new int[] { -1, -1 };
		int row = 0;
		int col = 0;

		if (matrix.length > 0 && matrix[row].length > 0) {
			col = matrix[row].length - 1;

			while (row < matrix.length && col >= 0)
				if (matrix[row][col] == value) {
					result[0] = row;
					result[1] = col;
					break;
				} else if (matrix[row][col] > value) {
					col--;
				} else {
					row++;
				}
		}

		return result;

	}

}
