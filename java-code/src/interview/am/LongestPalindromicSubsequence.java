package interview.am;

/**
 *  516. Longest Palindromic Subsequence

 Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

 Example 1:
 Input:

 "bbbab"

 Output:

 4

 One possible longest palindromic subsequence is "bbbb".

 Example 2:
 Input:

 "cbbd"

 Output:

 2

 */
public class LongestPalindromicSubsequence {

    // Kinda like Minimum insertion to Palindrome where:
    // The minimum number of insertions in the string str[l…..h] can be given as:
    // minInsertions(str[l+1…..h-1]) if str[l] is equal to str[h]
    // min(minInsertions(str[l…..h-1]), minInsertions(str[l+1…..h])) + 1 otherwise

    // Here, we could use dynamic programming
    // dp[i][j] - the length of longest palindromic subsequence of s.substring(i, j+1)
    // dp[i][j] = dp[i+1][j-1] + 2, if s(i) == s(j)
    // dp[i][j] = max(dp[i][j-1], dp[i+1][j]), if s(i) != s(j)
    // * For initialization, dp[i][i] = 1 (for every i)

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int i=s.length()-1; i>=0; i--) {
            dp[i][i] = 1;
            for (int j=i+1; j<s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // no need to check boundary of i+1 or j-1 here, why?
                    // when i is the last character, j = s.length, it won't enter the inner loop
                    // j = i+1, which is always greater than 0
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    // Can also be done in Top bottom recursive method with memoization
    // Use 2-d Integer array, initialized in null. So we know a cell is not calculated by checking null
    public int longestPalindromeSubseq_2(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }

    // returns the length of longest palindromic subsequence of s.substring(i, j+1) ---- i.e. memo[i][j]
    private int helper(String s, int i, int j, Integer[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        if (i > j)      return 0;
        if (i == j)     return 1;

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }
}
