import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 *  - All numbers (including target) will be positive integers.
 *  - The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * [
 *  [7],
 *  [2, 2, 3]
 * ]
 */
public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        helper(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private static void helper(int[] candidates, int start, int target, List<Integer> currList, List<List<Integer>> res) {
        // if(target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(currList)); // add new arraylist instead of currList, because currList will get reset in the following loops
            return;
        }
        for (int i=start; i<candidates.length; i++) {
            if (candidates[i] <= target) {
                currList.add(candidates[i]);
                helper(candidates, i, target - candidates[i], currList, res); // current i is the new start
                currList.remove(currList.size() - 1);
            } else {
                // optimize: the following are all larger than target. no possible solution exists.
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        System.out.println(combinationSum(candidates, 7));
    }
}
