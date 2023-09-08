package training.medium.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * In an array of integer numbers from 1 to n, where n is the length of the array.,
 * Find the first element that is repeated. if not elmment is repeated return -1.
 *
 * i.e
 *
 * [2, 1, 5, 2, 3, 3, 4] = 2
 * [2, 1, 5, 3, 3, 2, 4] = 3
 * [2, 1, 5, 8, 3, 9, 4] = -1
 *
 */
public class FirstDuplicateValue {
    public static void main(String[] args) {
        int[] array1 = {2, 1, 5, 2, 3, 3, 4};
        int[] array2 = {2, 1, 5, 3, 3, 2, 4};
        int[] array3 = {2, 1, 5, 7, 3, 6, 4};

        System.out.println(findFirstDuplicateMutateOriginalArray(array1));
        System.out.println(findFirstDuplicateMutateOriginalArray(array2));
        System.out.println(findFirstDuplicateMutateOriginalArray(array3));
    }

    /**
     * Time complexity: O(n): we transverse the array once.
     *
     * Space complexity: O(n): We store every element in a map
     * @param array
     * @return
     */
    private static int findFirstDuplicateNoMutateOriginalArray(int[] array) {
        int result = -1;
        Map<Integer, Integer> occurance = new HashMap<>();

        for (int index = 0; index < array.length; index++) {
            int value = array[index];
            Integer valueOcurrance = occurance.get(value) != null ? occurance.get(value) : 0;

            if ((valueOcurrance + 1) > 1) {
                result = value;
                break;
            } else {
                occurance.put(value, valueOcurrance + 1);
            }

        }

        return result;
    }

    /**
     * To make the solution constant space, instead of using a map or set, we are going to use the array indexes to store
     * if certain value has been spotted before (this works because the values of the array are between 1 and n which is
     * the length of the array, so every value - 1 correspond to a valid index in the array). So the array has numbers
     * from 1 to n, so we are going to subtract one to the value and this is going to be the index where we are going to
     * store if we see it already. So to do this, we are going to change the value at that index (values index - 1, give
     * the position, where we are going to change the value sign). So if when we check the value of an index position and
     * the value is negative, that mean we already saw this value and we return it as the result.
     *
     * Time complexity: O(n): we transverse the array once.
     *
     * Space complexity: O(1): We use the same input array and mutate it
     *
     * @param array
     * @return
     */
    private static int findFirstDuplicateMutateOriginalArray(int[] array) {
        int result = -1;

        for (int index = 0; index < array.length; index++) {
            int value = Math.abs(array[index]);
            int indexToStoreOccurance = value - 1;

            if (array[indexToStoreOccurance] < 0) {
                result = value;
                break;
            } else {
                array[indexToStoreOccurance] *= -1;
            }

        }

        return result;
    }


}
