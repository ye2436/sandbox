package interview.fb.lc;

/**
 * #50. lc.Pow(x,n)
 * Implement pow(x, n).
 */
public class Pow {
    public static double myPow(double x, int n) {
        if (n==0) return 1;
        if(n == Integer.MIN_VALUE){
            x = x * x;
            n = n/2;
        }
        if (n<0) {
            n = -n;
            x = 1/x;
        }
        return n%2 == 0 ? myPow(x*x, n/2) : x* myPow(x*x, n/2);
    }

    public static void main(String[] args) {
        System.out.println(myPow(2d,-3));
    }
}
