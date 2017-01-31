/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * Consider the following matrix:
 * [
 *  [1,   3,  5,  7],
 *  [10, 11, 16, 20],
 *  [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */
public class SearchA2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int l = 0;
        int r = rows*cols-1;
        while (l<=r) {
            int m = (l+r)/2;
            int curr = matrix[m/cols][m%cols];
            if (curr == target) {
                return true;
            } else if (curr < target) {
                l = m+1;
            } else {
                r = m-1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        //int[][] matrix = {{1,2,3},{4,0,6},{7,8,9}};
        int[][] matrix = {{1,3,5,7,8},{10,11,16,20,21},{23,30,34,50,51}};
        System.out.println(searchMatrix(matrix,3));
    }
}
