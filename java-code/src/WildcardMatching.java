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
        boolean [][] dp = new boolean[s.length()+1][p.length()+1];
        //init:
        dp[0][0] = true;

        for (int i=0; i<s.length(); i++) {
            for (int j=0; j<p.length(); j++) {
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') {
                    dp[i+1][j+1] = dp[i][j];
                }

                if (p.charAt(j) == '*') {
                    int m = j-1; // first index before j in p where char != '*'
                    while (m>=0) {
                        if (p.charAt(m) != '*') {
                            break;
                        }
                        m--;
                    } // if m<0, then before j is nothing but * or j=0
                    if (m<0) { // as long as s.length >= p.length
                        dp[i+1][j+1] = (i>=j); // i+1 >= j+1 ---> i>=j
                    } else {
                        int n = i-1;
                        while (n>=0) { // first index before i in p where dp[n][m] = true -- meaning:
                            if (dp[n][m]) {
                                break;
                            }
                            n--;
                        }
                        if (n>=0 && (i-n) >= (j-m)) {
                            dp[i+1][j+1] = true;
                        } else {
                            dp[i+1][j+1] = false;
                        }
                    }
                }
            }
        }


        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aaa","a"));
        System.out.println(isMatch("aa","a*"));
        System.out.println(isMatch("aa","a*"));
        System.out.println(isMatch("ab","?*"));
        System.out.println(isMatch("aab","c*a*b"));
        System.out.println(isMatch("zacabz","*a?b*")); // should be false
    }
}
