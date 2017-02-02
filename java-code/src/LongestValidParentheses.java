import java.util.Stack;

/**
 * #32. Longest Valid Parentheses
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class LongestValidParentheses {

    // use stack
    public static int longestValidParentheses(String s) {
        int max = 0;
        int len = s.length();
        if (s==null || len< 2) return max;

        Stack<Integer> stack = new Stack(); // record unmatched paren's index
        for (int i=0; i<len; i++) {
            // check if stack's top is (
            if (s.charAt(i) == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
                if (stack.isEmpty()) { // meaning - everything between 0 and i are valid parentheses
                    max = i+1;
                } else { // meaning - everything between stack.peek() and i are valid
                    max = Math.max(max, i-stack.peek()); // i+1-stack.peek-1
                }
            } else { // current char is ( or current char is ) but the stack's top is not (
                stack.push(i);
            }
        }

        return max;
    }

    // dynamic programming
    public static int longestValidParentheses2(String s) {
        int max = 0;
        int len = s.length();
        if (s==null || len< 2) return max;

        int[] dp = new int[len]; // records the length of the longest parentheses starting from position i
        for (int i=len-2; i>=0; i--) {
            if (s.charAt(i) == ')') {
                dp[i] = 0;
            } else {
                int end = i+dp[i+1]+1;
                if (end<len && s.charAt(end) == ')') {
                    dp[i] = dp[i+1]+2;
                    if (end+1 < len) { // after adding ), check if there is trailing valid paretheses
                        dp[i] += dp[end+1];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        //String s = ")(((((()())()()))()(()))(";
        String s = "(()";

        System.out.println(longestValidParentheses(s));

        System.out.println(longestValidParentheses2(s));
    }
}
