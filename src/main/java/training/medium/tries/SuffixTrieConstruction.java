package training.medium.tries;

import training.util.classes.SuffixTrie;

/**
 * Creates a trie (tree where every node stores a letter), for every suffix (we start taking the whole string
 * as the first suffix, them move forward one character and this is the new suffix and continue doing until we
 * end in the last character of the original string) we have in the original string.
 * 
 * This data structure is useful for string searching
 * 
 * @author JoseAlejandro
 *
 */
public class SuffixTrieConstruction {

	public static void main(String[] args) {
		String text = "babc";
		
		SuffixTrie suffixTrie = new SuffixTrie();
		
		suffixTrie.addStringToTrie(text);
		
		System.out.println(suffixTrie.contains("abc"));
		System.out.println(suffixTrie.contains("bab"));
		

	}
}
