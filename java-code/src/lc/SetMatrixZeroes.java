package lc;

import java.util.Arrays;

/**
 * #73. Set Matrix Zeroes
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
    public static void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[0].length; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        boolean rowFlag = false;
        boolean columnFlag = false;


        for (int i=0; i<matrix.length; i++) {
            if (matrix[i][0] == 0) {
                columnFlag = true;
                break;
            }
        }
        for (int j=0; j<matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowFlag = true;
                break;
            }
        }

        for (int i=1; i<matrix.length; i++) {
            for (int j=1; j<matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i=1; i<matrix.length; i++) {
            for (int j=1; j<matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (columnFlag) {
            for (int i=0; i<matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (rowFlag) {
            for (int j=0; j<matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = {{1,2,3},{4,0,6},{7,8,9}};
        int[][] matrix = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
        setZeroes2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
