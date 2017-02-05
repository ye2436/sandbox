import java.util.LinkedList;

/**
 * #85. Maximal Rectangle
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * For example, given the following matrix:
 *  1 0 1 0 0
 *  1 0 1 1 1
 *  1 1 1 1 1
 *  1 0 0 1 0
 * Return 6.
 */
public class MaximalRectangle {
     // 这道题的解法灵感来自于Largest Rectangle in Histogram这道题，假设我们把矩阵沿着某一行切下来，然后把切的行作为底面，
     // 将自底面往上的矩阵看成一个直方图（histogram）。直方图的中每个项的高度就是从底面行开始往上1的数量。
     // 根据Largest Rectangle in Histogram我们就可以求出当前行作为矩阵下边缘的一个最大矩阵。
     // 接下来如果对每一行都做一次Largest Rectangle in Histogram，从其中选出最大的矩阵，那么它就是整个矩阵中面积最大的子矩阵。
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                // 如果我们知道上一行直方图的高度，我们只需要看新加进来的行（底面）上对应的列元素是不是0，如果是，则高度是0，否则则是上一行直方图的高度加1。
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j]+1;
            }
            maxArea = Math.max(largestRectangleArea(heights), maxArea);
        }
        return maxArea;
    }

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int maxArea = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i=0; i<heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int idx = stack.pop();
                if (stack.isEmpty()) {
                    maxArea = Math.max(maxArea, heights[idx] * i);
                } else {
                    maxArea = Math.max(maxArea, heights[idx] * (i - stack.peek() - 1));
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            if (stack.isEmpty()) {
                maxArea = Math.max(maxArea, heights[idx] * heights.length);
            } else {
                maxArea = Math.max(maxArea, heights[idx] * (heights.length - stack.peek() - 1));
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalRectangle(matrix));
    }
}
