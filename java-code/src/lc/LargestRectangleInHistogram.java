package lc;

import java.util.LinkedList;

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

    // brute force
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int maxArea = 0;
        for (int i=0; i<heights.length; i++) {
            int minHeight = heights[i];
            for (int j=i; j>=0; j--) {
                minHeight = Math.min(heights[j], minHeight);
                maxArea = Math.max(minHeight * (i-j+1), maxArea);
            }
        }

        return maxArea;
    }

    // better
    public static int largestRectangleArea_2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int maxArea = 0;
        for (int i=0; i<heights.length; i++) {
            int l = i-1;
            while (l>=0 && heights[l] >= heights[i])  {
                l--;
            }
            int r = i+1;
            while (r < heights.length && heights[r] >= heights[i])  {
                r++;
            }
            maxArea = Math.max(maxArea, heights[i] * (r-l-1));
        }

        return maxArea;
    }

    // stack
    public static int largestRectangleArea_3(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int maxArea = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i=0; i<heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int idx = stack.pop();
                if (stack.isEmpty()) { // 说明到目前为止所有元素（当前下标元素除外）都比出栈元素高度要大（否则栈中肯定还有元素），所以矩阵面积就是高度乘以当前下标i
                    maxArea = Math.max(maxArea, i*heights[idx]);
                } else {
                    maxArea = Math.max(maxArea, (i-stack.peek()-1)*heights[idx]); //之所以i-(peek+1)而不是i-idx，是因为peek和idx之间可能有比他们都高的bar
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            if (stack.isEmpty()) { // 说明到目前为止所有元素（当前下标元素除外）都比出栈元素高度要大（否则栈中肯定还有元素），所以矩阵面积就是高度乘以当前下标i
                maxArea = Math.max(maxArea, heights.length*heights[idx]);
            } else {
                maxArea = Math.max(maxArea, (heights.length-stack.peek()-1)*heights[idx]);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea_3(heights));
    }
}
