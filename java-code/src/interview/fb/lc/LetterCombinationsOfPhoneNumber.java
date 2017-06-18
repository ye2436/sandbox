package interview.fb.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * #17. Letter lc.Combinations of Phone Number
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsOfPhoneNumber {

    // string represents of each number. 0 & 1 are empty
    public static final String[] phoneButtons = new String[] {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    // iterative dfs - similar to subset (iterative)
    // In subsets, we start with an empty set. Each time before we add new element, we make a copy of all existing sets.
    // Here, we don't want to save the copies of intermediate results. We simply add new elements to existing sets.
    // * To store the intermediate results, we could use a queue. Each time, we get the sets from the queue,
    // add in new element, and push back the new sets.
    // * How do we know how many sets to poll from the queue? Since we are pushing new sets at the same time, how do we know
    // where to end this round? --- We could check set size. Say the total # of digits we have is 4, and after processing 2 rounds,
    // each set should have exactly 2 characters in it.
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return new LinkedList<>();
        LinkedList<String> queue = new LinkedList<>();
        queue.offer(""); // initialize with empty set
        for (int i=0; i<digits.length(); i++) {
            // get current digit and find its corresponding characters
            int digit = digits.charAt(i) - '0';
            char[] chars = phoneButtons[digit].toCharArray();

            // poll from the queue if the head set size in the queue equals to current index (otherwise, the sets are what we just pushed in)
            while (queue.peek().length() == i) {
                String s = queue.poll();

                // add new element to current set
                for (char c : chars) {
                    queue.offer(s+c);
                }
            }
        }

        return queue;
    }

    // recursive dfs
    public static List<String> letterCombinations_dfs(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        helper(digits, 0, new StringBuilder(), res);
        return res;
    }

    public static void helper(String digits, int index, StringBuilder sb, List<String> results) {
        if (index == digits.length()) {
            results.add(sb.toString());
            return;
        }
        int digit = digits.charAt(index) - '0';
        char[] characters = phoneButtons[digit].toCharArray();
        for (char c : characters) {
            sb.append(c);
            helper(digits, index+1, sb, results);
            sb.deleteCharAt(sb.length()-1);
        }
    }


    public static void main(String[] args) {

        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations_dfs("23"));
    }
}
