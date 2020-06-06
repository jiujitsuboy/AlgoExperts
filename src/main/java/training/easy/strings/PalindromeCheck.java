package training.easy.strings;

/**
 * Verify is a string is a palindrome (mean the same if read forward or backwards)
 * @author JoseAlejandro
 *
 */
public class PalindromeCheck {

	public static void main(String[] args) {

//		System.out.println(isPalindrome1("Lindsay"));
//		System.out.println(isPalindrome1("LindniL"));

//		String text = "LinddniL";
//		System.out.println(isPalindromeRecursive(text, 0, text.length() - 1));
//		String text2 = "LindniL";
//		System.out.println(isPalindromeRecursive(text2, 0, text2.length() - 1));
		
		String text2 = "L";
		System.out.println(isPalindromeRecursive(text2, 0, text2.length() - 1));
		
		
//		String text3 = "LinddniL";
//		System.out.println(isPalindromeWithPointer(text3));
//		String text4 = "LindniL";
//		System.out.println(isPalindromeWithPointer(text4));		

	}

	/**
	 * Complexity 
	 * 
	 * Time 0(n^2) -> every substring should be traverse in the
	 * resulting string creation.
	 * 
	 * Space O(n)
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isPalindrome1(String text) {

		String tempText = "";		

		for (int charac = text.length() - 1; charac >= 0; charac--) {
			tempText += text.charAt(charac);			
		}

		return tempText.equals(text);		

	}

	/**
	 * Complexity Time 0(n) Space O(n)
	 * 
	 * If StringBuilder where not used, instead a String is built for every
	 * character iteration, the Complexity will be Time 0(n^2) -> every substring
	 * should be traversed in the resulting string creation Space O(n)
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isPalindrome2(String text) {

		StringBuilder backText = new StringBuilder(text.length());

		for (int charac = text.length() - 1; charac >= 0; charac--) {
			backText.append(text.charAt(charac));
		}

		return backText.toString().equals(text);

	}

	/**
	 * Complexity Time 0(n) 
	 * 			  Space O(n)
	 * 
	 * This function is not tail recursive. To  make it tail recursive, the last thing the function should do
	 * is calling the function again
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isPalindromeRecursive(String text, int initialIndex, int lastIndex) {

		boolean isEqual = false;

		System.out.println(String.format("initialIndex:%d  lastIndex:%d", initialIndex, lastIndex));

		if (initialIndex <= lastIndex || isInsideBounds(text, initialIndex) || isInsideBounds(text, lastIndex)) {

			isEqual = text.charAt(initialIndex) == text.charAt(lastIndex);

			if (isEqual && (lastIndex-initialIndex) > 1) {
				isEqual &= isPalindromeRecursive(text, ++initialIndex, --lastIndex);
			}
		}

		return isEqual;

	}

	private static boolean isInsideBounds(String text, int index) {
		return index >= 0 && index < text.length();
	}
	
	/**
	 * Complexity Time O(n) 
	 * 			  Space O(1)
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isPalindromeWithPointer(String text) {

		boolean isEqual = false;
		int mirrorPos = text.length()-1;

		for (int pos = 0; pos < text.length()/2; pos++) {
			if(text.charAt(pos)==text.charAt(mirrorPos-pos)) {
				isEqual = true;
			}
			else
			{
				break;
			}
		}

		return isEqual;

	}

}
