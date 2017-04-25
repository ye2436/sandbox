package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #52. N-Queens II
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 */
public class NQueensII {
    public static int totalNQueens(int n) {
        if (n==0) return 0;
        List<Integer> count = new ArrayList<>();
        count.add(0);
        helper(n, 0, new int[n], count);
        return count.get(0);
    }

    private static void helper(int n, int rowNum, int[] columnsForRow, List<Integer> count) {
        if (rowNum == n) {
            count.add(0, count.get(0)+1);
            return;
        }
        for (int i=0; i<n; i++) {
            columnsForRow[rowNum] = i; // let row=rowNum, column=i be Q
            if (isValid(rowNum, columnsForRow)) { // if valid move to next row
                helper(n, rowNum+1, columnsForRow, count);
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
        System.out.println(totalNQueens(8));
    }
}
