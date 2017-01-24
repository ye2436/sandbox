import java.util.Arrays;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * For example,
 * Given n = 3,
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 *  ]
 */
public class SpiralMatrixII {
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        return matrix;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(3)));
    }
}
