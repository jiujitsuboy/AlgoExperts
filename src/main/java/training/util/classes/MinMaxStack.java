package training.util.classes;

/**
 * A stack that also store the minimum and max related to the element in the top
 * of the stack
 * 
 * @author JoseAlejandro
 *
 * @param <T>
 */
public class MinMaxStack<T extends Comparable<T>> {
	DoubleLinkedList<T> values;
	DoubleLinkedList<MinMax<T>> minMax;

	public MinMaxStack() {
		values = new DoubleLinkedList<T>();
		minMax = new DoubleLinkedList<MinMax<T>>();
	}

	/**
	 * Get the top element of the stack
	 * 
	 * Complexity
	 * 
	 * Time O(1)
	 * 
	 * Space O(1)
	 * 
	 * @return
	 */
	public T peek() {
		return values.getLast();
	}

	/**
	 * Get the top element of the stack and remove it
	 * 
	 * Complexity
	 * 
	 * Time O(1)=> just remove the last node of the list, which is the point by the tail node pointer
	 * 
	 * Space O(1)
	 * 
	 * @return
	 */
	public T pop() {		
		minMax.removeLast();
		return values.removeLast();
	}

	/**
	 * Put a new value in the top, and calculate the new min and max for this value and store in the auxiliary list
	 * 
	 * Time O(1)=> insert the new element and calculate the min max. 
	 * 
	 * Space O(n) => the auxiliary list is 2n length
	 * 
	 * @param value
	 */
	public void push(T value) {

		values.addElement(value);

		MinMax<T> minMaxValues = minMax.getLast();

		T minValue = null;
		T maxValue = null;

		if (minMaxValues == null) {
			minValue = value;
			maxValue = value;
		} else {

			minValue = getMin(value, minMaxValues.getMinValue());
			maxValue = getMax(value, minMaxValues.getMaxValue());

		}

		minMax.addElement(new MinMax<T>(minValue, maxValue));
	}

	/**
	 * Get the minimum value for the top value in the stack. This value is stored in the auxiliary list
	 * 
	 * Complexity
	 * 
	 * Time O(1)=> get the last node of the auxiliary list 
	 * 
	 * Space O(1) => No extra values stored
	 * 
	 * @return
	 */
	public T getMin() {
		MinMax<T> minMaxValues = minMax.getLast();
		return (minMaxValues != null) ? minMaxValues.getMinValue() : null;
	}

	/**
	 * Get the max value for the top value in the stack. This value is stored in the auxiliary list
	 * 
	 * Complexity
	 * 
	 * Time O(1)=> get the last node of the auxiliary list 
	 * 
	 * Space O(1) => No extra values stored
	 * 
	 * @return
	 */
	public T getMax() {
		MinMax<T> minMaxValues = minMax.getLast();
		return (minMaxValues != null) ? minMaxValues.getMaxValue() : null;
	}

	@Override
	public String toString() {
		return String.format("%s %s", minMax.toString(), values.toString());
	}

	private T getMin(T value1, T value2) {
		T minValue = value2;

		if (value1.compareTo(value2) < 0) {
			minValue = value1;
		}

		return minValue;
	}

	private T getMax(T value1, T value2) {
		T maxValue = value2;

		if (value1.compareTo(value2) > 0) {
			maxValue = value1;
		}

		return maxValue;
	}

	/**
	 * Auxiliary object to store minimum and max value
	 * 
	 * @author JoseAlejandro
	 *
	 * @param <T>
	 */
	static class MinMax<T> {
		T minValue;
		T maxValue;

		public MinMax(T minValue, T maxValue) {
			this.minValue = minValue;
			this.maxValue = maxValue;
		}

		public T getMinValue() {
			return minValue;
		}

		public T getMaxValue() {
			return maxValue;
		}

		@Override
		public String toString() {

			String minValue = (this.minValue != null) ? this.minValue.toString() : null;
			String maxValue = (this.maxValue != null) ? this.maxValue.toString() : null;

			return String.format("min: %s, max: %s", minValue, maxValue);
		}
	}
}
