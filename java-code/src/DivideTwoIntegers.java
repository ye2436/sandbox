/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 */
public class DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor==-1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;

        int count = 0;
        int temp = 0;
        int sign;
        if ((dividend > 0) != (divisor > 0)) { // not the same sign
            sign = -1;

            // make sure it does not overflow, and now both have the same sign (negative)
            if (divisor > 0) {
                divisor = -divisor;
            } else {
                dividend = -dividend;
            }
        } else {
            sign = 1;
        }


        if (divisor > 0) {
            while (temp + divisor <= dividend) {
                temp += divisor;
                if (temp > Integer.MAX_VALUE - divisor) {
                    return Integer.MAX_VALUE;
                }
                count++;
            }
        } else {
            while (temp + divisor >= dividend) {
                temp += divisor;
                if (temp < Integer.MIN_VALUE - divisor) {
                    return Integer.MAX_VALUE;
                }
                count++;
            }
        }

        return count * sign;
    }

    public static void main(String[] args) {
        System.out.println(divide(-16,4));
    }
}
