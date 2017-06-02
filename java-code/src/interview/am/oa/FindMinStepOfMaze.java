package interview.am.oa;

/**
 * 有一个M*N的maze，0代表可以通过，1代表不可以通过，然后给你一个出口（x,y），找从（0,0）到出口的最少steps，如果找不到path就返回-1
 * 类似 Unique Path II.
 */
public class FindMinStepOfMaze {

    public int findMinSteps(int[][] maze, int x, int y) {

        if (maze == null || maze.length == 0 || maze[0].length == 0) return -1;
        int m = maze.length;
        int n = maze[0].length;
        if (x >= m || y >= n) return -1;
        if (maze[0][0] == 1 || maze[x][y] == 1) return -1;

        int[][] dp = new int[m][n]; // dp[i][j] : min steps from (0,0) to (i,j)
        // dp[0][0] = 0; // don't need
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (maze[i][j] == 1) {
                    dp[i][j] = Integer.MAX_VALUE;
                    continue;
                }
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]); // smaller of left point and upper pointer
                } else if (i > 0) {
                    dp[i][j] = dp[i-1][j];
                } else if (j > 0) {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[x][y];
    }

    /*public void helper(int[][] maze, boolean[][] visited, int i, int j) {
        if (i == maze.length) return;
        if (j == maze[0].length) return;
        if (visited[i][j]) return;


    }*/
}
