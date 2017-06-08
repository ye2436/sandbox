package interview.am.lc;

import java.util.Arrays;

/**
 * 396. Rotate Function

 Given an array of integers A and let n to be its length.

 Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

 F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

 Calculate the maximum value of F(0), F(1), ..., F(n-1).

 Note:
 n is guaranteed to be less than 105.

 Example:

 A = [4, 3, 2, 6]

 F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

 So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 */
public class RotateFunction {

    // This can be done mathematically.
    // F(k) = (0+k)%n * A[0] + (1+k)%n * A[1] + ... + (i+k)%n * A[i] + (n-1+k)%n * A[n-1]
    // note that each constant before A[i] should be modular of n
    public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int n = A.length;
        for (int k=0; k<n; k++) {
            int f = 0;
            for (int i=0; i<n; i++) {
                f += (i+k) % n * A[i];
            }
            max = Math.max(max, f);
        }
        return max;
    }

    // Further analyze F function
    // What's F(k) - F(k-1)? It's the sum of the entire array - n*A[n-k]
    // so first initialize with F(0), then calculate F(1) ... F(k) using above formular
    public int maxRotateFunction_2(int[] A) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int f = 0;
        int sum = 0;
        for (int i=0; i<n; i++) { // F(0)
            f += i * A[i];
            sum += A[i];
        }

        int max = f;
        for (int k=1; k<n; k++) {
            f += sum - n * A[n-k];
            max = Math.max(max, f);
        }

        return max;
    }




    public static void main(String[] args) {
        int[] a = {1,2,3};
        int[] b = Arrays.copyOf(a, a.length);
        a[0] = 4;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
