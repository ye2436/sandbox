package interview.fb.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators

 Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

 Examples:
 "123", 6 -> ["1+2+3", "1*2*3"]
 "232", 8 -> ["2*3+2", "2+3*2"]
 "105", 5 -> ["1*0+5","10-5"]
 "00", 0 -> ["0+0", "0-0", "0*0"]
 "3456237490", 9191 -> []

 */
public class ExpressionAddOperators {

    // backtracking
    // At each possible place, insert an operator, calculate the evaluated value.
    // * For +/-, we could simply add to or subtract from the value so far. But for *, we need to know what to multiply with.
    //   For that, we could save off the last number from recursion.
    // * Ending Condition - When the index reached the end the of num String && the evaluated value equals to target
    // * Need to skip substring start with 0, but we don't want to skip 0.
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        helper(num, target, 0, "", 0, 0, res);
        return res;
    }

    // expression: store the expression so far
    // value: evaluated value so far
    // lastNum: last number
    // both value & lastNum are long type to avoid integer overflow
    private void helper(String num, int target, int index, String expression, long value, long lastNum, List<String> res) {
        if (index == num.length()) {
            if (value == target) {
                res.add(expression);
            }
            return;
        }
        for (int i = index; i<num.length(); i++) {
            if (num.charAt(index) == '0' && i != index) break; // skip substring starts with 0 but not 0 itself
            long currNum = Long.valueOf(num.substring(index, i+1));
            if (index == 0) { // curr substring is starting from the 0 index
                helper(num, target, i+1, expression+currNum, currNum, currNum, res);
            } else {
                helper(num, target, i+1, expression + "+" + currNum, value + currNum, currNum, res);
                helper(num, target, i+1, expression + "-" + currNum, value - currNum, -currNum, res);
                helper(num, target, i+1, expression + "*" + currNum, value - lastNum + lastNum * currNum, lastNum * currNum, res);
            }
        }
    }
}
