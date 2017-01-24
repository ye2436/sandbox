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
        int count = 1;
        for (int i=0; i<n/2; i++) {
            int temp = i;
            int j=i;
            for (; j<n-1-temp; j++) {
                matrix[i][j] = count;
                count++;
            }
            for (; i<n-1-temp; i++) {
                matrix[i][j] = count;
                count++;
            }
            for (; j>temp; j--) {
                matrix[i][j] = count;
                count++;
            }
            for (; i>temp; i--) {
                matrix[i][j] = count;
                count++;
            }
        }
        if (n%2 == 1) {
            matrix[n/2][n/2] = count;
        }

        return matrix;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(3)));
    }
}
