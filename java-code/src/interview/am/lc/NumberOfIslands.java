package interview.am.lc;

/**
 * 200. Number of Islands Add to List
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 *
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */
public class NumberOfIslands {

    // dfs and union find
    // while finding an island, mark it to 0 along the way. so it does not get calculated multiple times
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int count=0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                }
            }
        }
        return count;
    }

    private void helper(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        grid[i][j] = '0';
        helper(grid, i+i, j);
        helper(grid, i-i, j);
        helper(grid, i, j+1);
        helper(grid, i, j-1);

    }
}
