package training.medium.stacks;

import training.util.classes.MinMaxStack;

public class MinMaxStackConstruction {

	public static void main(String[] args) {
		MinMaxStack<Integer> minMaxStack = new MinMaxStack<Integer>();
		
		minMaxStack.push(5);
		printMinMaxPeak(minMaxStack);
		minMaxStack.push(7);
		printMinMaxPeak(minMaxStack);
		minMaxStack.push(2);
		printMinMaxPeak(minMaxStack);
		System.out.println("Extracting top element: " +minMaxStack.pop());
		printMinMaxPeak(minMaxStack);

	}
	
	private static void printMinMaxPeak(MinMaxStack<Integer> minMaxStack) {
		System.out.println("Min Value: " + minMaxStack.getMin());
		System.out.println("Max Value: " + minMaxStack.getMax());
		System.out.println("Top Value: " + minMaxStack.peek());
		System.out.println();
	}

}
