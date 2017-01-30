/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
public class Sqrt {
    public static int mySqrt(int x) {
        if (x<=0) return 0;
        int l=1;
        int r = x/2+1;
        while(l<=r) {
            int m = (l+r)/2;
            if (m <= x/m && m+1 > x/(m+1)) {
                return m;
            }
            if (x/m<m) {
                r=m-1;
            } else {
                l=m+1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(16));
    }
}
