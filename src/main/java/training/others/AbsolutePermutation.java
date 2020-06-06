package training.others;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AbsolutePermutation {

	public static void main(String[] args) throws IOException {

//		String[][] test = new String[][] { { "9331", "0" }, { "9528", "794" }, { "9447", "0" }, { "9963", "0" },
//				{ "9979", "7977" }, { "9112", "948" }, { "9548", "0" }, { "9498", "2907" }, { "9836", "1699" },
//				{ "9904", "6932" } };

		String[][] test = new String[][] { { "100", "2" } };

		try (FileWriter fw = new FileWriter("D:\\Result.txt")) {
			for (int tItr = 0; tItr < test.length; tItr++) {

				int[] result = absolutePermutation(Integer.parseInt(test[tItr][0]), Integer.parseInt(test[tItr][1]));

				validatePermutation(result, Integer.parseInt(test[tItr][0]), Integer.parseInt(test[tItr][1]));

				for (int i = 0; i < result.length; i++) {
					fw.write(String.valueOf(result[i]));
//					System.out.print(String.valueOf(result[i]));

					if (i != result.length - 1) {
						fw.write(" ");
//						System.out.print(" ");
					}
				}
				fw.write("\n");
//				fw.write("-------------------------------------------------------------------");
//				fw.write("\n");
//				System.out.println();
				System.out.println(tItr);
			}			
		}
	}

	public static void validatePermutation(int[] perm, int n, int k) {

		System.out.println(n + "=" + perm.length);

		for (int i = 0; i < perm.length; i++) {
			System.out.println(String.format("perm[%d] - %d = %d = > %d", perm[i], (i + 1), perm[i] - (i + 1), k));
		}
	}

	// Complete the absolutePermutation function below.
	public static int[] absolutePermutation(int n, int k) {

		int[] permutation;

		Map<Integer, Integer> used = new HashMap<Integer, Integer>();

		if (n > 0) {
			permutation = new int[n];
		} else {
			permutation = new int[] { -1 };
		}

		for (int pos = 1; pos <= n; pos++) {
			int tempValue1 = pos - k;
			int tempValue2 = k + pos;
			int finalValue;

			if (isInsideArrayIndex(tempValue1, n) && isInsideArrayIndex(tempValue2, n)) {
				finalValue = Math.min(tempValue1, tempValue2);

				if (used.get(finalValue) != null) {
					finalValue = Math.max(tempValue1, tempValue2);
				}
			} else if (isInsideArrayIndex(tempValue1, n)) {
				finalValue = tempValue1;				
			} else if (isInsideArrayIndex(tempValue2, n)) {
				finalValue = tempValue2;				
			}

			else {
				permutation = new int[] { -1 };
				break;
			}
			
			permutation[pos - 1] = finalValue;
			used.put(finalValue, finalValue);
		}

		return permutation;

	}

	private static boolean isInsideArrayIndex(int tempValue, int arrayLength) {
		return (tempValue > 0 && tempValue <= arrayLength);
	}
}
