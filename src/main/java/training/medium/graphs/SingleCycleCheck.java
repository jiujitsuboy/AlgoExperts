package training.medium.graphs;

/**
 * Validate if the graph has one cycle or more than one
 * @author JoseAlejandro
 *
 */
public class SingleCycleCheck {

	public static void main(String[] args) {
		
		int[] array = new int[] {2,3,1,-4,-4,2};
		
		System.out.print(isSingleCycleGraph(array));
	}

	/**
	 * the value of every element of the array indicates the number of index to move forward or backward to find
	 * the next vertex on the graph. If the graph has only one cycle, it should be a close graph. 
	 * 
	 * Complexity: 
	 * 
	 * Time O(n) =>  we visited all the vertex or elements of the array.
	 * Space O(1) => we only use two variables for this validation
	 * 
	 * @param array
	 * @return
	 */
	public static boolean isSingleCycleGraph(int[] array) {		

		int startingIndex = (int) (Math.random() * (array.length - 1));
		int currentIndex = startingIndex;
		int nroVertexVisited = 0;

		while (nroVertexVisited < array.length) {						
			if (nroVertexVisited>0 && currentIndex == startingIndex) {
				return false;
			}
			currentIndex = getNextIndex(currentIndex, array);
			nroVertexVisited++;
		}

		return currentIndex == startingIndex;
	}

	private static int getNextIndex(int currentIndex, int[] array) {
		int nextIndex = (currentIndex + array[currentIndex]) % array.length;
		if (nextIndex < 0) {
			nextIndex += array.length;
		}

		return nextIndex;
	}
}
