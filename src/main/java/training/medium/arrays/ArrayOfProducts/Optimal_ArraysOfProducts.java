package training.medium.arrays.ArrayOfProducts;

import java.util.Arrays;

/**
 * receiving a non empty array of integers, return an array of the same length where each element in the output array
 * is equal to the product of every other number in the input array
 *
 * i.e
 *
 * [5, 1, 4, 2] => [(1*4*2),(5*4*2),(5*1*2),(5*1*4)]
 *                 [8,40,10,20]
 *
 *
 */
public class Optimal_ArraysOfProducts {
    public static void main(String[] args) {
        int[] array = {5, 1, 4, 2};
//        System.out.println(Arrays.toString(arrayOfProductsTwoAuxArrays(array)));
        System.out.println(Arrays.toString(arrayOfProductsOneAuxArrays(array)));
    }


    /**
     *
     * Create two arrays and one product variable which start in ONE.
     * From left to right, insert the product value in the current index and then multiply the original array value in that position with the product.
     * Repeat now from right to left in the second array. Then multiply both arrays in the same index position.
     *
     * Time complexity: O(n): we traverse the array length 3 times (one from left to right, another from right to left and then the orginal one more time)
     *
     * Space complexity: O(n): we store 2 array with the same length as the input
     * @param array
     * @return
     */
    public static int[] arrayOfProductsTwoAuxArrays(int[] array) {

        int[] arrayLeft = new int[array.length];
        int[] arrayRight = new int[array.length];

        int product = 1;
        for(int index=0;index<array.length;index++){
            arrayLeft[index] = product;
            product *= array[index];
        }

//        System.out.println(Arrays.toString(arrayLeft));

        product = 1;
        for(int index=array.length-1;index>=0;index--){
            arrayRight[index] = product;
            product *= array[index];
        }

//        System.out.println(Arrays.toString(arrayRight));

        for(int index=0;index<array.length;index++){
            array[index] = arrayLeft[index] * arrayRight[index];
        }

        return array;
    }

    /**
     *
     * Create one array and one product variable which start in ONE.
     * From left to right, insert the product value in the current index and then multiply the original array value in that position with the product.
     * Repeat now from right to left on the same auxiliary array used above.
     *
     * Time complexity: O(n): we traverse the array length 2 times (one from left to right, another from right to left)
     *
     * Space complexity: O(n): we store anothe array with the same length as the input
     * @param array
     * @return
     */
    public static int[] arrayOfProductsOneAuxArrays(int[] array) {

        int[] solution = new int[array.length];

        int product = 1;
        for(int index=0;index<array.length;index++){
            solution[index] = product;
            product *= array[index];
        }

//        System.out.println(Arrays.toString(solution));

        product = 1;
        for(int index=array.length-1;index>=0;index--){
            solution[index] *= product;
            product *= array[index];
        }

//        System.out.println(Arrays.toString(solution));

        return solution;
    }
}
