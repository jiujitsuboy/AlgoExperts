package training.easy.arrays;

import java.util.Arrays;

/**
 * transpose a matrix (change row elements for columns elements). All the elments in a diagonal from top-left to bottom-right
 * remain in the same position, the others are switched
 *
 * [1,2] = [1
 *          2]
 *
 *
 *  [[1,2],   = [ [1,3,5]
 *   [3,4],       [2.4.6]
 *   [5,6]]      ]
 */
public class TransposeMatrix {
    public static void main(String[] args) {

        int[][] originalMatrix1 = {{1,2}};
        int[][] originalMatrix2 = {{1,3,5},{2,4,6}};

        printMatrix(transposeMatrix(originalMatrix1));
        printMatrix(transposeMatrix(originalMatrix2));

    }

    public static void printMatrix(int[][] matrix){
        Arrays.stream(matrix).forEach(row-> System.out.println(Arrays.toString(row)));
    }

    /**
     * Time Complexity: O(r+c): traverse the whole matrix once.
     *
     * Space Complexity: O(r+c): we are creating a new whole matrix
     * @param matrix
     * @return
     */
    public static int[][] transposeMatrix(int[][] matrix) {

        int[][] result = {{}};

        if(matrix!=null && matrix[0].length>0){
            result = new int[matrix[0].length][matrix.length];

            for(int rowIndex=0;rowIndex<matrix.length;rowIndex++){
                for(int colIndex=0;colIndex<matrix[rowIndex].length;colIndex++){
                    result[colIndex][rowIndex] = matrix[rowIndex][colIndex];
                }
            }
        }

        return result;
    }
}
