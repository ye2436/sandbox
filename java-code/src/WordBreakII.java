import java.util.*;

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
    // 直接brute force用递归解
    // 用动态规划的代码复杂度要远远高于brute force的解法，而且本质来说并没有很大的提高，甚至空间上还是一个暴涨的情况。
    // 所以这道题来说并不是一定要用动态规划，有一个朋友在面Amazon时遇到这道题，面试官并没有要求动态规划，用brute force即可，
    // 不过两种方法时间上和空间上的优劣还是想清楚比较好，面试官可能想听听理解。实现的话可能主要是递归解法。
    // 还有一点需要指出的是，上面的两个代码放到LeetCode中都会超时，原因是LeetCode中有一个非常tricky的测试case，
    // 其实是不能break的，但是又很长，出现大量的记录和回溯，因此一般通过这个的解法是把Word Break先跑一遍，判断是不是能break，
    // 如果可以再跑上面的代码。
    public static List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        helper(s, 0, new ArrayList<>(), res, new HashSet<>(wordDict));
        return res;
    }

    private static void helper(String s, int index, List<String> currList, List<String> res, Set<String> dict) {
        if (index == s.length()) {
            res.add(String.join(" ", currList));
            return;
        }
        for (int i=index; i<s.length(); i++) {
            String word = s.substring(index, i+1);
            if (dict.contains(word)) {
                currList.add(word);
                helper(s, i+1, currList, res, dict);
                currList.remove(currList.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }
}
