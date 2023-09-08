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
public class BruteForce_ArraysOfProducts {
    public static void main(String[] args) {
        int[] array = {5, 1, 4, 2};
        System.out.println(Arrays.toString(arrayOfProducts(array)));
    }

    /**
     *
     * take every element and traverse the array again multiplying the rest of elements
     *
     * Time complexity: O(n^2)
     *
     * Space complexity: O(n)
     * @param array
     * @return
     */
    public static int[] arrayOfProducts(int[] array) {
        int[] result = new int[array.length];

        for (int index1 = 0; index1 < array.length; index1++) {
            result[index1] = 1;
            for (int index2 = 0; index2 < array.length; index2++) {
                if (array[index1] != array[index2]) {
                    result[index1] *= array[index2];
                }
            }
        }

        return result;
    }
}
