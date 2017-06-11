package interview.am.lc2;

/**
 * #62. Unique Path
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 * Note: m and n will be at most 100.
 */
public class UniquePaths {


    // dynamic programming
    // dp[i][j] : represents number of unique paths from top left to cell(i,j)
    //          = dp[i-1][j] + dp[i][j-1]    (sum of # of paths to cell up and cell left)
    // initiation : dp[0][0] = 1
    // * This can be simplified. As we are using 2 for-loops (1 for row, 1 for column), inside the inner loop,
    // We are always processing on the same row (i unchanged). We could do with only 1-d dp array.
    // dp[j]: represents the number of unique paths from top left to cell(i-1, j)
    // * For current row i, dp[j] = dp[j-1] + dp[j] ......... dp[j-1] is the left cell, dp[j] is the upper cell

    public int uniquePaths2(int m, int n) {
        if (m<=0 || n<=0) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i=0; i<m; i++) {
            // j starts from 1 - since dp[0] never changes (it is always the same as the upper cell, since there is no left cell)
            for (int j=1; j<n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }


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
    public static int uniquePaths3(int m, int n) {
        if (m<=0 || n<=0) return 0;
        int smaller = Math.min(m,n) - 1;
        int bigger = Math.max(m,n) - 1;
        long numerator = 1;
        long denominator = 1;
        for (int i=1; i<=smaller; i++) {
            denominator *= i;
            numerator *= bigger+i;
        }
        return (int) (numerator/denominator);
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(10,10));
        System.out.println(uniquePaths3(10,10));
    }
}
