import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #40. Combination Sum II
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * Note:
 *  - All numbers (including target) will be positive integers.
 *  - The solution set must not contain duplicate combinations.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 * [
 *  [1, 7],
 *  [1, 2, 5],
 *  [2, 6],
 *  [1, 1, 6]
 * ]
 */
public class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        helper2(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private static void helper2(int[] candidates, int start, int target, List<Integer> currList, List<List<Integer>> res) {
        //if(target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(currList)); // add new arraylist instead of currList, because currList will get reset in the following loops
            return;
        }
        for (int i=start; i<candidates.length; i++) {
            if (i>start && candidates[i] == candidates[i-1]) {
                continue; // avoid duplicates
            }
            if (candidates[i] <= target) {
                currList.add(candidates[i]);
                helper2(candidates, i+1, target-candidates[i], currList, res); // i+1 is the new start
                currList.remove(currList.size()-1);
            } else {
                // optimize: the following are all larger than target. no possible solution exists.
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum2(candidates, 8));
    }
}
