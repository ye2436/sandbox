package interview.fb.lc;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array
 *
 Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

 Example 1:
 Input: [0,1]
 Output: 2
 Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 Example 2:
 Input: [0,1,0]
 Output: 2
 Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

 Note: The length of the given binary array will not exceed 50,000.
 */
public class ContiguousArray {

    // Similar as 325. Maximum Size Subarray Sum Equals k, use hash map to keep track 0f (# of 1s - # of 0s) ending at index i
    // for any index i, the max length could be i+1 if count(i) == 0
    // otherwise, try to see if there is any index j before i where count(j) = count(i), which means from j+1 to i the 0/1 are matched
    // note that -- we should only add new counts to map and do not override existing ones, so that we can get the longest length
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int max = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                max = i+1;
            } else if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            }
            if (!map.containsKey(count)) {
                map.put(count, i);
            }
        }

        return max;
    }
}
