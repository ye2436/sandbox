import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 *  [2,4],
 *  [3,4],
 *  [2,3],
 *  [1,2],
 *  [1,3],
 *  [1,4],
 * ]
 */
public class Combinations {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n==0 || k>n) return res;
        helper(n, k, 1, new ArrayList<>(), res);
        return res;
    }

    private static void helper(int n, int k, int start, List<Integer> currList, List<List<Integer>> res) {
        if (currList.size()==k) {
            res.add(new ArrayList<>(currList));
            return;
        }
        for (int i=start; i<=n; i++) {
            currList.add(i);
            helper(n, k, i+1, currList, res);
            currList.remove(currList.size()-1);
        }

    }

    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }
}