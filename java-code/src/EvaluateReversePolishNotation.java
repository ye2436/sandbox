import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * #150. Evaluate Reverse Polish Notation
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 *  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class EvaluateReversePolishNotation {

    // RPN is also known as postfix notation.
    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;

        LinkedList<Integer> stack = new LinkedList<>();
        Set<String> operator = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        for (String token : tokens) {
            if (!operator.contains(token)) {
                stack.push(Integer.valueOf(token)); // operand
                continue;
            }
            // current token is operator
            if (stack.isEmpty()) return 0;
            int operand2 = stack.pop();
            if (stack.isEmpty()) return 0;
            int operand1 = stack.pop();
            if (token.equals("+")) {
                stack.push(operand1 + operand2);
            } else if (token.equals("-")) {
                stack.push(operand1 - operand2);
            } else if (token.equals("*")) {
                stack.push(operand1 * operand2);
            } else if (token.equals("/")) {
                stack.push(operand1 / operand2);
            }
        }

        return Integer.valueOf(stack.pop());
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}
