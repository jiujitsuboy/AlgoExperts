package training.util.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Create a tree build upon every suffix of the string passed to his
 * addStringToTrie method
 * 
 * @author JoseAlejandro
 *
 */
public class SuffixTrie {
	private Map<Character, TrieNode> root;

	public SuffixTrie() {
		root = new HashMap<Character, SuffixTrie.TrieNode>();
	}

	/**
	 * For every suffix substring (starting with the whole string and moving one
	 * character until we reach the end of the String) we stored in a structure of
	 * hashmap that every element has as a value another hashmap
	 * 
	 * Complexity
	 * 
	 * Time O(n^2)=> for every letter in the string, we iterate the whole array.
	 * 
	 * Space O(n^2)=> we store every suffix, so we end with n^2 substring
	 * 
	 * @param text
	 */
	public void addStringToTrie(String text) {

		Map<Character, TrieNode> currentNode = null;
		String subString = null;

		for (int suffix = 0; suffix < text.length(); suffix++) {

			currentNode = root;
			subString = text.substring(suffix);

			for (char letter : subString.toCharArray()) {

				TrieNode trieNode = currentNode.get(letter);

				if (trieNode == null) {
					trieNode = new TrieNode();
					currentNode.put(letter, trieNode);
				}
				currentNode = trieNode.getChildren();
			}
			currentNode.put('*', null);
		}

	}

	/**
	 * we search in the data structure, every letter starting from the first letter
	 * and if the letter exist, we continue with the next one, until we find every
	 * letter and finally an asterisk.
	 * 
	 * Complexity
	 * 
	 * Time O(n)=> we traverse the whole text (letter by letter)
	 * 
	 * Space O(1)=> we don't create any auxiliary structure, we just work with the
	 * current data structure
	 * 
	 * @param text
	 * @return
	 */
	public boolean contains(String text) {

		Map<Character, TrieNode> currentNode = root;

		for (char letter : text.toCharArray()) {

			TrieNode trieNode = currentNode.get(letter);

			if (trieNode == null) {
				break;
			}
			
			currentNode = trieNode.getChildren();
		}

		return currentNode.containsKey('*');
	}

	private static class TrieNode {
		private Map<Character, TrieNode> children;

		public TrieNode() {
			children = new HashMap<Character, SuffixTrie.TrieNode>();
		}

		public Map<Character, TrieNode> getChildren() {
			return children;
		}

	}
}
