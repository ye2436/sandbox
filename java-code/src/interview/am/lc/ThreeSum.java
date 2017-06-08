package interview.am.lc;

import java.util.*;

/**
 * #15. 3 Sum
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Note: The solution set must not contain duplicate triplets.
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 *  [-1, 0, 1],
 *  [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);

        for (int i=0; i<nums.length-2; i++) {
            if (i>0 && nums[i] == nums[i-1]) continue; // avoid duplicate

            int j=i+1;
            int k = nums.length-1;
            while (j<k) {
                if (nums[i]+nums[j]+nums[k]==0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    // avoid duplicate
                    while (j<k && nums[j] == nums[j-1]) {
                        j++;
                    }
                    while (j<k && nums[k] == nums[k+1]) {
                        k--;
                    }

                } else if (nums[i]+nums[j]+nums[k]<0) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return res;
    }

    // need to return all possible combos
    /*private static List<List<Integer>> twoSum (List<Integer> nums, int target){
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i=0; i<nums.size(); i++) {
            if (set.contains(target - nums.get(i))) {
                if (!set.contains(nums.get(i))) { // to make sure the same combo with a different order is not added
                    res.add(Arrays.asList(nums.get(i), target-nums.get(i)));
                }
            }
            set.add(nums.get(i));
        }
        return res;
    }*/

    public static void main(String[] args) {
        //System.out.println(threeSum(new int[]{1,2,3,4,5}));
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,4,3));
        System.out.println(list);
        list.remove(3);
        System.out.println(list);


        Set<List<Integer>> set = new HashSet<>();
        set.add(Arrays.asList(1,2,3));
        set.add(Arrays.asList(4,5,6));
        System.out.println(set);
        set.add(Arrays.asList(3,2,1));
        set.add(Arrays.asList(1,2,3));
        System.out.println(set);

        //System.out.println(twoSum(Arrays.asList(1,2,3,4,5,-1,1,-1,-2), 0));
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
