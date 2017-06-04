package interview.am;

import java.util.ArrayList;
import java.util.List;

/**
 * #78. lc.Subsets
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



    // non-recursive
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
        return helper(nums, nums.length-1);
    }

    private static List<List<Integer>> helper(int[] nums, int index) {
        if (index == -1) {
            List<Integer> emptyList = new ArrayList<>();
            List<List<Integer>> res = new ArrayList<>();
            res.add(emptyList);
            return res;
        }
        List<List<Integer>> res = helper(nums, index-1); // get all subsets from last index
        int size = res.size();
        for (int i=0; i<size; i++) {
            List<Integer> newSubset = new ArrayList<>(res.get(i));
            newSubset.add(nums[index]);
            res.add(newSubset);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(subsets2(new int[]{1,2,3}));
    }
}

