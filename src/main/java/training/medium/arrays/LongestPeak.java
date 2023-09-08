package training.medium.arrays;

/**
 * Calculate de longest peak on an array (peak is a number which both sides are
 * lower than him, and longest refers to the number of elements that conform
 * these sides, until you reach a number greater of a edge)
 *
 * @author JoseAlejandro
 */
public class LongestPeak {

    public static void main(String[] args) {
//		int[] array = new int[] { 1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3 };
        int[] array = new int[]{1, 2, 3, 4, 5, 1};
        System.out.println(longestPeak(array));
    }

    /**
     * Iterate over the array starting from position 1 until lenght-2. At every iteration, it evaluate if the
     * current number is a peak (left and right values are lower than him). If so, it scan backward and forward
     * counting the length of this peak, and storing it if is the greater than the previous found.
     * <p>
     * Complexity: Time 0(n)
     * Space O(1)
     *
     * @param array
     * @return
     */
    public static int longestPeak(int[] array) {

        int longPeak = 0;
        int currentIndex = 1;
        int currentPeakLength = 0;
        int leftPeak = 0;
        int rightPeak = 0;

        while (currentIndex < array.length - 1) {
            if (isPeak(currentIndex, array)) {

                leftPeak = findLeftPeakLength(currentIndex, array);
                rightPeak = findRightPeakLength(currentIndex, array);

                currentIndex += rightPeak;

                currentPeakLength = leftPeak + rightPeak + 1;

                if (currentPeakLength > longPeak) {
                    longPeak = currentPeakLength;
                }
            }
            currentIndex++;
        }

        return longPeak;
    }

    private static boolean isPeak(int index, int[] array) {
        return array[index - 1] < array[index] && array[index] > array[index + 1];
    }

    private static int findLeftPeakLength(int index, int[] array) {

        int totalLength = 1;

        for (int pos = index - 2; pos >= 0; pos--) {
            if (array[pos] >= array[pos + 1]) {
                break;
            }
            totalLength++;
        }

        return totalLength;
    }

    private static int findRightPeakLength(int index, int[] array) {

        int totalLength = 1;

        for (int pos = index + 2; pos < array.length; pos++) {
            if (array[pos - 1] <= array[pos]) {
                break;
            }
            totalLength++;
        }

        return totalLength;
    }

}
