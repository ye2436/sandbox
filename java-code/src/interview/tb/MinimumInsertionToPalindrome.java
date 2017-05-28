package interview.tb;

/**
 * Find minimum insertions needed for a string to become a palindrome
 */
public class MinimumInsertionToPalindrome {

    // Solution 1: dynamic programming
    // if s(0) = s(n-1), then f(s(0,n-1)) = f(s(1, n-2)) + 1
    // if s(0) != s(n-1), then f(s(0,n-1)) = min(f(s(1,n-1)), f(s(0,n-2))) + 1
    public static int minInsertions(String s) {
        if (s.length() <= 1) return 0;
        int n = s.length();
        if (s.charAt(0) == s.charAt(n-1)) {
            if (n <= 3) return 0;
            return minInsertions(s.substring(1, n-1)); // n-1 exclusive
        } else {
            return Math.min(minInsertions(s.substring(0, n-1)), minInsertions(s.substring(1, n)))+1;
        }
    }

    public static void main(String[] args) {
        System.out.println(minInsertions("abcde"));
        System.out.println(minInsertions("abcda"));
        System.out.println(minInsertions("a"));
        System.out.println(minInsertions("aa"));
        System.out.println(minInsertions("abac"));
    }
}
