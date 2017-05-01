package interview.li;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * #20. Valid Parentheses
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {
    public static boolean isValid(String s) {
        Map<Character, Character> charMap = new HashMap<>();
        // only need right to left
        charMap.put(')', '(');
        charMap.put(']','[');
        charMap.put('}','{');

        Stack<Character> stack = new Stack();
        for (int i=0; i<s.length(); i++) {
            if (!stack.empty() && charMap.get(s.charAt(i)) == stack.peek()) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{[}]"));
        System.out.println(isValid("()[]{}[]"));
    }
}
