package training.medium.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of String, find all the strings that are anagrams (string who
 * has the same letter but in different order) and create a sub array with all
 * the same anagram strings.
 * 
 * @author JoseAlejandro
 *
 */
public class GroupAnagrams {

	public static void main(String[] args) {
		String[] array = new String[] { "yo", "act", "flop", "tac", "cat", "oy", "olfp" };
		System.out.print(getGroupsOfAnagrams2(array));
	}

	/**
	 * for each word, we sort their letter and then sort again the whole array
	 * according to natural string order. We create two new arrays, one with the
	 * word sorted and another with the index of the words (this array is sorted
	 * using the word, but what we really store is the indexes of the words in the
	 * word sorted). Then we just traverse the word sorted array and create a
	 * subgroup for every distinct word.
	 * 
	 * Complexity
	 * 
	 * Time O( N * W Log(W) + W * N Log(N)) => We sort each word of W length of an
	 * array of length N, then we sort the N array to get the indexes ordered
	 * regarding the words natural order (which implied comparing each W length
	 * word), finally we need to traverse the array again of length N (this part is
	 * discarted from the complexity).
	 * 
	 * 
	 * Space O(NW) => we are storing the same array twice (one for the cloning) and
	 * other for the group of anagrams. so it would be 2NW.
	 * 
	 * 
	 * @param array
	 * @return
	 */
	public static List<List<String>> getGroupsOfAnagrams(String[] array) {

		List<List<String>> anagramGroups = new ArrayList<>();

		if (array != null && array.length > 0) {

			String[] arrayCopy = array.clone();
			Integer[] arrayOrigIndexes = new Integer[array.length];

			/*
			 * take each word, and order alphabetically their letters and stored back to the
			 * array. At the same time, store in arrayOrigIndexes, the current index.
			 */
			for (int index = 0; index < arrayCopy.length; index++) {
				// sorting is O(n log(n)) time complexity
				arrayCopy[index] = arrayCopy[index].chars().sorted().mapToObj(in -> String.valueOf((char) in))
						.collect(Collectors.joining());
				arrayOrigIndexes[index] = index;
			}

			/*
			 * sort arrayOrigIndexes based in the content of arrayCopy. This way, we
			 * logically sorted alphabetically arrayCopy. sorting is O(n log(n)) time
			 * complexity
			 */
			Arrays.sort(arrayOrigIndexes, (x, y) -> arrayCopy[x].compareTo(arrayCopy[y]));

			String currentAnagram = arrayCopy[arrayOrigIndexes[0]];
			List<String> anagramGroup = new ArrayList<>();

			for (int anagramIndex = 0; anagramIndex < arrayOrigIndexes.length; anagramIndex++) {

				int origPosition = arrayOrigIndexes[anagramIndex];

				if (!currentAnagram.equals(arrayCopy[origPosition])) {
					anagramGroups.add(anagramGroup);
					currentAnagram = arrayCopy[origPosition];
					anagramGroup = new ArrayList<>();
				}
				anagramGroup.add(array[arrayOrigIndexes[anagramIndex]]);

				if (anagramIndex + 1 == arrayOrigIndexes.length) {
					anagramGroups.add(anagramGroup);
				}
			}

		}

		return anagramGroups;

	}

	/**
	 *  We just traverse the array and for each word, sort it and check if the map has already that anagram, 
	 *  if not, we create a new list and add the original word corresponding to this anagram. if the anagram 
	 *  already exist, we add the original word to the corresponding anagram list.
	 * 
	 * Complexity
	 * 
	 * Time O( N * W Log(W)) => We traverse the array of length N and sort every word of W length of an array of length
	 * N and store the value on the map.
	 * 
	 * 
	 * Space O(NW) => we are creating a list of list, which has all the original words of the N array.
	 * 
	 * 
	 * @param array
	 * @return
	 */
	public static List<List<String>> getGroupsOfAnagrams2(String[] array) {

		List<List<String>> anagramGroups = new ArrayList<>();

		if (array != null && array.length > 0) {		
			Map<String, List<String>> anagramGroupsMap = new HashMap<>();

			/*
			 * take each word, and order alphabetically their letters and stored back to the
			 * array. At the same time, store in arrayOrigIndexes, the current index.
			 */
			for (int index = 0; index < array.length; index++) {
				// sorting is O(n log(n)) time complexity
				String anagram = array[index].chars().sorted().mapToObj(in -> String.valueOf((char) in))
						.collect(Collectors.joining());

				List<String> anagramGroup = anagramGroupsMap.get(anagram);

				if (anagramGroup == null) {
					anagramGroup = new ArrayList<>();
					anagramGroupsMap.put(anagram, anagramGroup);
				}
				anagramGroup.add(array[index]);

			}

			anagramGroups = anagramGroupsMap.values().stream().collect(Collectors.toList());
		}

		return anagramGroups;

	}

}
