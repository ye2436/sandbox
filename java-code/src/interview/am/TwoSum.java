package interview.am;

import java.util.HashMap;
import java.util.Map;

/**
 * #1. Two Sum
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class TwoSum {

    // naive solution - brute force, check all 2 pairs. Time O(n^2)

    // one pass solution - as we loop through the array, record the nums in a hash table. so we can later access it with O(1) time
    // Time: O(n), Space: O(n)
    public int[] solution(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
