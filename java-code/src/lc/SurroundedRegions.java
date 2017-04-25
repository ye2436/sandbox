package lc;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * #130. Surrounded Regions
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * For example,
 *  X X X X
 *  X O O X
 *  X X O X
 *  X O X X
 * After running your function, the board should be:
 *  X X X X
 *  X X X X
 *  X X X X
 *  X O X X
 */
public class SurroundedRegions {
    // 这个题目用到的方法是图形学中的一个常用方法：Flood fill算法，其实就是从一个点出发对周围区域进行目标颜色的填充。
    // 背后的思想就是把一个矩阵看成一个图的结构，每个点看成结点，而边则是他上下左右的相邻点，然后进行一次广度或者深度优先搜索。
    // 首先根据题目要求，边缘上的'O'是不需要填充的，所以我们的办法是对上下左右边缘做Flood fill算法，把所有边缘上的'O'都替换成另一个字符，比如'#'。
    // 接下来我们知道除去被我们换成'#'的那些顶点，剩下的所有'O'都应该被替换成'X'，而'#'那些最终应该是还原成'O'，
    // 如此我们可以做最后一次遍历，然后做相应的字符替换就可以了。复杂度分析上，我们先对边缘做Flood fill算法，
    // 因为只有是'O'才会进行，而且会被替换成'#'，所以每个结点改变次数不会超过一次，因而是O(m*n)的复杂度，最后一次遍历同样是O(m*n)，
    // 所以总的时间复杂度是O(m*n)。空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，
    // 同时存在的空间占用不会超过O(m+n)（以广度优先搜索为例，每次队列中的结点虽然会往四个方向拓展，但是事实上这些结点会有很多重复，
    // 假设从中点出发，可以想象最大的扩展不会超过一个菱形，也就是n/2*2+m/2*2=m+n，所以算法的空间复杂度是O(m+n)）。
    //
    // 下面代码用的是广度优先搜索，用一个队列来维护，当然也可以用深度优先搜索，但是如果使用递归，会发现LeetCode过不了，
    // 这是因为在图形中通常图片（或者说这里的矩阵）一般会很大，递归很容易导致栈溢出，所以即使要用深度优先搜索，也最好使用非递归的实现方式
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        for (int i=0; i<board.length; i++) { // left & right
            fill(board, i,0);
            fill(board, i, board[0].length-1);
        }
        for (int j=0; j<board[0].length; j++) { // top & bottom
            fill(board, 0, j);
            fill(board, board.length-1, j);
        }
        for (int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // flood fill from start point (i,j), replace O with #
    private static void fill(char[][] board, int i, int j) {
        // if not equals to target, return
        if (board[i][j] != 'O') {
            return;
        }
        // replace the point and add to queue;
        board[i][j] = '#';
        LinkedList<int[]> queue = new LinkedList(); // int[]: row index and column index
        queue.add(new int[]{i,j});
        while (!queue.isEmpty()) {
            int[] coordinates = queue.pop();
            int row = coordinates[0];
            int column = coordinates[1];

            if (row > 0 && board[row-1][column] == 'O') { // up
                board[row-1][column] = '#';
                queue.add(new int[]{row-1, column});
            }

            if (row < board.length-1 && board[row+1][column] == 'O') { // down
                board[row+1][column] = '#';
                queue.add(new int[]{row+1, column});
            }

            if (column > 0 && board[row][column-1] == 'O') { // left
                board[row][column-1] = '#';
                queue.add(new int[]{row, column-1});
            }

            if (column < board[0].length-1 && board[row][column+1] == 'O') { // right
                board[row][column+1] = '#';
                queue.add(new int[]{row, column+1});
            }
        }

    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'}, {'X','O','O','X'}, {'X','X','O','X'}, {'X','O','X','X'}};
        solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}
