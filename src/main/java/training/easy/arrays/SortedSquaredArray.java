package training.easy.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a function that takes in a non-empty array of integers that are sorted in ascending order
 * and returns a new array of the same length with the squares of the original integers also sorted
 * in ascending order.
 * <p>
 * Sample Input array  = [1, 2, 3, 5, 6, 8, 9]
 * <p>
 * Sample Output [1, 4, 9, 25, 36, 64, 81]
 */
public class SortedSquaredArray {

  public static void main(String[] args) {
//    int[] array= {-4,-3,2,5};
//    int[] array= {-10,-5,0,5, 6,10};
//    int[] array= {-50, -13, -2, -1, 0, 0, 1, 1, 2, 3, 19, 20};
//    int[] array={1, 2, 3, 5, 6, 8, 9};
    int[] array={-3, -2, -1};
//    System.out.println(Arrays.toString(mySolution(array)));
    System.out.println(Arrays.toString(sortedSquaredArray(array)));
  }


  /**
   * AlgoExpert solution
   *
   * Time Complexity: O(n), traverse the array once
   * Space Complexity: O(n) create a second array with all the content of the first array
   */
  public static int[] sortedSquaredArray(int[] array){
    int leftPointer = 0;
    int rightPointer = array.length -1;
    int[] resp = new int[array.length];
    int index = resp.length-1;

    while(leftPointer<=rightPointer){
      int leftValue = Math.abs(array[leftPointer]);
      int rightValue = Math.abs(array[rightPointer]);
      int maxValue = 0;

      if(leftValue<rightValue){
        rightPointer--;
        maxValue = rightValue * rightValue;
      }
      else
      {
        leftPointer++;
        maxValue = leftValue * leftValue;
      }

      resp[index--] = maxValue;
    }
    return resp;
  }

  /**
   * Not 100% correct
   *
   * Time Complexity: O(n), traverse the array once
   * Space Complexity: O(n) create a second array with all the content of the first array
   */
  public static int[] mySolution(int[] array) {

    List<Integer> listResp = new ArrayList();

    if(array.length==0){
      return convertListToArray(listResp);
    }

    int minValue = array[0] * array[0];
    int maxValue = minValue;

    listResp.add(minValue);

    for(int index=1;index<array.length;index++){

      int square = array[index] * array[index];

      if(square<=minValue){
        minValue = square;
        listResp.add(0, minValue);
      }
      else if(square>=maxValue) {
        maxValue = square;
        listResp.add(maxValue);
      }
      else {
        listResp.add(listResp.size()-1, square);
      }
    }


    return convertListToArray(listResp);
  }

  /**
   * Used to convert a list of Integers into a int[]
   * @param numbersList List of Integers
   * @return int[]
   */
  public static int[] convertListToArray(List<Integer> numbersList){
    int[] resp = new int[numbersList.size()];

    for(int index=0;index<numbersList.size();index++){
      resp[index]=numbersList.get(index);
    }

    return resp;
  }


}
