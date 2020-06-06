package training.medium.dynamicprogramming;

public class MinNumberOfCoinsForChange {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int target = 7;
//		int[] denominations = new int[] { 1, 5, 10, 25 };

		int[] denominations = new int[] { 1, 2, 4 };

		int res = 0;

//		System.out.println(String.format(" %s %s  %s", "T","D","C"));
//		res = SolutionMinNumberOfCoinsForChange.calcMinCoinsWithDuplicateBruteForce(denominations, target);
//		System.out.println(res);
//
//		res = SolutionMinNumberOfCoinsForChange.calcMinCoinsWithDuplicateWithCache(denominations, target);
//		System.out.println(res);

//		res = SolutionMinNumberOfCoinsForChange.calcMinCoinsNoDuplicateBruteForce(denominations, target);
//		System.out.println(res);

		res = SolutionMinNumberOfCoinsForChange.calcMinCoinsNoDuplicateIterative(denominations, target);
		System.out.println(res);

	}

}

class SolutionMinNumberOfCoinsForChange {

	/**
	 * Brute force approach,where I found all the combinations, just by Subtracting
	 * the current denomination from the target value, and calculate the
	 * combinations for the remaining part using all the available denominations.
	 * Every denominations used is count as 1, so the end result, just pick the
	 * lower number of coins to represent the target value
	 * 
	 * Complexity:
	 * 
	 * Time O(d^t) => d = denominations and t = target
	 * 
	 * Space O(1) => only we store combination variable
	 * 
	 * @param denominations
	 * @param target
	 * @return
	 */
	public static int calcMinCoinsWithDuplicateBruteForce(int[] denominations, int target) {

		int nroCoins = 0;
		int minNroCoins = Integer.MAX_VALUE;

		if (target > 0) {

			for (int denom : denominations) {
				int diff = target - denom;
				System.out.println(String.format("[%d-%d]", target, denom));
				if (diff >= 0) {
					nroCoins = calcMinCoinsWithDuplicateBruteForce(denominations, diff);
					nroCoins++;
					if (nroCoins < minNroCoins) {
						minNroCoins = nroCoins;
					}
//					System.out.println(String.format("[%d-%d]=%d", target, denom,minNroCoins));
				}
			}
		} else {
			minNroCoins = nroCoins;
		}

		return minNroCoins;
	}

	public static int calcMinCoinsWithDuplicateWithCache(int[] denominations, int target) {

		int[] diffs = new int[target];

		return calcMinCoinsWithDuplicateWithCacheAux(denominations, diffs, target);
	}

	/**
	 * cache approach,where I found all the combinations but only process those that
	 * I did´t process before , for any repeated combination, I just pick the cache
	 * value.
	 * 
	 * Complexity: Time O(d*t) => d = denominations and t = target Space O(N) =>
	 * only we store combination variable
	 * 
	 * @param denominations
	 * @param target
	 * @return
	 */
	private static int calcMinCoinsWithDuplicateWithCacheAux(int[] denominations, int[] diffs, int target) {

		int nroCoins = 0;
		int minNroCoins = Integer.MAX_VALUE;

		if (target > 0) {

			for (int denom : denominations) {
				int diff = target - denom;
				if (diff >= 0) {
					if (diffs[diff] == 0) {
						System.out.println(String.format("[%d-%d]Calculating.....", target, denom));
						nroCoins = calcMinCoinsWithDuplicateWithCacheAux(denominations, diffs, diff);
						nroCoins++;
					} else {
						System.out.println(String.format("[%d-%d]Cached", target, denom));
						nroCoins = diffs[diff];
					}

					if (nroCoins < minNroCoins) {
						minNroCoins = nroCoins;
					}
					diffs[diff] = minNroCoins;
				}
			}
		} else {
			minNroCoins = nroCoins;
		}

		return minNroCoins;

	}

	public static int calcMinCoinsNoDuplicateBruteForce(int[] denominations, int target) {

		int untilDenom = 0;
		if (denominations != null) {
			untilDenom = denominations.length;
		}

		return calcMinCoinsNoDuplicateBruteForceAux(denominations, untilDenom, target);
	}

