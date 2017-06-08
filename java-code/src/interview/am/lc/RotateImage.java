package interview.am.lc;

import java.util.Arrays;

/**
 * #48. Rotate Image
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 */
public class RotateImage {

    public static void practice(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int n = matrix.length;
        // divide into n/2 layers - outer loop i
        // inner loop j : [i, n-1-i]
        for (int i=0; i<n/2; i++) {
            for (int j=i; j<=n-1-i; j++) {
                // rotate 4 cells at a time.
                // (i,j) -> (j, n-1-i) -> (n-1-i, n-1-j) -> (n-1-j, i) -> (i,j)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[n-1-j][i] = temp;
            }
        }
    }


    public static void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int n = matrix.length;
        int[][] temp = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                matrix[i][j] = temp[n-1-j][i];
            }
        }
    }

    // in place
    public static void rotate2(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int n = matrix.length;
        for (int i=0; i<n/2; i++) {
            for (int j=i; j<n-1-i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2,3},{4,5,6},{7,8,9}};
        rotate2(nums);
        System.out.println(Arrays.deepToString(nums));
    }
}
