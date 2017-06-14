package interview.am.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * #78. Subsets
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subsets {

    // iterative
    // We start with an empty set. This is our smallest subset. And we expand from that.
    // Each time before we add new element, we make a copy of all existing sets.
    // Then we add the new element to the cloned sets, and they become new subsets.
    // Continue until we added all elements.
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // add empty subset
        //Arrays.sort(nums); // not necessary

        for (int i=0; i<nums.length; i++) {
            int size = res.size();
            for (int j=0; j<size; j++) {
                List<Integer> newSubset = new ArrayList<>(res.get(j));
                newSubset.add(nums[i]);
                res.add(newSubset);
            }
        }

        return res;
    }

    // recursive
    public static List<List<Integer>> subsets2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private static void helper(int[] nums, int index, List<Integer> currList, List<List<Integer>> res) {
        res.add(new ArrayList<>(currList));
        for (int i=index; i<nums.length; i++) {
            currList.add(nums[i]);
            helper(nums, i+1, currList, res);
            currList.remove(currList.size()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsets2(new int[]{1,2,3}));
    }
}

