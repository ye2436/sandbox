package lc;

import java.util.*;

/**
 * #51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * [
 *  [".Q..",  // Solution 1
 *  "...Q",
 *  "Q...",
 *  "..Q."],
 *  ["..Q.",  // Solution 2
 *  "Q...",
 *  "...Q",
 *  ".Q.."]
 * ]
 */
public class NQueens {

    // dfs
    public static List<List<String>> solveNQueens_2(int n) {
        if (n==0) return new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }
        helper_2(board, res, 0);
        return res;
    }

    // only need to keep track of row number, because there could be only 1 queen in a row
    // we are processing one row at a time
    private static void helper_2(char[][] board, List<List<String>> res, int row) { // board initialized with '.'
        if (row == board.length) {
            // add current board to res and return
            List<String> solution = new ArrayList<>();
            for (int i = 0; i<board.length; i++) {
                solution.add(new String(board[i]));
            }
            res.add(solution);
            return;
        }

        for (int i=0; i<board.length; i++) {
            board[row][i] = 'Q';
            if (isValid_2(board, row, i)) {
                helper_2(board, res, row+1);
            }
            board[row][i] = '.';
        }
    }

    private static boolean isValid_2(char[][] board, int row, int column) {
        // no need to check the columns on the same row because we add only 1 to a row
        // so check above rows, and left upper diagonal, and right upper diagonal
        for (int i=0; i<row; i++) { // i is all the rows before row
            if (board[i][column] == board[row][column]
                    || column-row+i >=0 && board[i][column-row+i] == board[row][column]
                    || column+row-i < board.length && board[i][column+row-i] == board[row][column])
                return false;
        }
        return true;
    }

    /////////// better solution with fewer space complexity /////////////

    public static List<List<String>> solveNQueens(int n) {
        if (n==0) return new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        helper(n, 0, new int[n], res);
        return res;
    }

    // 一次check一行
    private static void helper(int n, int rowNum, int[] columnsForRow, List<List<String>> res) {
        if (rowNum == n) {
            List<String> solution = new ArrayList<>();
            for (int i=0; i<columnsForRow.length; i++) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[columnsForRow[i]] = 'Q';
                solution.add(new String(row));
            }
            res.add(solution);
            return;
        }
        for (int i=0; i<n; i++) {
            columnsForRow[rowNum] = i; // let row=rowNum, column=i be Q
            if (isValid(rowNum, columnsForRow)) { // if valid move to next row
                helper(n, rowNum+1, columnsForRow, res);
            }
            // no need to revert last
            // 这道题目中不用移除的原因是我们用一个一维数组去存皇后在对应行的哪一列，因为一行只能有一个皇后，
            // 如果二维数组，那么就需要把那一行那一列在递归结束后设回没有皇后，所以道理是一样的。
        }
    }

    private static boolean isValid(int row, int[] columnsForRow) {
        for (int i=0; i<row; i++) { // i is all the rows before row
            if (columnsForRow[i] == columnsForRow[row]
                    || Math.abs(columnsForRow[row]-columnsForRow[i]) == row-i) return false; // row is always greater than i
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(6));
        System.out.println(solveNQueens_2(6));
    }
}
