package training.easy.arrays.NonConstructibleChange;

import java.util.Arrays;

/**
 * Given an array of positive integers representing coins, find the minimum amount of change (the minimum sum of money)
 * that we cannot create.
 * <p>
 * example:
 * coins = [1,2,5]
 * answer = 4 (1 exist, 2 exist, 3 is the sum of 1 and 2 coins, but four is impossible)
 */
public class BruteForce_NonConstructibleChange {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        System.out.println(bruteforce_missingMinValue(coins)); //4


        int[] coins2 = {5, 7, 1, 1, 2, 3, 22};
        System.out.println(bruteforce_missingMinValue(coins2)); //20


        int[] coins3 = {1, 3, 5, 7};
        System.out.println(bruteforce_missingMinValue(coins3)); // 2

        int[] coins4 = {};
        System.out.println(bruteforce_missingMinValue(coins4)); // 1


        int[] coins5 = {1, 1, 1, 1, 1};
        System.out.println(bruteforce_missingMinValue(coins5)); // 1


    }


    public static int bruteforce_missingMinValue(int[] coins) {

        int target = 1;

        if (coins != null && coins.length > 0) {
            //calculate the max possible sum from the coins array, so this will give the max target value to evaluate
            long maxValue = Arrays.stream(coins).mapToLong(x -> x).sum();


            //loop from zero to max possible sum value (maxValue)
            for (; target <= maxValue; target++) {
//                System.out.println(String.format("target: %s", target));
                //by doing all possible combination, it will validate if any of the so far calculated sums is the target
                if (bruteforce_allSumCombinations(coins, 0, 0, target)) {
//                    System.out.println(String.format("target: %s -> true", target));
                } else {
//                    System.out.println(String.format("target: %s -> false", target));
                    break;
                }
            }
        }

        return target;
    }

    /**
     * Check if the target exists in any of the possible sum of elements (calculate all the possible combinations)
     * Time Complexity: O(n^2): No estoy seguro
     * <p>
     * Space Complexity: O(1)
     *
     * @param coins
     * @param fromIndex
     * @param accumulator
     * @param target
     * @return
     */
    public static boolean bruteforce_allSumCombinations(int[] coins, int fromIndex, int accumulator, int target) {

        if (target == accumulator) {
            return true;
        }
        for (int index1 = fromIndex; index1 < coins.length; index1++) {
//            System.out.println(String.format("%s", accumulator + coins[index1]));
            boolean result = bruteforce_allSumCombinations(coins, index1 + 1, (accumulator + coins[index1]), target);
            if (result) {
                return true;
            }
        }

        return false;

    }
}
