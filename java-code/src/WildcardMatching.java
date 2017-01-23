/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 *  isMatch("aa","a") → false
 *  isMatch("aa","aa") → true
 *  isMatch("aaa","aa") → false
 *  isMatch("aa", "*") → true
 *  isMatch("aa", "a*") → true
 *  isMatch("ab", "?*") → true
 *  isMatch("aab", "c*a*b") → false
 */
public class WildcardMatching {
    public static boolean isMatch(String s, String p) {
        if (p.equals("*")) return true;
        //if (p.length()>s.length()) return false; // * could match empty string

        boolean [][] dp = new boolean[s.length()+1][p.length()+1];
        //init:
        dp[0][0] = true;

        for (int j=0; j<p.length(); j++) {
            if (p.charAt(j) != '*') {
                for (int i=0; i<s.length(); i++) {
                    dp[i+1][j+1] = dp[i][j] && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i));
                }
            } else {
                // before *, and any i before at dp[i][j] is true, all the following dp[i][j] are true
                int i=0;
                while (i<=s.length() && !dp[i][j]) {
                    i++;
                }
                for (; i<=s.length(); i++) {
                    dp[i][j+1] = true;
                }
            }

        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aa","*"));
        System.out.println(isMatch("aaa","a"));
        System.out.println(isMatch("aa","a*"));
        System.out.println(isMatch("ab","?*"));
        System.out.println(isMatch("aab","c*a*b"));
        System.out.println(isMatch("zacabz","*a?b*")); // should be false
        System.out.println(isMatch("","*")); // should be true
    }
}
