package interview.am.lc2;

import java.util.ArrayList;
import java.util.List;

/**
 * #54. Spiral Matrix
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example,
 * Given the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class SpiralMatrix {

    // keep 4 variables rowBegin, rowEnd, colBegin colEnd.
    public static List<Integer> spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length-1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // right
            for (int i= colBegin; i<=colEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            // down
            for (int i=rowBegin; i<=rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;

            // left
            if (rowBegin <= rowEnd) { // in case of this round there is only 1 last row left
                for (int i = colEnd; i >= colBegin; i--) {
                    res.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            // up
            if (colBegin <= colEnd) { // in case of this round there is only 1 last column left
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][colBegin]);
                }
            }
            colBegin++;
        }

        return res;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int min = Math.min(m,n);
        int layer = min/2;
        for (int i=0; i<layer; i++) {
            int temp = i;
            int j=i;
            for (; j<n-1-temp; j++) {
                res.add(matrix[i][j]);
            }
            // 此时 j = n-1-i
            for (; i<m-1-temp; i++) {
                res.add(matrix[i][j]);
            }
            // 此时 i = m-1-i
            for (; j>temp; j--) {
                res.add(matrix[i][j]);
            }
            // 此时 j = i
            for (; i>temp; i--) {
                res.add(matrix[i][j]);
            }
            // i back to temp
        }

        if (min%2==1) {
            if (m<n) {
                int i = layer;
                for (int j=layer; j<n-layer; j++) {
                    res.add(matrix[i][j]);
                }
            } else {
                int j = layer;
                for (int i=layer; i<m-layer; i++) {
                    res.add(matrix[i][j]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{2,3}};
        System.out.println(spiralOrder(matrix));
    }
}
