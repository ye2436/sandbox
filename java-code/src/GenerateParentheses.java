import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 *  "((()))",
 *  "(()())",
 *  "(())()",
 *  "()(())",
 *  "()()()"
 * ]
 */
public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        Set<String> res = new HashSet<>();
        if (n<=0) return new ArrayList<>(res);
        helper(n,1, new StringBuilder(), res);

        return new ArrayList<>(res);
    }

    public static void helper(int n, int step, StringBuilder sb, Set<String> results) {
        if (step == 1) {
            sb.append("()");
        }
        if (step == n) {
            results.add(sb.toString());
            return;
        }
        for (int i=0; i<sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                // insert before '('
                sb.insert(i,"()");
                helper(n,step+1, sb, results);
                sb.delete(i,i+2);

                // insert after '('
                sb.insert(i+1,"()");
                helper(n,step+1, sb, results);
                sb.delete(i+1,i+3);
            }
        }
    }

    public static List<String> generateParenthesis_2(int n) {
        List<String> res = new ArrayList<>();
        helper_2(n,n,"", res);
        return res;
    }

    /* left and right represents the remaining number of ( and ) that need to be added.
    When left > right, there are more ")" placed than "(". Such cases are wrong and the method stops. */
    public static void helper_2(int left, int right, String s, List<String> results) {
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            results.add(s);
            return;
        }

        // use String instead of StringBuilder to avoid deleting last after returns
        if (left>0) {
            helper_2(left-1,right, s+"(", results);
        }
        if (right>0) {
            helper_2(left,right-1,s+")",results);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis_2(3));

    }
}
