package training.medium.arrays;

import java.util.Arrays;

/**
 * Take a matrix and convert it into a arrar which values are taken in spiral
 * form from th matrix
 * 
 * @author JoseAlejandro
 *
 */
public class SpiralTransversal {

	public static void main(String[] args) {
//		Integer[][] matrix = new Integer[][] {{ 1}};
//		Integer[][] matrix = new Integer[][] { { 1, 2 }, { 4, 3 } };
//		Integer[][] matrix = new Integer[][] { { 1, 2, 3, 4 }, { 8, 7, 6, 5 } };

//		Integer[][] matrix = new Integer[][] { { 1, 2, 3, 4 }, { 12, 13, 14, 5 }, { 11, 16, 15, 6 }, { 10, 9, 8, 7 } };
		Integer[][] matrix = new Integer[][] { { 1, 2, 3, 4, 5 }, { 16, 17, 18, 19, 6 }, { 15, 24, 25, 20, 7 },{ 14, 23, 22, 21, 8 }, { 13, 12, 11, 10, 9 } };
		Integer[] spiral = new Integer[matrix.length * matrix[0].length];

		matrixToSpiralArray2(matrix, spiral);
//		matrixToSpiralArrayRecursive(matrix, spiral);
//		matrixToSpiralArrayWithDirection(matrix, spiral);
//		matrixToSpiralArray(matrix, spiral);
		System.out.println(Arrays.toString(spiral));

	}

	/**
	 * Complexity: Time O(N) Space O(N)
	 * 
	 * @param <T>
	 * @param matrix
	 * @param spiral
	 */
	public static <T> void matrixToSpiralArrayWithDirection(T[][] matrix, T[] spiral) {

		int horizontal = 0;
		int vertical = 0;
		int currentDirIndex = vertical;
		int currentMaxLength = (matrix != null) ? matrix[0].length : 0;

		Direction direction = Direction.HorizontalPos;

		int currentIndex = 0;
		int lengthOffSet = 0;

		while (currentDirIndex >= lengthOffSet && currentDirIndex < (currentMaxLength - lengthOffSet)) {
			while (currentDirIndex >= lengthOffSet && currentDirIndex < (currentMaxLength - lengthOffSet)) {

				switch (direction) {
				case HorizontalPos:
					spiral[currentIndex++] = matrix[horizontal][currentDirIndex++];
					break;
				case HorizontalNeg:
					spiral[currentIndex++] = matrix[horizontal][currentDirIndex--];
					break;
				case VerticalPos:
					spiral[currentIndex++] = matrix[currentDirIndex++][vertical];
					break;
				case VerticalNeg:
					spiral[currentIndex++] = matrix[currentDirIndex--][vertical];
					break;
				}

			}
			switch (direction) {
			case HorizontalPos:
				direction = Direction.VerticalPos;
				vertical = (currentMaxLength - lengthOffSet) - 1;
				currentDirIndex = ++horizontal;
				currentMaxLength = matrix.length;
				break;
			case VerticalPos:
				direction = Direction.HorizontalNeg;
				horizontal = (currentMaxLength - lengthOffSet) - 1;
				currentDirIndex = --vertical;
				currentMaxLength = matrix[0].length;
				break;
			case HorizontalNeg:
				direction = Direction.VerticalNeg;
				vertical = lengthOffSet;
				currentDirIndex = --horizontal;
				currentMaxLength = matrix.length;
				lengthOffSet++;
				break;
			case VerticalNeg:
				direction = Direction.HorizontalPos;
				horizontal = lengthOffSet;
				currentDirIndex = ++vertical;
				currentMaxLength = matrix[0].length;
				break;
			}

		}
	}

	public static <T> void matrixToSpiralArray(T[][] matrix, T[] spiral) {

		int horizontal = 0;
		int vertical = 0;
		int offSet = 0;
		int availIndex = 0;

		while (horizontal < Math.ceil(matrix.length / 2d)) {

			while (vertical < (matrix[0].length - offSet)) {
				spiral[availIndex++] = matrix[horizontal][vertical];
				vertical++;
			}

			horizontal++;
			vertical = (matrix[0].length - offSet) - 1;

			while (horizontal < (matrix.length - offSet)) {
				spiral[availIndex++] = matrix[horizontal][vertical];
				horizontal++;
			}

			horizontal = (matrix.length - offSet) - 1;
			vertical--;

			while (vertical >= offSet) {
				spiral[availIndex++] = matrix[horizontal][vertical];
				vertical--;
			}

			horizontal--;
			vertical = offSet;

			while (horizontal > offSet) {
				spiral[availIndex++] = matrix[horizontal][vertical];
				horizontal--;
			}

			offSet++;
			horizontal = offSet;
			vertical++;

		}

	}

	public static <T> void matrixToSpiralArrayRecursive(T[][] matrix, T[] spiral) {
		int horizontal = 0;
		int vertical = 0;
		int offSet = 0;
		int availIndex = 0;

		matrixToSpiralArrayRecursiveAux(horizontal, vertical, offSet, availIndex, matrix, spiral);
	}

	public static <T> void matrixToSpiralArrayRecursiveAux(int horizontal, int vertical, int offSet, int availIndex,
			T[][] matrix, T[] spiral) {

		if (horizontal < Math.ceil(matrix.length / 2d)) {
			while (vertical < (matrix[0].length - offSet)) {
				spiral[availIndex++] = matrix[horizontal][vertical];
				vertical++;
			}

			horizontal++;
			vertical = (matrix[0].length - offSet) - 1;

			while (horizontal < (matrix.length - offSet)) {
				spiral[availIndex++] = matrix[horizontal][vertical];
				horizontal++;
			}

			horizontal = (matrix.length - offSet) - 1;
			vertical--;

			while (vertical >= offSet) {
				spiral[availIndex++] = matrix[horizontal][vertical];
				vertical--;
			}

			horizontal--;
			vertical = offSet;

			while (horizontal > offSet) {
				spiral[availIndex++] = matrix[horizontal][vertical];
				horizontal--;
			}

			offSet++;
			horizontal = offSet;
			vertical++;

			matrixToSpiralArrayRecursiveAux(horizontal, vertical, offSet, availIndex, matrix, spiral);

		}
	}

	public static <T> void matrixToSpiralArray2(T[][] matrix, T[] spiral) {

		
		int row = 0;
		int rowLen = matrix.length-1;
		int column = 0;
		int colLen = matrix[0].length-1;
		int availIndex = 0;

		while (row <= rowLen && column <= colLen) {

			//left to right
			for (int pos = column; pos <= colLen; pos++) {
				spiral[availIndex++] = matrix[row][pos];
			}

			//up to bottom
			for (int pos = row + 1; pos <= rowLen; pos++) {
				spiral[availIndex++] = matrix[pos][colLen];
			}

			//right to left
			for (int pos = colLen - 1; pos >= column; pos--) {
				spiral[availIndex++] = matrix[rowLen][pos];				
			}

			//bottom to up
			for (int pos = rowLen-1 ;pos > row;pos--) {
				spiral[availIndex++] = matrix[pos][column];				
			}
			
			row++;
			rowLen--;
			column++;
			colLen--;

		}

	}

	private enum Direction {
		HorizontalPos, VerticalPos, HorizontalNeg, VerticalNeg
	}

}
