package training.medium.heaps;

import java.util.ArrayList;
import java.util.List;

import training.util.classes.MinHeap;

/**
 * Using a Min Heap to store the minimum value in the top of the array
 * @author JoseAlejandro
 *
 */
public class MinHeapConstruction {

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(31);
		numbers.add(17);
		numbers.add(12);
		numbers.add(18);
		numbers.add(9);
		numbers.add(44);
		numbers.add(102);
		numbers.add(30);
		numbers.add(23);
		
		MinHeap<Integer> minHeap = new MinHeap<Integer>(numbers);
		System.out.println(minHeap);
		
		System.out.println("Insert 1");
		minHeap.insert(1);
		System.out.println(minHeap);
		
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("Insert 5");
		minHeap.insert(5);
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("Insert 2");
		minHeap.insert(2);
		System.out.println(minHeap);
		System.out.println("remove minimun element: " + minHeap.remove());
		System.out.println(minHeap);
		System.out.println("Insert 100");
		minHeap.insert(100);
		System.out.println(minHeap);
		System.out.println("Insert 4");
		minHeap.insert(4);
		System.out.println(minHeap);
		System.out.println("Insert 3");
		minHeap.insert(3);
		System.out.println(minHeap);
		System.out.println("Insert 2");
		minHeap.insert(2);
		System.out.println(minHeap);
		System.out.println("Insert 1");
		minHeap.insert(1);
		System.out.println(minHeap);
	}	

}
