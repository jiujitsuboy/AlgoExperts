package training.easy.searching;

public class BinarySearch {

	public static void main(String[] args) {

		Integer[] nums = new Integer[] { 1, 2, 5, 7, 8, 9, 10, 13, 16, 19, 33, 67, 900 };

//		Search<Integer> search = new Search<Integer>();
//		Integer resp1 = search.searchElementIterative(nums, 9);
//		Integer resp2 = search.searchElementIterative(nums, 90);
//
//		System.out.println(resp1);
//		System.out.println(resp2);
//
//		Integer resp3 = search.searchElementRecursive(nums, 9, 0, nums.length);
//		Integer resp4 = search.searchElementRecursive(nums, 90, 0, nums.length);
//
//		System.out.println(resp3);
//		System.out.println(resp4);
				
		Integer resp1 = searchElementIterative(nums, 9);
		Integer resp2 = searchElementIterative(nums, 90);

		System.out.println(resp1);
		System.out.println(resp2);

		Integer resp3 = searchElementRecursive(nums, 9, 0, nums.length);
		Integer resp4 = searchElementRecursive(nums, 90, 0, nums.length);

		System.out.println(resp3);
		System.out.println(resp4);

	}

	//private static class Search<T extends Comparable<T>> {

		// O(log(n)) Time
		// O(1) Space
		public static <T extends Comparable<T>> T searchElementIterative(T[] list, T value) {

			int initialIndex = 0;
			int lastIndex = list.length;
			int compareIndex = 0;

			T response = null;

			while (initialIndex <= lastIndex) {
				compareIndex = (lastIndex + initialIndex) / 2;

				if (value.compareTo(list[compareIndex]) == 0) {
					response = list[compareIndex];
					break;
				}
				// lower
				else if (value.compareTo(list[compareIndex]) < 0) {
					lastIndex = compareIndex - 1;
				} else {
					initialIndex = compareIndex + 1;
				}

			}

			return response;
		}

		//O(log(n)) Time
		//O(log(n)) Space
		public static <T extends Comparable<T>> T searchElementRecursive(T[] list, T value, int initialIndex, int lastIndex) {

			int compareIndex = 0;
			T response = null;

			if (initialIndex <= lastIndex) {

				compareIndex = (lastIndex + initialIndex) / 2;

				if (value.compareTo(list[compareIndex]) == 0) {
					response = list[compareIndex];
				}
				// lower
				else if (value.compareTo(list[compareIndex]) < 0) {
					response = searchElementRecursive(list, value, initialIndex, compareIndex - 1);

				} else {
					response = searchElementRecursive(list, value, compareIndex + 1, lastIndex);
				}
			}

			return response;
		}
//	}

}
