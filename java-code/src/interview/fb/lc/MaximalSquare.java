package interview.fb.lc;

/**
 * 221. Maximal Square

 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0
 Return 4.

 */
public class MaximalSquare {

    // dynamic programming O(m*n)
    // Let dp[i][j] be the maximum size that can be achieved at cell(i,j) --- meaning (i,j) is the bottom right of the achieved square.
    // * Size, not area. (area = size^2)
    // To initialize, the first row: dp[0][col] = matrix[0][col]   --- 0 or 1
    //                the first col: dp[row][0] = matrix[row][0]   --- 0 or 1
    // for any other if a matrix (i,j) = 0, then dp[i][j] = 0.
    //                    matrix (1,j) = 1, dp[i][j] = min (dp of up, left, upper left) + 1
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxSize = 0;
        // first row
        for (int i=0; i<n; i++) {
            dp[0][i] = matrix[0][i] - '0';
            maxSize = Math.max(maxSize, dp[0][i]);
        }
        // first column
        for (int i=0; i<m; i++) {
            dp[i][0] = matrix[i][0] - '0';
            maxSize = Math.max(maxSize, dp[i][0]);
        }
        // the rest
        for (int i=1; i<m; i++) {
            for (int j=1; j<n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }
        return maxSize * maxSize;
    }
}
