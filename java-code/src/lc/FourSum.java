package lc;

import java.util.*;

/**
 * #18. Four Sum
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note: The solution set must not contain duplicate quadruplets.
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * A solution set is:
 * [
 *  [-1,  0, 0, 1],
 *  [-2, -1, 1, 2],
 *  [-2,  0, 0, 2]
 * ]
 */
public class FourSum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;
        Arrays.sort(nums);

        for (int i=0; i<nums.length-3; i++) {
            if(i>0 && nums[i]==nums[i-1]) continue; // skip duplicate
            for (int j=i+1; j<nums.length-2; j++) {
                if (j>i+1 && nums[j]==nums[j-1]) continue; // skip

                int k=j+1;
                int l=nums.length-1;
                while (k<l) {

                    int sum = nums[i]+nums[j]+nums[k]+nums[l];
                    if (sum==target) {
                        res.add(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                        k++;
                        l--;

                        while (k<l && nums[k]==nums[k-1]) k++;
                        while (k<l && nums[l]==nums[l+1]) l--;

                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        //System.out.println(fourSum(new int[]{1,0,-1,0,-2,2}, 0));
        System.out.println(fourSum(new int[]{9,6,4,9,8,-2,4,0,6,-2,-8,6,-3,9,10}, 25));
    }
}
