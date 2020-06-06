package training.medium.dynamicprogramming;

/**
 * Calculate the numbers of ways that you can represent a target amount with an
 * array of denominations which could be infinity on each denomination
 * 
 * @author JoseAlejandro
 *
 */
public class NumberOfWaysToMakeChange {

	public static void main(String[] args) {
		int target = 10000;
//		int[] denominations = new int[] { 1, 5, 10, 25 };

		int[] denominations = new int[] { 2, 5 };

		int res = 0;

//		res = SolutionsNumberOfWaysToMakeChange.calcCombinationWithDuplicateBruteForce(denominations, target);
//		System.out.println(res);
//		
//		
//		res = SolutionsNumberOfWaysToMakeChange.calcCombinationWithDuplicateWithCache(denominations, target);
//		System.out.println(res);

		
		res = SolutionsNumberOfWaysToMakeChange.calcCombinationNoDuplicateBruteForce(denominations, target);
		System.out.println(res);
		
		res = SolutionsNumberOfWaysToMakeChange.calcCombinationNoDuplicateIterative(denominations, target);
		System.out.println(res);			

	}
}

class SolutionsNumberOfWaysToMakeChange {

	/**
	 * Brute force approach,where I found all the combinations, just by Subtracting
	 * the current denomination from the target value, and calculate the
	 * combinations for the remaining part. This process is repeated for every
	 * denominations.
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
	public static int calcCombinationWithDuplicateBruteForce(int[] denominations, int target) {

		int combination = 0;
		if (target > 0) {
			for (int denom : denominations) {
				int diff = target - denom;
//				System.out.println(String.format("[%d-%d]", target, denom));
				if (diff >= 0) {
					combination += calcCombinationWithDuplicateBruteForce(denominations, diff);
				}
			}
		} else {
			combination = 1;
		}
		return combination;
	}	

		
	public static int calcCombinationWithDuplicateWithCache(int[] denominations, int target) {

		int[] diffs = new int[target];

		return calcCombinationWithDuplicateWithCacheAux(denominations, diffs, target);
	}

	/**
	 * cache approach,where I found all the combinations but only process those that
	 * I did´t process before , for any repeated combination, I just pick the cache
	 * value.
	 * 
	 * Complexity: Time O(d*t) => d = denominations and t = target
	 *  
	 *             Space O(N) => the cache is N length
	 * 
	 * @param denominations
	 * @param target
	 * @return
	 */
	private static int calcCombinationWithDuplicateWithCacheAux(int[] denominations, int[] diffs, int target) {

		int combination = 0;

		if (target > 0) {
			for (int denom : denominations) {
				int diff = target - denom;
				if (diff >= 0) {
					if (diffs[diff] == 0) {
//						System.out.println(String.format("[%d-%d] Calculation...", target, denom));
						diffs[diff] = calcCombinationWithDuplicateWithCacheAux(denominations, diffs, diff);
						combination += diffs[diff];

					} else {
						System.out.println(String.format("[%d-%d] cached...", target, denom));
						combination += diffs[diff];
					}
				}
			}
		} else {
			combination = 1;
		}
		return combination;
	}
	
	public static int calcCombinationNoDuplicateBruteForce(int[] denominations, int target) {

		int untilDenom = 0;
		if (denominations != null) {
			untilDenom = denominations.length;
		}

		return calcCombinationNoDuplicateBruteForceAux(denominations, untilDenom, target);
	}

	/**
	 * We calculate the combinations by groups, starting with one denomination, then two denominations,etc
	 * this way we eliminate cases where the combinations is the same just in different order
	 * 
	 * Complexity: Time O()
	 * 
	 * @param denominations
	 * @param untilDenom
	 * @param target
	 * @return
	 */
	private static int calcCombinationNoDuplicateBruteForceAux(int[] denominations, int untilDenom, int target) {

		int combination = 0;
		if (target > 0) {
			for (int denom = 0; denom < untilDenom; denom++) {
				int diff = target - denominations[denom];
//				System.out.println(String.format("[%d-%d]", target, denominations[denom]));
				if (diff >= 0) {
					combination += calcCombinationNoDuplicateBruteForceAux(denominations, denom + 1, diff);
				}
			}
		} else {
			combination = 1;
		}
		return combination;
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
	public static int calcCombinationNoDuplicateIterative(int[] denominations, int target) {

		int[] accumulatePerDenom = new int[target + 1];
		/*
		 * because this means that I don't have any remainder of the original value, so
		 * I have found a combinations of denominations that represent the whole target
		 */
		accumulatePerDenom[0] = 1;

		for (int denom : denominations) {
			for (int amount = 1; amount <= target; amount++) {
				if (denom <= amount) {
					accumulatePerDenom[amount] += accumulatePerDenom[amount - denom];
				}
			}
		}

		return accumulatePerDenom[target];

	}

}
/*
modenas = {1, 2}
valor = $4

1, 1, 1, 1
2, 2
2, 1, 1

Fuerza Bruta

4 - 1 = 3
		  3 - 2 = 1
					1 - 1 = 0 => 1 opcion (1, 2, 1)
         3 - 1 = 2
                    2 - 2 = 0 => 1 opcion (1, 1, 2)
                    2 - 1 = 1
							  1 - 1 = 0 => 1 opcion (1, 1, 1, 1) 

4 - 2 = 2
		  2 - 2 = 0  =>  1 opcion (2,2)
         2 - 1 = 1   
					1 - 1 = 0 => 1 opcion (2,1,1)

-----------------------------------------------------------------------------------------------------------------------------
Por cada subconjunto de monedas, empezando con 1 denominacions y incrementando

4 - 1 = 3	  
         3 - 1 = 2                    
                    2 - 1 = 1
				               1 - 1 = 0 => 1 opcion (1, 1, 1, 1) 						

4 - 2 = 2
	      2 - 2 = 0  =>  1 opcion (2,2)
         2 - 1 = 1   
		           1 - 1 = 0 => 1 opcion (2,1,1) 
			
------------------------------------------------------------------------		


        0  1  2  3  
	  1  1  1  1  1  
     2  1  1  2  2  
 

					max[valor] = max[valor]+ max[valor-moneda)]
*/