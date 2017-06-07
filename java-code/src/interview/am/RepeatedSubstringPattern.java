package interview.am;

/**
 * 459. Repeated Substring Pattern

 Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 Example 1:
 Input: "abab"

 Output: True

 Explanation: It's the substring "ab" twice.
 Example 2:
 Input: "aba"

 Output: False
 Example 3:
 Input: "abcabcabcabc"

 Output: True

 Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {

    // With repeated pattern appearing, the pattern can be half of the string at the longest, and 1 character long at the shortest
    // We can get the pattern from length of 1 to length of n/2 (if n is even), concatenate the pattern multiple times till it reaches n length
    // compare the newly formed string with s to find if s is the same as the new string.
    public static boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() < 2) return false;
        int n = s.length();
        for (int i=n/2; i>=1; i--) { // i is the pattern length
            if (n % i == 0) {
                String pattern = s.substring(0, i);
                StringBuilder sb = new StringBuilder();

                int m = n/i;
                for (int j=0; j< m; j++) {
                    sb.append(pattern);
                }
                if (sb.toString().equals(s)) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("abab"));
    }
}
