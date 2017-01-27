/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
public class Sqrt {
    public static int mySqrt(int x) {
        if (x<=0) return 0;
        if (x==1) return 1;

        return 2*mySqrt(x/4);
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(16));
    }
}
