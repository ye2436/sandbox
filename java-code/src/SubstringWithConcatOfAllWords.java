import java.util.ArrayList;
import java.util.List;

/**
 *  30. Substring with Concatenation of All Words
 *  You are given a string, s, and a list of words, words, that are all of the same length.
 *  Find all starting indices of substring(s) in s that is a concatenation of each word in words
 *  exactly once and without any intervening characters.
 *  For example, given:
 *  s: "barfoothefoobarman"
 *  words: ["foo", "bar"]
 *  You should return the indices: [0,9]. (order does not matter).
 */
public class SubstringWithConcatOfAllWords {

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<>();
        if (words == null || words.length == 0) return results;

        int n = words[0].length();
        for (int i=0; i<s.length(); i++) {
            for (int j=0; j<n; j++) {

            }
        }

        return results;
    }

    public static void containsWord(int start) {

    }

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }
}
