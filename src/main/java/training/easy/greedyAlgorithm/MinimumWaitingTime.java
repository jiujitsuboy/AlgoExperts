package training.easy.greedyAlgorithm;

import java.util.Arrays;

/**
 * You're given a non-empty array of positive integers representing the amounts of time that
 * specific queries take to execute. Only one query can be executed at a time, but the queries can
 * be executed in any order.
 * <p>
 * A query's waiting time is defined as the amount of time that it must wait before its execution
 * starts. In other words, if a query is executed second, then its waiting time is the duration of
 * the first query; if a query is executed third, then its waiting time is the sum of the durations
 * of the first two queries.
 * <p>
 * Write a function that returns the minimum amount of total waiting time for all the queries.
 * <p>
 * For example, if you're given the queries of durations [1, 4, 5] , then the total waiting time if
 * the queries were executed in the order of [5, 1, 4] would be (0) + (5) + (5 + 1) = 11.
 * <p>
 * The first query of duration 5 would be executed immediately, so its waiting time would be 0, the
 * second query of duration 1 would have to wait 5 seconds (the duration of the first query) to be
 * executed, and the last query would have to wait the duration of the first two queries before
 * being executed.
 * <p>
 * Note: you're allowed to mutate the input array.
 * <p>
 * Sample Input queries  = [3, 2, 1, 2, 6] = (3) + (3 + 2) + (3 + 2 + 1) + (3 + 2 + 1 + 2)
 * <p>
 * 1, 3, 5, 8 = 17
 * <p>
 * Sample Output 17
 */
public class MinimumWaitingTime {


  public static void main(String[] args) {
    int[] queries = {17, 4, 3};
    System.out.println(minimumWaitingTime(queries));
  }

  /**
   * Time Complexity: O(nlog(n)), we sort the array then trasverse the array once. nlog(n) + n =
   * nlog(n) Space Complexity: O(1), we use the same array, so no extra space needed.
   */
  public static int minimumWaitingTime(int[] queries) {

    int previousTotal = 0;
    int totalWaitingTime = 0;

    if (queries.length == 0) {
      return 0;
    }

    Arrays.sort(queries);

    for (int index = 0; index < queries.length - 1; index++) {
      previousTotal += queries[index];
      queries[index] = previousTotal;
      totalWaitingTime += queries[index];
    }

    return totalWaitingTime;
  }
}
