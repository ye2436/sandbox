import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * #139. Word Break
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into
 * a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
 * For example, given
 *  s = "leetcode",
 *  dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return true;

        Set<String> dict = new HashSet<>(wordDict);
        // dp[i]: if the first i characters in s can be broken up into words from dict
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        // dp[i] = dp[j] && s.subString(j, i) is in dict, where 0<=j<i
        for (int i=1; i<=s.length(); i++) {
            for (int j=i-1; j>=0; j--) { // descending j is faster
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet","code")));
    }
}
