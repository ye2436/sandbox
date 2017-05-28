package lc;

/**
 * #69. lc.Sqrt(x)
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 */
public class Sqrt {
    // to find sqrt s
    // s^2 <= x < (s+1)^2
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

    // 二分法float
    public static float mySqrt(float x) {
        if (x<=0) return 0;
        double l=0;
        double r = x/2+1;
        float eps = 0.0000001f;
        while(l<r) {
            double m = (l+r)/2;
            if (Math.abs(m*m - x) < eps) {
                return (float) m;
            }
            if (m*m >x) {
                r=m;
            } else {
                l=m;
            }
        }
        return 0;
    }

    // 一般牛顿法是用数值方法来解一个f(y)=0的方程（为什么变量在这里用y是因为我们要求的开方是x，避免歧义）。
    // 对于这个问题我们构造f(y)=y^2-x，其中x是我们要求平方根的数，那么当f(y)=0时，即y^2-x=0,所以y=sqrt(x),即是我们要求的平方根。
    // f(y)的导数f'(y)=2*y，根据牛顿法的迭代公式我们可以得到y_(n+1)=y_n-f(n)/f'(n)=(y_n+x/y_n)/2。最后根据以上公式来迭代解以上方程。

    // 实际面试遇到的题目可能不是对一个整数开方，而是对一个实数。方法和整数其实是一致的，只是结束条件换成左界和右界的差的绝对值小于某一个epsilon（极小值）即可。
    public static float mySqrtf(float x) {
        if (x==0) return 0;
        float prev = 0;
        float val = 1;
        float eps = 0.0000001f;
        while (Math.abs(val - prev) > eps) {
            prev = val;
            val = (prev + x/prev)/2;
        }
        return val;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(16));
        System.out.println(mySqrt(17.5f));
        System.out.println(mySqrtf(17.5f));
    }
}
