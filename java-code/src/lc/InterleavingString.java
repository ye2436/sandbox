package lc;

/**
 * #97. Interleaving String
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 *  s1 = "aabcc",
 *  s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
public class InterleavingString {
    // dynamic programming
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        // dp[i][j]: if the first i+j characters of s3 could be formed by
        // the interleaving of the first i characters in s1 and the first j characters in s2
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for (int i=0; i<s1.length(); i++) {
            dp[i+1][0] = dp[i][0] && s1.charAt(i) == s3.charAt(i);
        }
        for (int j=0; j<s2.length(); j++) {
            dp[0][j+1] = dp[0][j] && s2.charAt(j) == s3.charAt(j);
        }
        for (int i=0; i<s1.length(); i++) {
            for (int j=0; j<s2.length(); j++) {
                dp[i+1][j+1] = (dp[i][j+1] && s1.charAt(i) == s3.charAt(i+j+1))
                        || (dp[i+1][j] && s2.charAt(j) == s3.charAt(i+j+1));
            }
        }

        return dp[s1.length()][s2.length()];
    }

    // dynamic programming - one dimension array
    public static boolean isInterleave_2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        String longStr;
        String shortStr;
        if (s1.length() > s2.length()) {
            longStr = s1;
            shortStr = s2;
        } else {
            longStr = s2;
            shortStr = s1;
        }

        // loop through longStr in outer loop, longStr index be row, shortStr index be column,
        // then use only 1-dimension array to record only the last row
        // dp[i]: if the first i+j characters of s3 could be formed by
        // the interleaving of the first i characters in longStr and the first j characters in shortStr
        boolean[] dp = new boolean[shortStr.length()+1];
        dp[0] = true;
        for (int i=0; i<shortStr.length(); i++) {
            dp[i+1] = dp[i] && shortStr.charAt(i) == s3.charAt(i);
        }
        for (int i=0; i<longStr.length(); i++) {
            dp[0] = dp[0] && longStr.charAt(i) == s3.charAt(i);
            for (int j=0; j<shortStr.length(); j++) {
                dp[j+1] = (dp[j+1] && longStr.charAt(i) == s3.charAt(i+j+1))
                        || (dp[j] && shortStr.charAt(j) == s3.charAt(i+j+1));
            }
        }

        return dp[shortStr.length()];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc","dbbca","aadbbcbcac"));
        System.out.println(isInterleave("aabcc","dbbca","aadbbbaccc"));
        System.out.println(isInterleave_2("aabcc","dbbca","aadbbcbcac"));
        System.out.println(isInterleave_2("aabcc","dbbca","aadbbbaccc"));
    }
}
