import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #140. Word Break II
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s
 * to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
 * Return all such possible sentences.
 * For example, given
 *  s = "catsanddog",
 *  dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class WordBreakII {
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();

        return res;
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }
}
