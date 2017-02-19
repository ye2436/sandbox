/**
 * #132. Palindrome Partitioning II
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitioningII {
    public static int minCut(String s) {
        if (s == null && s.length() == 0) return 0;

        boolean[][] dict = getPalindromeDict(s);
        int[] dp = new int[s.length()+1]; // dp[i]: number of partitions in first i characters
        for (int i=1; i<=s.length();i++) {
            dp[i] = i; // number of i characters can be partitioned into at most i parts
            for (int j=0; j<i; j++) {
                if (dict[j][i-1]) { // if s.subString(j, i) is palindromic
                    dp[i] = Math.min(dp[i], dp[j]+1);
                }
            }
        }

        return dp[s.length()]-1;
    }

    private static boolean[][] getPalindromeDict(String s) {
        boolean[][] dict = new boolean[s.length()][s.length()]; // if s.subString(i, j+1) is palindromic
        for (int i=s.length()-1; i>=0; i--) {
            for (int j=0; j<s.length(); j++) {
                dict[i][j] = s.charAt(i) == s.charAt(j) && (j-i<=2 || dict[i+1][j-1]);
            }
        }
        return dict;
    }

    public static void main(String[] args) {
        System.out.println(minCut("aab"));
    }
}
