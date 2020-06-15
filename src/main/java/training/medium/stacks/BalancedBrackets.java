package training.medium.stacks;

import java.util.Stack;

/**
 * Validate if the opening brackets match with their corresponding closing
 * brackets, in the correct order
 * 
 * @author JoseAlejandro
 *
 */
public class BalancedBrackets {

	public static void main(String[] args) {
		String brackets = "({([])}";
		System.out.println(matchBrackets(brackets));
	}

	/**
	 * We iterate over the String and every opening bracket is store in the stack, and when we find a closing
	 * bracket, we pop the last opening bracket from the stack and see if both correspond.
	 * 
	 * Complexity
	 * 
	 * Time O(n)=> we need to traverse all the String
	 * 
	 * Space O(n)=> we store in the stack on average N/2 and in the worst case we store the whole string "{{{{("
	 * 
	 * 
	 * @param brackets
	 * @return
	 */
	public static boolean matchBrackets(String brackets) {

		Stack<Character> stack = new Stack<Character>();

		for (int index = 0; index < brackets.length(); index++) {
			
			char charac = brackets.charAt(index);
			
			if ("{([".contains(String.valueOf(charac))) {

				stack.push(charac);

			} else {

				if (stack.isEmpty()) {
					return false;
				}

				Character openBracket = stack.pop();
				boolean match = false;

				switch (charac) {
				case '}':
					match = openBracket == '{';
					break;
				case ')':
					match = openBracket == '(';
					break;
				case ']':
					match = openBracket == '[';
					break;
				}

				if (!match) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}
}
