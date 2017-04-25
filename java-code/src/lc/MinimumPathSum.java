package lc;

/**
 * #64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {
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
