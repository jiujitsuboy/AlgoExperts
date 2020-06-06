package training.easy.recursion;

/**
 * Calculates the fibonacci series, for the specified number.
 * 
 * @author JoseAlejandro
 *
 */
public class NFibonacci {

	public static void main(String[] args) {

		System.out.println(Fibonacci.getValueNumber(1));
		System.out.println(Fibonacci.getValueNumber(2));
		System.out.println(Fibonacci.getValueNumber(3));
		System.out.println(Fibonacci.getValueNumber(4));
		System.out.println(Fibonacci.getValueNumber(5));
		System.out.println(Fibonacci.getValueNumber(6));
		System.out.println(Fibonacci.getValueNumber(7));
		System.out.println(Fibonacci.getValueNumber(8));
		System.out.println(Fibonacci.getValueNumber(9));
		System.out.println(Fibonacci.getValueNumber(10));

		System.out.println(Fibonacci.getValueNumberRecursive(1));
		System.out.println(Fibonacci.getValueNumberRecursive(2));
		System.out.println(Fibonacci.getValueNumberRecursive(3));
		System.out.println(Fibonacci.getValueNumberRecursive(4));
		System.out.println(Fibonacci.getValueNumberRecursive(5));
		System.out.println(Fibonacci.getValueNumberRecursive(6));
		System.out.println(Fibonacci.getValueNumberRecursive(7));
		System.out.println(Fibonacci.getValueNumberRecursive(8));
		System.out.println(Fibonacci.getValueNumberRecursive(9));
		System.out.println(Fibonacci.getValueNumberRecursive(10));

		System.out.println(Fibonacci.getValueNumberRecursiveCache(0));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(1));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(2));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(3));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(4));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(5));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(6));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(7));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(8));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(9));
		System.out.println(Fibonacci.getValueNumberRecursiveCache(10));

	}

	// O(n) Time
	// 0(1) Space
	private static class Fibonacci {

		public static int getValueNumber(int value) {
			int val1 = 0;
			int val2 = 1;

			int calculated = 0;

			if (value == 1) {
				calculated = val1;
			} else if (value == 2) {
				calculated = val2;
			} else {
				for (int val = 3; val <= value; val++) {
					calculated = val1 + val2;
					val1 = val2;
					val2 = calculated;
				}
			}

			return calculated;
		}

		/**
		 * Recursive version which use brute force approach (it calculate all the
		 * possible combinations)
		 * 
		 * Complexity: Time O(2^n) => In every function call, we make two other calls,
		 * so on every step, the input duplicates. Space O(1)=> only we store the sum of
		 * the two calls, which is constant
		 * 
		 * @param value
		 * @return fibonacci number
		 */
		public static int getValueNumberRecursive(int value) {
			if (value < 2) {
				return value;
			}

			return getValueNumberRecursive(value - 1) + getValueNumberRecursive(value - 2);
		}

		/**
		 * Recursive version using a cache, to store previous calculation and to avoid
		 * redoing those calculations Complexity: Time O(n)=> we just calculate from 2
		 * to n values once. Space 0(n)=> we store all the previous calculations
		 * 
		 * @param value
		 * @return
		 */
		private static int getValueNumberRecursiveCache(int value) {

			int[] cache = new int[value];

			return getValueNumberRecursiveCacheAux(value, cache);
		}

		private static int getValueNumberRecursiveCacheAux(int value, int[] cache) {

			if (value == 0) {
				return value;
			}

			else if (value < 3) {
				return value - 1;
			}

			if (cache[value - 1] == 0) {
				int sum = 0;
				for (int times = 1; times <= 2; times++) {
					if (cache[value - times - 1] == 0) {
						cache[value - times - 1] = getValueNumberRecursiveCacheAux(value - times, cache);
					}
					sum += cache[value - times - 1];
				}
				cache[value - 1] = sum;
			}

			return cache[value - 1];
		}

	}

}
