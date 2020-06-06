package training.easy.sorting;

import java.util.Arrays;

/**
 * Sorting algorithm, which pass N times on the collection, comparing the N-n to N-n+1 element and
 * swapping they if N-n is lower than N-n+1.So on any loop iteration, the N-n value is compared to all the
 * N-n+1 elements previous to him and swaps them if N-n is lower than any N-n+1. If any comparation that 
 * shows N-n is greater,  the next comparation are not done, because N-n is greater.
 * @author JoseAlejandro
 *
 */
public class InsertSort {

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 10, 5, 23, 6, 2, 1,0};
		sort(arr);
		System.out.println(Arrays.toString(arr));

	}
	
	/***
	 * Sorting method (implemented with a While Loop and a inner For Loop),which
	 * complexity is: O(n^2) Time 
	 * 				  O(1) Space
	 * 
	 * @param <T>   type of the elements of the array
	 * @param array array to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] array) {
		
		for(int pos=1;pos<array.length;pos++) {
			for(int prev=pos;prev>0;prev--) {
				if(array[prev].compareTo(array[prev-1])<0) {
					swap(prev,prev-1,array);
				}
				else {					
					break;
				}
			}
		}
	}
	
	private static <T> void swap(int minorIndex,int mayorIndex ,T[] array) {
		T temp = array[minorIndex];
		array[minorIndex] = array[mayorIndex];
		array[mayorIndex] = temp;
	}

}
