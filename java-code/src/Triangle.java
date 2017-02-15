import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #120. Triangle
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class Triangle {
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        // dp[i][j] represents the min sum at ith row and jth column
        // it equals to the sum of 1) the min of the two adjacent points from upper level, and 2) triangle[i][j]'s value
        // i.e., dp[i][j] = min(dp[i-1][j-1],dp[i-1][j]) + triangle[i][j]

        // we could use one dimensional array to record the row
        int[] dp = new int[triangle.size()]; // triangle.size() is the # of rows, it equals to the length of the bottom row.
        dp[0] = triangle.get(0).get(0); // init dp[0] to the value of the triangle top
        for (int i=1; i<triangle.size(); i++) {

            for (int j=1; j<=i; j++) { // j--?
                dp[j] = Math.min(dp[j-1], dp[j]) + triangle.get(i).get(j);
            }
        }

        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3,4));
        triangle.add(Arrays.asList(6,5,7));
        triangle.add(Arrays.asList(4,1,8,3));
        System.out.println(triangle);
    }
}
