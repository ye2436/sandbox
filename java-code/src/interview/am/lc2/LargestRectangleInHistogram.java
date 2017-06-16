package interview.am.lc2;

import java.util.LinkedList;
import java.util.Stack;

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

    // Solution 1 - brute force. Use 2 pointers as left and right window, while go from left right, keep updating min between the 2 bars
    // area = minHeight * (r-l+1)
    // Time: O(n^2)
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

    // Solution 2 - Stack O(n)
    // What we are trying to find is, with each bar as height, what is the width? (how far could it extend to either end?)
    // That is, we want to find the first bar that is shorter than the current one, in both ends. width = r-l-1.
    // (l&r being the first index to the left & right with bar height smaller than the current bar.)
    // * Imagine a queue, | smaller1 | larger1 | current | larger2 | smaller2 |
    // Once we reached the right smaller (monotonically increasing order broken), we know we have reached the max width for larger1, current, and larger2
    // For larger 2, the window is (smaller2-current-1); for current, it's (smaller2-larger1-1),....
    // but what if larger1 > current, the width should be smaller2-smaller1-1.
    // * So here we want to have a data structure to indicate monotonically increasing height. Since we only compare when right side window is closed, we use stack.
    // We only store the bars with monotonically increasing order. When it's broken, we pop from stack until the order is restored.
    // The popped bars are the ones with both side windows complete, we can calculate their width by
    // idx of current smaller bar to add ---- idx of stack.peek()-1
    // * To initialize stack, we can add a -1 to indicate the left boundary
    // * When all bars are added, what's left in the stack are all in monotonically increasing order. These are the ones with the right most bar as the right window
    //   calculate their width by idx of last bar - idx of stack.pop()
    public int largestRectangleArea_stack(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i=0; i<heights.length; i++) {
            while (stack.peek()>=0 && heights[stack.peek()] >= heights[i]) {
                int height = heights[stack.pop()];
                maxArea = Math.max(maxArea, height * (i-stack.peek()-1)); // between [stack.peek+1, i-1]
            }
            stack.push(i);
        }

        // what's left in the stack are the bars with right side window extends to the end
        int lastIdx = heights.length-1;
        while (stack.peek()>=0) {
            int height = heights[stack.pop()];
            maxArea = Math.max(maxArea, height * (lastIdx - stack.peek()));// between[stack.peek+1, lastIdx]
        }
        return maxArea;
    }

    public static int largestRectangleArea_2(int[] heights) {
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
        System.out.println(largestRectangleArea_2(heights));
    }
}