	private static int calcMinCoinsNoDuplicateBruteForceAux(int[] denominations, int untilDenom, int target) {

		int nroCoins = 0;
		int minNroCoins = Integer.MAX_VALUE;

		if (target > 0) {

			for (int denom = 0; denom < untilDenom; denom++) {
				int diff = target - denominations[denom];
				System.out.println(String.format("[%d-%d]", target, denominations[denom]));
				if (diff >= 0) {
					nroCoins = calcMinCoinsNoDuplicateBruteForceAux(denominations, denom + 1, diff);
					nroCoins++;
					if (nroCoins < minNroCoins) {
						minNroCoins = nroCoins;
					}
//					System.out.println(String.format("[%d-%d]=%d", target, denom,minNroCoins));
				}
			}
		} else {
			minNroCoins = nroCoins;
		}

		return minNroCoins;
	}

	/**
	 * Complexity:
	 * 
	 * Time O(Nd)=> N de target value, d = number of denominations
	 * 
	 * Space O(N) => we create an array calls ways of length target + 1
	 * 
	 * @param denominations
	 * @param target
	 * @return
	 */
	public static int calcMinCoinsNoDuplicateIterative(int[] denominations, int target) {

		int[] denomPerValue = new int[target + 1];
		for (int index = 0; index < denomPerValue.length; index++) {
			denomPerValue[index] = Integer.MAX_VALUE;
		}
		/*
		 * because this means that I don't have any remainder of the original value, so
		 * I have found a combinations of denominations that represent the whole target
		 */
		denomPerValue[0] = 0;

		for (int denom : denominations) {
			for (int amount = 1; amount <= target; amount++) {
				if (denom <= amount) {
					int prevValue = (denomPerValue[amount - denom] == Integer.MAX_VALUE) ? Integer.MAX_VALUE
							: denomPerValue[amount - denom] + 1;

					denomPerValue[amount] = Math.min(prevValue, denomPerValue[amount]);
				}
			}
		}

		//If I could not represent the target value with the denominations provided, the result is going to be Integer.MAX_VALUE, so I return -1 
		return (denomPerValue[target] == Integer.MAX_VALUE) ? -1 : denomPerValue[target];

	}
}

/*
 * Fuerza Bruta

    1
4 - 1 = 3	  2
          3 - 1 = 2      3                    
                     2 - 1 = 1     4
				               1 - 1 = 0 => 1 opcion (1, 1, 1, 1)  4 monedas
					
                        3
                     2 - 2	= 0 => 1 opcion (1,1,2)				3 monedas
							   
							   
			  2				   
          3 - 2 = 1    3
                    1 - 1 = 0 => 1 opcion (1, 2, 1	) 		  3 monedas

    1
4 - 2 = 2     2
	      2 - 2 = 0  =>  1 opcion (2,2)						2 monedas
		   
		      2
          2 - 1 = 1    3    
		           1 - 1 = 0 => 1 opcion (2,1,1) 			3 monedas
				   
				   
Cache

    1
4 - 1 = 3	  2
          3 - 1 = 2      3                    
                     2 - 1 = 1     4
				               1 - 1 = 0 => 1 opcion (1, 1, 1, 1)  4 monedas				                       

    1
4 - 2 = 2     2
	      2 - 2 = 0  =>  1 opcion (2,2)						2 monedas
		   
		      2
          2 - 1 = 1    3    
		           1 - 1 = 0 => 1 opcion (2,1,1) 			3 monedas

				   
-----------------------------------------------------------------------------------------------------

Valor  denominacions   resultado
			   0         1             1
			   0         2             1
			   1         1             1
			   1         2             1
		       2         1             1
			   2         2             1
			   3         1             1
			   3         2             
			 
	0  1  2  3  4   5
  2 0  0  1  1  2	2
	
	0  1  2  3  4  5
1   0  1  2  3  4  5
2   0  1  1  2  2  3
5   0  1  2  2  2  1



      values[amount] =  min(values[amount-denom] + 1 , values[amount]) 				

 */