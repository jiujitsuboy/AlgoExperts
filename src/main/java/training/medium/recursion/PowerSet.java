package training.medium.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of elements, we find, all the possible set conformed by the elements of the original array
 * without taking care of permutations, just a combination of unique elements, starting with a empty set, going
 * up with every element of the array, and then by combining each element to each other. 
 * 
 * @author JoseAlejandro
 *
 */
public class PowerSet {

	public static void main(String[] args) {

		Integer[] array = new Integer[] { 1, 2, 3, 4 };
//		List<Integer[]> powerset = calculateIterative(array);
		List<Integer[]> powerset = calculateRecursive(array);
		printSet(powerset);
	}

	private static void printSet(List<Integer[]> powerset) {
		for (Integer[] set : powerset) {
			System.out.println(Arrays.toString(set));
		}
	}

	/**
	 * we create a list of powerset with an empty array. Then for each element of
	 * the array parameter pass to the function, we take one element each time and
	 * add it to every array that is present in the powerset list
	 * 
	 * Complexity
	 * 
	 * Time O(n*2^n) => for each element in the array, we double the number of sets,
	 * so that why 2^n. And each operation, implies to copy an array of n/2 length
	 * on average. so we get rid of the /2 and take n.
	 * 
	 * Space O(n*2^n) => we store 2^n sets of n/2 length on average so we get rid of
	 * the /2 and take n.
	 * 
	 * 
	 * @param array
	 * @return
	 */
	public static List<Integer[]> calculateIterative(Integer[] array) {

		List<Integer[]> powerset = new ArrayList<Integer[]>();

		powerset.add(new Integer[] {});

		for (int index = 0; index < array.length; index++) {
			Integer element = array[index];

			int powersetLength = powerset.size();

			for (int pos = 0; pos < powersetLength; pos++) {
				Integer[] set = powerset.get(pos);
				Integer[] newSet = Arrays.copyOf(set, set.length + 1);
				newSet[newSet.length - 1] = element;
				powerset.add(newSet);
			}

		}

		return powerset;
	}

	public static List<Integer[]> calculateRecursive(Integer[] array) {

		List<Integer[]> powerset = new ArrayList<Integer[]>();

		calculateRecursiveAux(array, array.length - 1, powerset);

		return powerset;
	}

	/**
	 * Same approach that the iterative, but instead of having a for loop to iterate over all the elements of
	 * the parameter array of the method, we initialize the index variable to the last element of the array
	 * and call again the method, just decreasing the index by one, until we hit -1 (where we create the empty
	 * set) and end the recursion, then on every call, we iterate using the current index against the set 
	 * available on the powerset list.
	 * 
	 * Complexity
	 * 
	 * Time O(n*2^n) => for each element in the array, we double the number of sets,
	 * so that why 2^n. And each operation, implies to copy an array of n/2 length
	 * on average. so we get rid of the /2 and take n.
	 * 
	 * Space O(n*2^n) => we store 2^n sets of n/2 length on average so we get rid of
	 * the /2 and take n.
	 * 
	 * @param array
	 * @param index
	 * @param powerset
	 */
	public static void calculateRecursiveAux(Integer[] array, int index, List<Integer[]> powerset) {

		if (index > -1) {

			Integer element = array[index];

			calculateRecursiveAux(array, index - 1, powerset);

			int powersetLength = powerset.size();

			for (int pos = 0; pos < powersetLength; pos++) {
				Integer[] set = powerset.get(pos);
				Integer[] newSet = Arrays.copyOf(set, set.length + 1);
				newSet[newSet.length - 1] = element;
				powerset.add(newSet);
			}

		} else {
			powerset.add(new Integer[] {});
		}

	}

}
