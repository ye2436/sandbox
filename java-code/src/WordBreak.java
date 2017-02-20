import java.util.Arrays;
import java.util.List;

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


        return false;
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet","code")));
    }
}
