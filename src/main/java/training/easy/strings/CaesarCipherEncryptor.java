package training.easy.strings;

public class CaesarCipherEncryptor {

	final static int MAX_VALUE = 122;
	final static int MIN_VALUE = 65;
	final static int RANGE = MAX_VALUE - MIN_VALUE + 1;

	public static void main(String[] args) {
		String plainText = "yuca";

		System.out.println(plainText);
		String cipherText = cipher(plainText, 1045664343);
		System.out.println(cipherText);
		System.out.println(decipher(cipherText, 1045664343));

	}

	/**
	 * Complexity Time O(n)
	 *            Space O(1)
	 * @param plainText
	 * @param offset
	 * @return
	 */
	public static String cipher(String plainText, int offset) {
		char[] letters = plainText.toCharArray();

		for (int letter = 0; letter < letters.length; letter++) {

			int homologate = letters[letter] - MIN_VALUE;
			int homologatePlusOffSet = homologate + offset;
			int noShiftFrom65 = homologatePlusOffSet % RANGE;
			int no65PlusNoShift = noShiftFrom65 + MIN_VALUE;

			letters[letter] = (char) no65PlusNoShift;
		}

		return new String(letters);
	}

	public static String decipher(String cipherText, int offset) {
		char[] letters = cipherText.toCharArray();

		for (int letter = 0; letter < letters.length; letter++) {
			int homologate = letters[letter] - MIN_VALUE;
			int invertScale = RANGE  - homologate;
			int homologatePlusOffSet = invertScale + offset;
			int noShiftFrom65 = homologatePlusOffSet % RANGE;
			int invertShift = MAX_VALUE + 1 - noShiftFrom65; 

			letters[letter] = (char) invertShift;
			
		}

		return new String(letters);
	}

}
