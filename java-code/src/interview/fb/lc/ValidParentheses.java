package interview.fb.lc;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * #20. Valid Parentheses
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParentheses {

    // use stack, whenever encounter a left paren, push a right paren
    // otherwise compare curr char with stack top, if not same, them invalid
    public static boolean isValid_2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }
        return stack.isEmpty();
    }

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
