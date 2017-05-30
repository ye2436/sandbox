package interview.fb;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k

 Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

 Note:
 The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

 Example 1:
 Given nums = [1, -1, 5, -2, 3], k = 3,
 return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

 Example 2:
 Given nums = [-2, -1, 2, 1], k = 1,
 return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

 Follow Up:
 Can you do it in O(n) time?
 */
public class MaximumSizeSubarraySumEqualsK {
    // accumulate sum at each index (from index 0), store the values as keys in a hash map, sum -> ending index
    // as for max subarray ending at a particular index j, it could start from index 0, or any other previous index.
    // we need to find the smallest start index i.
    // If such index i exists, the sum from i to j is sum(j) - sum(i-1) or sum(j) if i=0. we could find out the sum from hash map
    // The length would be j - (i-1), which could be get from the value of the hash map.

    public static int maxSubArrayLen(int[] nums, int k) {
        int maxSize = 0;
        Map<Integer, Integer> sums = new HashMap<>();
        int sum = 0;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                maxSize = i+1;
            } else if (sums.containsKey(sum-k)) {
                // we are trying to find where sum(i) - sum(x) = k, then from x+1 to i is the subarray with the sum of k.
                // length is i - (x+1) +1 = i - x
                maxSize = Math.max(maxSize, i - sums.get(sum-k));
            }
            if (!sums.containsKey(sum)) {
                sums.put(sum, i); // do not override previous index with same sum value. always keep the left most index so we get max length of subarray
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
    }
}
