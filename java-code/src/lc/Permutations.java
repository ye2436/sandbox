package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #46. lc.Permutations
 * Given a collection of distinct numbers, return all possible permutations.
 *
 * For example,
 * [1,2,3] have the following permutations:
 * [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 */
public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        helper(nums, new boolean[nums.length], new ArrayList<>(), res);
        //helper2(nums, new ArrayList<>(), res);
        return res;
    }

    private static void helper(int[] nums, boolean[] used, List<Integer> currList, List<List<Integer>> res) {
        if (currList.size() == nums.length) {
            res.add(new ArrayList<>(currList));
            return;
        }
        for (int i=0; i<nums.length; i++) {
            if(!used[i]) {
                currList.add(nums[i]);
                used[i] = true;
                helper(nums,used, currList, res);
                currList.remove(currList.size()-1);
                used[i] = false;
            }

        }
    }

    private static void helper2(int[] nums, List<Integer> currList, List<List<Integer>> res) {
        if (currList.size() == nums.length) {
            res.add(new ArrayList<>(currList));
            return;
        }
        for (int i=0; i<nums.length; i++) {
            if(!currList.contains(nums[i])) {
                currList.add(nums[i]);
                helper2(nums, currList, res);
                currList.remove(currList.size()-1);
            }

        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};

        System.out.println(permute(nums));
    }
}
