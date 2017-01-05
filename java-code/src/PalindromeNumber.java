/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        if (x<0)
            return false;
        if (x==0)
            return true;

        int temp = x;
        int reversed = 0;
        while (x>0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        if (reversed == temp) { // if reversed overflows, the x is definitely not a palindrome.
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(12321));
    }
}
