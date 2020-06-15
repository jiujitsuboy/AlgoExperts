package training.medium.strings;


/**
 * Calculate the longest palindrome string or substring in the specified input
 * 
 * @author JoseAlejandro
 *
 */
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		String text = "1aaaaaaaaa1";
		String longPalin = longestSubPalindrome(text);
		System.out.println(longPalin);

	}

	/**
	 * Instead of calculating all the possible substrings and then check which of them are palindroms and get
	 * the longest one. Which would end in O(n^3) => O(n^2) for getting all the possible substrings and O(n) 
	 * to chech if the current string is a palindrom. We implement a traverse of the string where for each
	 * position, we expand on both directions comparing the letter to see if there is a palindrom or not. On 
	 * each expansion, we validate on two initial position (the current index, and the current index plus one, 
	 * and the previous index and the current index plus one).
	 * 
	 *Complexity
	 *
	 *Time O(n^2)  => we traverse the string once O(n), but on each letter we expand on both direction twice O(n^2)
	 *
	 *Space O(1) => we only store the initial and final indexes of the longest palindrom
	 *
	 * 
	 * 
	 * @param text
	 * @return
	 */
	public static String longestSubPalindrome(String text) {
		String longPalindrome = null;

		if (text != null && text.length() > 0) {

			int[] idxsLngPalin = new int[] { 0, 0 };

			for (int index = 1; index < text.length(); index++) {

				int[] idxsLngPalinOdd = positionPalindromCheck(index - 1, index + 1, text);
				int[] idxsLngPalinEven = positionPalindromCheck(index, index - 1, text);
				updateLongestPalindIndexes(idxsLngPalin, idxsLngPalinOdd);
				updateLongestPalindIndexes(idxsLngPalin, idxsLngPalinEven);
			}

			longPalindrome = text.substring(idxsLngPalin[0], idxsLngPalin[1] + 1);
		}

		return longPalindrome;
	}

	private static int[] positionPalindromCheck(int leftIndex, int rightIndex, String text) {

		int[] indexLongPalind = new int[2];

		if (rightIndex - leftIndex == 2) {
			indexLongPalind[0] = leftIndex + 1;
			indexLongPalind[1] = rightIndex - 1;
		} else {
			indexLongPalind[0] = leftIndex;
			indexLongPalind[1] = leftIndex;
		}

		while (leftIndex > -1 && rightIndex < text.length()) {
			if (text.charAt(leftIndex) == text.charAt(rightIndex)) {
				indexLongPalind[0] = leftIndex--;
				indexLongPalind[1] = rightIndex++;
			} else {
				break;
			}
		}

		return indexLongPalind;
	}

	private static void updateLongestPalindIndexes(int[] savedIdxsLngPalind, int[] currentIdxsLngPalind) {

		int currentLength = 1 + (currentIdxsLngPalind[1] - currentIdxsLngPalind[0]);
		int savedLength = 1 + (savedIdxsLngPalind[1] - savedIdxsLngPalind[0]);

		if (currentLength > savedLength) {
			savedIdxsLngPalind[0] = currentIdxsLngPalind[0];
			savedIdxsLngPalind[1] = currentIdxsLngPalind[1];
		}

	}
}
