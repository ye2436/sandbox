package interview.fb.lc;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses

 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]

 */
public class RemoveInvalidParentheses {

    // BFS Solution
    // Use BFS to find if a string is valid, remove 1 left/right paren in each round (level).
    // With BFS, we are guaranteed to have removed min numbers of invalid parens when we found the valid string (need all possible results from that level)
    // * How to remove? What to remove? Each round, check if a string is a valid. If not, start from left to right, remove 1 paren at a time.
    //   Add intermediate result into the queue1(that indicates 1 removal is done).
    // * When to end? When a valid string is found. We add it to res, and finish this level, add all other valid ones.
    public static List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (isValid(str)) {
                res.add(str);
                found = true;
            }
            if (found) continue; // once found, only valid ones are being looked at. no new strings to be added.

            // from here, we process the invalid ones by removing 1 paren.
            for (int i=0; i<str.length(); i++) {
                if (str.charAt(i) != '(' && str.charAt(i) != ')') continue; // skip other characters
                // if it's a paren, remove it and add to queue
                String subStr = str.substring(0,i) + str.substring(i+1);
                if (!visited.contains(subStr)) {
                    queue.offer(subStr);
                    visited.add(subStr);
                }
            }
        }

        return res;
    }

    private static boolean isValid(String s) {
        int count = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')' && count-- == 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
    }
}
