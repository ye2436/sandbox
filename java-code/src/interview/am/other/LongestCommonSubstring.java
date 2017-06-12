package interview.am.other;

/**
 * http://www.geeksforgeeks.org/longest-common-substring/
 * https://en.wikipedia.org/wiki/Longest_common_substring_problem
 */
public class LongestCommonSubstring {

    // Dynamic Programming can be used to find the longest common substring in O(m*n) time. The idea is to find length
    // of the longest common suffix for all substrings of both strings and store these lengths in a table.

    // dp[i][j] - represents length of longest common suffix of first i characters of s and first j characters of t
    //                                                          s.substring(0,i)            t.substring(0,j)
    // when i=0 or j=0, dp[i][j] = 0
    // for any other cells, when s.charAt(i) != t.charAt(j), dp[i][j] = 0
    //                      otherwise, dp[i][j] = dp[i-1][j-1] + 1 ...... look at previous common suffix length and plus the length of last char

    // ** Note the right boundaries of s and t. It should be equal to string length
    public static String longestCommonSubString(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m+1][n+1];
        int maxLen = -1;  // To store length of the longest common substring

        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (i==0 || j==0) {
                    dp[i][j] = 0;
                } else if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        for (int i=0; i<=m; i++) {
            for (int j=0; j<=n; j++) {
                if (dp[i][j] == maxLen) {
                    return s.substring(i-maxLen, i);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubString("abcdxyz","xyzabcd"));
    }
}
