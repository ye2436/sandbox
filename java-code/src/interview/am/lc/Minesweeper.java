package interview.am.lc;

import java.util.Arrays;

/**
 * 529. Minesweeper

 Let's play the minesweeper game (Wikipedia, online game)!

 You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

 Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

 If a mine ('M') is revealed, then the game is over - change it to 'X'.
 If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 Return the board when no more squares will be revealed.
 Example 1:
 Input:

 [['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

 Click : [3,0]

 Output:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Explanation:

 Example 2:
 Input:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Click : [1,2]

 Output:

 [['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

 Explanation:

 Note:
 The range of the input matrix's height and width is [1,50].
 The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
 The input board won't be a stage when game is over (some mines have been revealed).
 For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
 */
public class Minesweeper {

    // dfs - for a given click point, first check if it's E or M
    // if M, mark X and stop, otherwise continue
    // check 8 directions for a given cell to get a count of adjacent mines, if 0, set B, otherwise set number
    // if B, further check 8 directions (except visited?)
    public static char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];
        if (i<0 || i>=board.length || j<0 || j>=board[0].length) return board;
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        } else if (board[i][j] != 'E') { // check if visited
            return board;
        }
        int cnt = helper(board, i-1,j) + helper(board, i+1, j) + helper(board, i, j-1) + helper(board, i, j+1)
                + helper(board, i-1, j+1) + helper(board, i-1, j-1) + helper(board, i+1, j+1) + helper(board, i+1, j-1);
        if (cnt == 0) {
            board[i][j] = 'B';
            for (int m=-1; m<2; m++) {
                for (int n=-1; n<2; n++) {
                    if (m==0 && n==0) continue; // itself

                    updateBoard(board, new int[]{i + m, j + n});
                }
            }
        } else {
            board[i][j] = (char) (cnt + '0');
        }

        return board;
    }

    // return 1 if cell [i,j] is mine
    private static int helper(char[][] board, int i, int j) {
        if (i<0 || i>=board.length || j<0 || j>=board[0].length) return 0;
        return board[i][j] == 'M' ? 1: 0;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        System.out.println(Arrays.deepToString(updateBoard(board, new int[]{3,0})));
    }
}
