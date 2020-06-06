package training.easy.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculate the sum of every sub array contained in the main array, where every level of nesting is multiply
 *  the sum result with the number of levels of nested.
 *  
 * [1,2,[4,-5],[1,[4,3]] 
 * 
 * [1,2,[-1],[1,[7]]
 * 
 * [1,2,(2*[-1]),2*([1,(3*[7]))]
 * 
 * [1,2,-2,2*([1,(21))]
 * 
 * [1,2,-2,2*(21)]
 * 
 * [1,2,-2,42]
 * 
 * resp: 43
 * 
 * @author JoseAlejandro
 *
 */
public class ProductSum {

	//O(n) Time
	//O(d) Space (d = deep)
	@SuppressWarnings("unchecked")
	public static double calculateSumRecursive(List<Object> items, int level) {

		double sum = 0;

		for (Object item : items) {
			if (item instanceof List) {
				sum += calculateSumRecursive((List<Object>) item, level + 1);
			} else {
				sum += Double.parseDouble(item.toString());
			}
		}

		return sum * level;
	}

	public static void main(String[] args) {

		List<Object> items1 = new ArrayList<Object>();
		List<Object> item2a = new ArrayList<Object>();
		List<Object> item2b = new ArrayList<Object>();
		List<Object> item3 = new ArrayList<Object>();

		item3.add(-13);
		item3.add(8);

		item2a.add(7);
		item2a.add(-1);

		item2b.add(6);
		item2b.add(item3);
		item2b.add(4);

		items1.add(5);
		items1.add(2);
		items1.add(item2a);
		items1.add(3);
		items1.add(item2b);

		double sum = calculateSumRecursive(items1, 1);

		System.out.println(sum);

	}

}
