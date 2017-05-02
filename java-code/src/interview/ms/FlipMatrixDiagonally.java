package interview.ms;

import java.util.Arrays;

/**
 * Diagonally flip a two dimensional array.
 */
public class FlipMatrixDiagonally {

    // Observation:
    //  - in order to talk about diagonals in a two dimensional array, that means
    // that we are dealing with a (n x n) array (square matrix)

    //Solution:
    //        - flip on the first diagonal (i = j): (i, j) (j, i)
    //        - flip on the second diagonal (i + j = n-1): (i,j) (n - 1 - j, n - 1 - i)

    // 右上到左下
    public static void flip(int[][] array) {
        if (array.length != array[0].length) {
            //throw new Exception("Unable to flip the array. It needs to be a square matrix");
        }
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j > i; j--) { // only flip half, and no need to flip the point on diagonal
                int temp = array[i][j];
                array[i][j] = array[j][i];
                array[j][i] = temp;
            }
        }
    }

    // 左上到右下 time: O(n^2)
    public static void flip2(int[][] array) {
        if (array.length != array[0].length) {
            //throw new Exception("Unable to flip the array. It needs to be a square matrix");
        }
        int n = array.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1-i; j++) { // when j = n-1-i, it's on the diagonal
                int temp = array[i][j];
                array[i][j] = array[n-1-j][n-1-i];
                array[n-1-j][n-1-i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,0,1,2},
                {3,4,5,6}
        };
        flip2(array);
        System.out.println(Arrays.deepToString(array));
    }
}
