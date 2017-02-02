import java.util.*;

/**
 * #37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'.
 * You may assume that there will be only one unique solution.
 */
public class SudokuSolver {
    public static void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) return;
        helper(board, 0, 0);
    }

    private static boolean helper(char[][] board, int i, int j) { // i, j - starting point
        if (j>=9) { // line change
            i++;
            j=0;
        }
        if (i==9) { // end of the board
            return true;
        }
        if (board[i][j] == '.') {
            for (int k=1; k<=9; k++) { // loop through all potential answers
                // let the current point be k
                board[i][j] = (char)(k+'0');
                if(isValid(board,i,j) && helper(board,i,j+1)) {
                    return true;
                }
                board[i][j] = '.'; // if this k didn't work out, reset the point
            }
        } else {
            return helper(board, i, j+1); // move to next
        }

        return false;
    }

    private static boolean isValid(char[][] board, int i, int j) { // only needs to valid if element at board[i][j] is valid
        for (int k=0; k<9; k++) { // row
            if (k!=j && board[i][k] == board[i][j]) {
                return false;
            }
        }
        for (int k=0; k<9; k++) { // column
            if (k!=i && board[k][j] == board[i][j]) {
                return false;
            }
        }
        for (int x=i/3*3; x<i/3*3+3; x++) { // cube
            for (int y=j/3*3; y<j/3*3+3; y++) {
                // use or here is faster
                // either x != i or y != j means that the point is not [i][j]
                if ((x!=i || y!=j) && board[x][y] == board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }
}
