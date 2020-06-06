package training.medium.dynamicprogramming;

/**
 * Calculate how many changes (substitutions, insertions or deletions) you must
 * apply to a original string to become another string
 * 
 * @author JoseAlejandro
 *
 */
public class LevenshteinDistance {

	public static void main(String[] args) {

		String original = "alejo de la contra chichinaaaaaa";
		String expected = "aleja de la contra pequeñaaaaaaa";

		int minR = minNumberOfChangesRecursvie(original, expected);
		int minI = minNumberOfChangesIterative(original, expected);

		System.out.println(String.format("recursive: %d, iterative: %d", minR, minI));

	}

	/**
	 * Recursively decompose every string, removing one character in every iteration
	 * until only one remains and then compare if the characters are equal. If there
	 * are equal, no operation has to be made. If they are not equal, a substitution
	 * has to be apply. if one of the character is empty, we need to insert/remove
	 * the length of the other string (in this case insertion and removal are not
	 * distinguished).
	 * 
	 * Complexity:
	 * 
	 * Time O(min(o,e)) => we need to operate over any character of the Shores
	 * string, once the shortest string has been consumed, the process stop and
	 * return the remaining length of the other string
	 * 
	 * Space O(min(o,e)) => the frames of every call in the stack.
	 * 
	 * 
	 * @param original
	 * @param expected
	 * @return
	 */
	public static int minNumberOfChangesRecursvie(String original, String expected) {

		System.out.println(String.format("[%s-%s]", original, expected));

		if (original.length() == 0) {
			return expected.length();
		}

		if (expected.length() == 0) {
			return original.length();
		}

		return minNumberOfChangesRecursvie(original.substring(1), expected.substring(1))
				+ costSubstitution(original.charAt(0), expected.charAt(0));

	}

	/**
	 * Compare the two character for equality and if they are not equal, it return
	 * the cost of a substitution which in this case is 1
	 * 
	 * @param original
	 * @param expected
	 * @return
	 */
	private static int costSubstitution(char original, char expected) {
		return (original == expected) ? 0 : 1;
	}

	/**
	 * Iterative version, we pick the longest string and iterate over it, comparing
	 * each character with the corresponding character in the shortest or equal
	 * string
	 * 
	 * Complexity:
	 * 
	 * Time O(min(o,e)) => we iterate over every character of the longest string
	 * until we finish or we over pass the length of the shortest string, in which
	 * case we stop processing and take the remaining length as insertions
	 * 
	 * Space O(1) => we only story the changes variable
	 * 
	 * @param original
	 * @param expected
	 * @return
	 */
	public static int minNumberOfChangesIterative(String original, String expected) {

		int changes = 0;
		if (original.length() > expected.length()) {
			changes = minNumberOfChangesIterativeAux(original, expected);
		} else {
			changes = minNumberOfChangesIterativeAux(expected, original);
		}
		return changes;
	}

	private static int minNumberOfChangesIterativeAux(String longest, String shortest) {
		int changes = 0;

		for (int lonPos = 0; lonPos < longest.length(); lonPos++) {
			if (lonPos >= shortest.length()) {
				changes += longest.length() - lonPos - 1;
				break;
			} else if (longest.charAt(lonPos) != shortest.charAt(lonPos)) {
				changes++;
			}
		}
		return changes;
	}

	/**
	 * Create a matrix, original X expected length, and every cell value is going to
	 * be calculates using two rules.
	 * 
	 * original = a
	 * expected = al
	 * 
	 *    "" a l e
	 *	"" 0 1 2 3
	 *	a  1 0 1 2
     *  l  2 1 0 1
     *
     *
	 *	if str1[row]==str2[col]
     *			M[row][col] = M[row-1][col-1]
	 *	else
   	 *		M[row][col] = min( M[row-1][col-1],M[row][col-1],M[row-1][col])
	 * 
	 * 
	 * 
	 * Complexity: Time O(o*e)
	 *             Space O(o*e)
	 * 
	 * @param original
	 * @param expected
	 * @return
	 */
	public static int minNumberOfChangesAlgoExpert(String original, String expected) {

		int edits[][] = new int[original.length() + 1][expected.length() + 1];

		for (int row = 0; row <= original.length(); row++) {
			for (int col = 0; col <= expected.length(); col++) {
				if (row == 0) {
					edits[row][col] = col;
				}
				if (col == 0) {
					edits[row][col] = row;
				}
				if (original.charAt(row - 1) == expected.charAt(col - 1)) {
					edits[row][col] = edits[row - 1][col - 1];
				} else {
					edits[row][col] = Math.min(edits[row - 1][col - 1],
							Math.min(edits[row - 1][col], edits[row][col - 1]));
				}
			}
		}

		return edits[original.length()][expected.length()];

	}

}
