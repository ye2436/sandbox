/**
 * #84. Largest Rectangle in Histogram
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 */
public class LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int maxArea = 0;
        for (int i=0; i<heights.length; i++) {
            int minHeight = heights[i];
            for (int j=i; j>=0; j--) {

                maxArea = Math.max(minHeight * (j-i+1), maxArea);
                minHeight = Math.min(heights[j], minHeight);
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }
}
