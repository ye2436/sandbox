package interview.am.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 532. K-diff Pairs in an Array

 Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

 Example 1:
 Input: [3, 1, 4, 1, 5], k = 2
 Output: 2
 Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 Although we have two 1s in the input, we should only return the number of unique pairs.
 Example 2:
 Input:[1, 2, 3, 4, 5], k = 1
 Output: 4
 Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 Example 3:
 Input: [1, 3, 1, 5, 4], k = 0
 Output: 1
 Explanation: There is one 0-diff pair in the array, (1, 1).
 Note:
 The pairs (i, j) and (j, i) count as the same pair.
 The length of the array won't exceed 10,000.
 All the integers in the given input belong to the range: [-1e7, 1e7].
 */
public class KDiffPairsInArray {

    // Solution 1 - Two Pointers - Time O(nlogn), Space O(1)
    // First sort the array. Use two pointers to check diffs. At any given time, we only need to move 1 pointer and keep the other unchanged.
    // Since we are finding the diff, if diff(i,j) < k, we move j; if diff(i,j) >= k, we move i.
    // Pointer i - range : [0,n-2]
    // Pointer j - range : initially start at 1, and then [max(j, i+1), n-1]
    // * why keep j after i is updated? when nums[i] and nums[j] diff becomes larger than k, we move only i to the right, and keep j unchanged.
    // * why max(j,i+1)? when we move i, it can be moved multiple times in the case of dups, it may exceed j. we want to keep j on the right of i
    // * we check dup at the end of each loop of i, so that the first of all dups will be taken, and the rest not.
    public static int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k<0) return 0;
        Arrays.sort(nums);
        int count = 0;
        int i=0;
        int j=1;

        for (; i<nums.length-1; i++) {

            for (j = Math.max(j, i+1); j<nums.length; j++) {
                if ((long)(nums[j] - nums[i]) >= k) break;
            }

            // Now there are 3 possibilities
            // (1) we found a diff that is equal to k
            // (2) the diff is greater than k
            // (3) we looped through all possible j and reached the end (j = nums.length)

            // in situation (1), increment the count
            if (j< nums.length && (long)(nums[j] - nums[i]) == k) {
                count++;
            }

            // no matter which scenario, we need to increment i because we have searched all possible from this round
            // note that last possible round we check the very last 2 elements of the array. (including j)
            while (i<nums.length-1 && nums[i] == nums[i+1]) { // skip dups
                i++;
            }
        }

        return count;
    }

    // Solution 2 - Hash Map - Time O(n), Space O(n)
    // First get counts of each number.
    // Then loop through the map - if k=0, find how many numbers have counts >=2
    // else, for each num , find if exists key num+k (use smaller to find larger, hence avoid dup)
    public static int findPairs2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k<0) return 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num)+1);
            } else {
                map.put(num, 1);
            }
        }

        for (Integer num : map.keySet()) {
            if (k == 0) {
                if (map.get(num) >=2) {
                    count++;
                }
            } else {
                if (map.containsKey(num+k)) {
                    count++;
                }
            }
        }

        return count;
    }


    public static void main(String[] args) {
        System.out.println(findPairs(new int[] {1,3,1,5,4}, 0)); // expect 1
        System.out.println(findPairs(new int[] {1,1,1,1,1}, 0)); // expect 1
    }
}
