import java.util.ArrayList;
import java.util.List;

/**
 * #131. Palindrome Partitioning
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 * [
 *  ["aa","b"],
 *  ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

    // solution 1
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        helper(s, 0, new ArrayList<>(), res);
        return res;
    }

    private static void helper(String s, int index, List<String> currList, List<List<String>> res) {
        if (index >= s.length()) {
            res.add(new ArrayList<>(currList));
            return;
        }

        for (int i=index+1; i<=s.length(); i++) {
            String subString = s.substring(index, i);
            if (isPalindrome(subString)) {
                currList.add(subString);
                helper(s, i, currList, res);
                currList.remove(currList.size()-1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        while (l<r) { // no need to check when l=r, the character in the center is always a palindrome
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }

    // another solution - use a dictionary to record if a substring is a palindrome
    private static boolean[][] getPalindromeDict(String s) {
        // dynamic programing
        // dict[i][j]: if s.subString(i,j+1) is a palindrome
        boolean[][] dict = new boolean[s.length()][s.length()];
        for (int i=s.length()-1; i>=0; i--) {
            for (int j=i; j<s.length(); j++) { // all false where j<i
                dict[i][j] =  s.charAt(i) == s.charAt(j) && (j-i<=2 || dict[i+1][j-1]);
            }
        }
        return dict;
    }

    private static void helper_2(String s, int index, List<String> currList, List<List<String>> res, boolean[][] dict) {
        if (index >= s.length()) {
            res.add(new ArrayList<>(currList));
            return;
        }

        for (int i=index; i<s.length(); i++) {
            String subString = s.substring(index, i+1);
            if (dict[index][i]) { // if s.subString(index,i+1) is palindrome
                currList.add(subString);
                helper_2(s, i+1, currList, res, dict);
                currList.remove(currList.size()-1);
            }
        }
    }

    public static List<List<String>> partition_2(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        helper_2(s, 0, new ArrayList<>(), res, getPalindromeDict(s));
        return res;
    }

    public static void main(String[] args) {
        System.out.println(partition("aab"));
        System.out.println(partition_2("aab"));
    }
}
