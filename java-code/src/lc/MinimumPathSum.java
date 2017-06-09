package lc;

/**
 * #64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {

    // dp[i][j]: minPathSum at cell (i, j)
    //         = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
    // to use 1-d array:
    // dp[j] = min(dp[j], dp[j-1]) + grid[i][j]
    // * We need to initialize the first row, otherwise min(dp[j], dp[j-1]) will always be 0 because initial dp[j] is 0
    public static int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        // intialize with first row results
        dp[0] = grid[0][0];
        for (int j=1; j<n; j++) {
            dp[j] = dp[j-1] + grid[0][j];
        }

        // continue with the rest rows
        for (int i=1; i<m; i++) {
            dp[0] += grid[i][0]; // initialize first column
            for (int j=1; j<n; j++) {
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            }
        }

        return dp[n-1];
    }



    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        // min_ending_here
        int[] dp = new int[grid[0].length];
        //dp[0] 初始第一行
        dp[0] = grid[0][0];
        for (int j=1; j<grid[0].length; j++) {
            dp[j] = dp[j-1] + grid[0][j];
        }
        for (int i=1; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                // dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]

                if (j > 0) {
                    dp[j] = Math.min(dp[j-1], dp[j]) + grid[i][j]; //dp[j-1]为同一行左边一列的值, dp[j]为上一行同一列的值
                } else {
                    dp[j] = dp[j] + grid[i][j]; // 等式右边的dp[j]还是上一行这一列的值，尚未被覆盖
                }
            }
        }

        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(minPathSum(grid));
    }
}
