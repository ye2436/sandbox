package interview.ms;

/**
 * 258. Add Digits Add to List
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 * Follow up:
 * Could you do it without any loop/recursion in O(1) runtime?
 */
public class AddDigits {

    public int addDigits(int num) {
        while (num >= 10) {
            num = num/10 + num%10;
        }
        return num;
    }

    // Digital root problem
    // https://en.wikipedia.org/wiki/Digital_root#Congruence_formula
    // For base b (decimal case b = 10), the digit root of an integer is:
    // dr(n) = 0 if n == 0
    // dr(n) = (b-1) if n != 0 and n % (b-1) == 0
    // dr(n) = n mod (b-1) if n % (b-1) != 0
    // OR
    // dr(n) = 1 + (n - 1) % 9
    public int addDigits2(int num) {
        //return num==0?0:(num%9==0?9:(num%9));
        return 1+(num-1)%9;
    }
}
