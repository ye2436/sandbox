package lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * #90. lc.Subsets II
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * [
 *  [2],
 *  [1],
 *  [1,2,2],
 *  [2,2],
 *  [1,2],
 *  []
 * ]
 */
public class SubsetsII {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        List<Integer> emptySet = new ArrayList<>();
        res.add(emptySet);
        int start = 0;
        for (int i=0; i<nums.length; i++) {
            int size = res.size(); // original size before new subsets are added
            for (int j=start; j<size; j++) {
                List<Integer> newSubset = new ArrayList<>(res.get(j));
                newSubset.add(nums[i]);
                res.add(newSubset);
            }
            if (i<nums.length-1 && nums[i] == nums[i+1]) {
                start = size; // ignore the front part of subsets
            } else {
                start = 0;
            }

        }

        return res;
    }

    public static void main(String[] args) {
            System.out.println(subsetsWithDup(new int[]{2,2,2}));
    }
}
