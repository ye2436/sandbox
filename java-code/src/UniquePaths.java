/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * Note: m and n will be at most 100.
 */
public class UniquePaths {
    // dynamic programming
    public static int uniquePaths(int m, int n) {
        if (m<=0 || n<=0) return 0;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        // dp[i][j] = dp[i][j-1] + dp[i-1][j]
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (j>0) {
                    dp[i][j] += dp[i][j-1];
                }
                if (i>0) {
                    dp[i][j] += dp[i-1][j];
                }
            }
        }

        return dp[m-1][n-1];
    }

    // m+n-2 choose m-1, let m be the smaller one

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }
}
