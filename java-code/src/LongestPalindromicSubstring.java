
/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example:

 Input: "babad"

 Output: "bab"

 Note: "aba" is also a valid answer.
 Example:

 Input: "cbbd"

 Output: "bb"
 */
public class LongestPalindromicSubstring {
    public static String solution_1(String s) { // brutal force
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j<= s.length(); j++) {
                if (isPalindrome(s.substring(i,j)) && j-i+1>longestPalindrome.length()) {
                    longestPalindrome = s.substring(i,j);
                }
            }
        }
        return longestPalindrome;
    }

    public static String solution_2(String s) { // dynamic programming
        String res = "";
        int max = 0;
        int l = s.length();
        boolean[][] dp = new boolean[l][l]; // default to false
        for (int i = l-1; i >= 0; i--) {
            for (int j = i; j < l; j++) {
                if (s.charAt(i) == s.charAt(j) && (j-i<=2 || dp[i+1][j-1])) { // use condition (j-i <=2) to initiate
                    dp[i][j] = true;
                    if (j-i+1>max) {
                        max = j-i+1;
                        res = s.substring(i,j+1);
                    }
                }
            }
        }
        return res;
    }

    public static String solution_3(String s) { // expand from the center
        String res = "";
        int max = 0;
        int n = s.length();
        for (int i = 0; i<n; i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            if (Math.max(len1, len2) > max) {
                max = Math.max(len1, len2);
                res = s.substring(i - (max -1)/2, i + max/2 +1);
            }
        }

        return res;
    }

    private static int expandAroundCenter(String s, int left, int right) {  // return the max length with a given center
        while (left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
        // usually the length should be right-left+1, but here, after last loop of condition was met (left=0, right=length-1)
        // left and right was expanded once more. so the real length was expanded by 2, it should be removed.
    }


    private static boolean isPalindrome(String s) {
        int length = s.length();
         for (int i = 0 ; i < length/2; i++) {
             //System.out.println(s.charAt(i)+" - "+s.charAt(length-i-1));
             if (s.charAt(i) != s.charAt(length-i-1)) {
                 return false;
             }
         }
         return true;
    }

    public static void main(String[] args) {

        System.out.println(solution_1("a"));

        System.out.println(solution_2("a"));

        System.out.println(solution_3("abcdcba"));
    }

}
