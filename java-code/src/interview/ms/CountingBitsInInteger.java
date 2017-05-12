package interview.ms;

/**
 * Write an efficient program to count number of 1s in binary representation of an integer.
 * Or count 0
 */
public class CountingBitsInInteger {

    // Subtraction of 1 from a number toggles all the bits (from right to left) till the rightmost set bit(including the righmost set bit).
    // So if we subtract a number by 1 and do bitwise & with itself (n & (n-1)), we unset the righmost set bit.
    // If we do n & (n-1) in a loop and count the no of times loop executes we get the set bit count.
    public static int countOne(int x) {
        int count = 0;
        while (x>0) {
            x &= (x-1);
            count++;
        }
        return count;
    }

    // shift right = /2
    public static int countTotalBits(int x) {
        int count = 0;
        while (x > 0) {
            x = x >> 1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countOne(4));
        System.out.println(countTotalBits(4));
        System.out.println(countTotalBits(4) - countOne(4)); // count of 0's
    }

}
