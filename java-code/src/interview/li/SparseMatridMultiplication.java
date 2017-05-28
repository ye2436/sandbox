package interview.li;

/**
 * # 311. Sparse Matrix Multiplication
 */
public class SparseMatridMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || A[0].length == 0 || B == null || B.length != A[0].length || B[0].length == 0) {
            return null;
        }
        int m = A.length;
        int n = A[0].length;
        int nB = B[0].length;
        int[][] C = new int[m][nB];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (A[i][j] == 0) {
                    continue;
                }
                for (int k=0; k<nB; k++) {
                    if (B[j][k] == 0) continue;
                    C[i][k] += A[i][j] * B[j][k];
                }
            }
        }
        return C;
    }
}
