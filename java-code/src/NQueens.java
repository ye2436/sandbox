import java.util.*;

/**
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

    private static boolean helper_tel(int n, int count, int i, int j, char[][] board, List<List<String>> res) {
        if (j == n) {
            i++;
            j = 0;
        }
        if (i == n) {
            if (count == 0) {
                return true;
            } else {
                return false;
            }
        }
        board[i][j] = 'Q';
        if (isValid_tel(i, j, board) && helper_tel(n, count-1, i, j+1, board, res)) {
            List<String> solution = new ArrayList<>();
            for (int r=0; r<n; r++) {
                solution.add(new String(board[r]));
            }
            res.add(solution);
        }
        board[i][j] = '.';
        return helper_tel(n, count, i, j+1, board, res);
    }

    private static boolean isValid_tel(int i, int j, char[][] board) {
        int n = board.length;
        for (int k=0; k<i; k++) { // up
            if(board[k][j] == 'Q') return false;
        }
        for (int k=0; k<j; k++) { // left
            if(board[i][k] == 'Q') return false;
        }

        for (int k=1; k<=Math.min(i,j); k++) { //diagonal
            if (board[i-k][j-k] == 'Q') return false; // left up
        }
        for (int k=1; k<=Math.min(i,n-1-j); k++) { //diagonal
            if (board[i-k][j+k] == 'Q') return false; // right up
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(6));
    }
}
