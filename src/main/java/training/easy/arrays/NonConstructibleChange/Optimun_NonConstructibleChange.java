package training.easy.arrays.NonConstructibleChange;

import java.util.Arrays;

public class Optimun_NonConstructibleChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 4, 5};
        System.out.println(optimin_missingMinValue(coins)); //4


        int[] coins2 = {5, 7, 1, 1, 2, 3, 22};
        System.out.println(optimin_missingMinValue(coins2)); //20


        int[] coins3 = {1, 3, 5, 7};
        System.out.println(optimin_missingMinValue(coins3)); // 2

        int[] coins4 = {};
        System.out.println(optimin_missingMinValue(coins4)); // 1


        int[] coins5 = {1, 1, 1, 1, 1};
        System.out.println(optimin_missingMinValue(coins5)); // 6
    }

    /**
     * We other the array from lower to higher and we start traversing the array summing the coins. We know that we can
     * make the target value with any combinacion of this sorted coins if the current coin is less or equal to the current sum amount + 1.
     *
     * for example [1,2,5]
     *
     * when evaluation 5 against the current accumulated amount, we see that 5 is greater  than 3 + 1. So that mean that 4 is not possible to
     * get by combining this coins, because so far the sum is 3 and the next coin is 5, so we can get 3, 5 and 8, but no 4
     *
     *
     *Time Complexity: O(nlogn): because we order the array.
     *
     * Space Complixity: O(1)
     *
     *
     * @param coins
     * @return
     */
    private static int optimin_missingMinValue(int[] coins) {

        int changes = 0;

        if (coins != null && coins.length > 0) {
            Arrays.sort(coins);

            for (int coin : coins) {
                if (coin > (changes + 1)) {
                    break;
                } else {
                    changes += coin;
                }
            }
        }

        return ++changes;
    }
}

