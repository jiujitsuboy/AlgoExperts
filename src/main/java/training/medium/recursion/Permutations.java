package training.medium.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Generate all the possible permutation of the element of an array (without repetition)
 * 
 * @author JoseAlejandro
 *
 */
public class Permutations {

	public static void main(String[] args) {

		long start = 0;
		long end = 0;

		Integer[] array = new Integer[] { 1, 2, 3 };
		
		System.out.println(Integer.valueOf(1).compareTo(Integer.valueOf(2)));
		System.out.println(Integer.valueOf(2).compareTo(Integer.valueOf(1)));

//		start = System.currentTimeMillis();
//		List<Integer[]> permutations1 = allPosiblePermutationsIterative(array);
//		end = System.currentTimeMillis();
//
//		System.out.println("Number of permutations: " + permutations1.size() + ", time taken: " + (end - start) / 100);
//		printPermutations(permutations2);

		start = System.currentTimeMillis();
		List<Integer[]> permutations2 = allPosiblePermutationsRecursive(array);
		end = System.currentTimeMillis();

		System.out.println("Number of permutations: " + permutations2.size() + ", time taken: " + (end - start) / 100);
		printPermutations(permutations2);

	}

	private static void printPermutations(List<Integer[]> permutations) {
		for (Integer[] permutation : permutations) {
			System.out.println(Arrays.toString(permutation));
		}
	}

	/**
	 * Iterative method, which start at the index 1 and make
	 * 
	 * 
	 * Complexity
	 * 
	 * Time: O(n!.n) => On every permutation, we goes n times, and because for 
	 *          any number we iterate n-1 times, we end having * n-1 times * n-2 times * n -m time, 
	 *          which is n!. So we have n! permutations, each permutation with n iterations. 
	 * 
	 * Space O(n!.n) => we create n! combinations of the array of length n.
	 * 
	 * 
	 * @param <T>
	 * @param array
	 * @return
	 */
	public static <T> List<T[]> allPosiblePermutationsIterative(T[] array) {
		List<T[]> permutations = new ArrayList<T[]>();

		if (array.length > 1) {

			int[] counts = new int[array.length];
			int indexCount = 1;

			while (true) {

				// every time we permute or operate over a index greater than 1, we need to
				// reset to 1, so
				// the permutation process start again with the lowest index in the array
				if (indexCount > 1) {
					indexCount = 1;
				}
				// Store the permutation
				permutations.add(array.clone());
				// undo permutation
				if (counts[indexCount] > 0) {
					swap(indexCount, indexCount - (counts[indexCount]), array);
				}

				// goes up to the next number in the array if the current, reach the number of
				// possible permutations for his position
				if (counts[indexCount] >= indexCount) {

					// if the current position spend all his possible permutations, we switch to the
					// next upper index which has remaining available permutations
					while (indexCount < array.length && counts[indexCount] >= indexCount) {
						counts[indexCount] = 0;
						indexCount++;

						// if the current position spend all his available permutations, we
						// swap the last permutation, to leave the array as it original state, which
						// guarantee that every next permutation is going to be over a different
						// position
						// and not repeat positions.
						if (indexCount < array.length && counts[indexCount] > 0) {
							swap(indexCount, indexCount - (counts[indexCount]), array);
						}
					}

					// when all the permutations available over the array are done
					if (indexCount >= array.length) {
						break;
					}

				}

				// the next available permutation over the current position
				swap(indexCount, indexCount - (1 + counts[indexCount]), array);
				// remaining permutations for the current position
				counts[indexCount]++;
			}
		} else if (array.length == 1) {
			permutations.add(array);
		}

		return permutations;
	}

	private static <T> void swap(int index1, int index2, T[] array) {
		T temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	public static <T> List<T[]> allPosiblePermutationsRecursive(T[] array) {
		int start = 0;
		List<T[]> permutations = new ArrayList<T[]>();
		return allPosiblePermutationsRecursiveAux(start, array, permutations);
	}

	/**
	 * On every recursion call we iterate from the start pointer to the end of the
	 * array and call the function by increasing by one the start pointer, until it
	 * reach the end of the array. at that point,we have a permutation which is
	 * stored in the permutation list. on every iteration we swap one element
	 * (firstable the next, then the next plus one, until we reach the end of the
	 * array), and after every permutation we undo the permutation, to guarantee
	 * that during the swap process, we permuted all the elements of the array.
	 * 
	 * Complexity
	 * 
	 * Time: O(n!.n) => On every permutation, we goes n times, and because for 
	 *          any number we iterate n-1 times, we end having * n-1 times * n-2 times * n -m time, 
	 *          which is n!. So we have n! permutations, each permutation with n iterations. 
	 * 
	 * Space O(n!.n) => we create n! combinations of the array of length n.
	 * 
	 * 
	 * @param <T>
	 * @param start
	 * @param array
	 * @param permutations
	 * @return
	 */
	public static <T> List<T[]> allPosiblePermutationsRecursiveAux(int start, T[] array, List<T[]> permutations) {

		if (start == array.length - 1) {
			permutations.add(array.clone());
		} else {
			for (int times = start; times < array.length; times++) {
				allPosiblePermutationsRecursiveAux(start + 1, array, permutations);
				// undo permutation, only if is not the first iteration
				if (times > start) {
					swap(start, times, array);
				}
				// if we didn't reach the last time, we make the permutation
				if (times + 1 < array.length) {
					swap(start, times + 1, array);
				}

			}
		}

		return permutations;
	}

}
