package lc;

import java.util.Arrays;

/**
 * #41. First Missing Positive
 *  Given an unsorted integer array, find the first missing positive integer.
 *  For example,
 *  Given [1,2,0] return 3,
 *  and [3,4,-1,1] return 2.
 *  Your algorithm should run in O(n) time and uses constant space.
 */
public class FirstMissingPositive {
    // use array's index to record number. let nums[i] = i+1
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        // move nums[i] to nums[nums[i]-1]
        for (int i=0; i<nums.length; i++) {
            // only record positive numbers. ignore numbers that are larger than nums.length.
            // skip nums that are already in position to avoid dead loops
            if (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i]-1] != nums[i]) {
                // swap it to position nums[i]-1
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
                i--; // stay in the current position so the newly swapped in number will be processed
            }
        }

        // find first number that doesn't meet nums[i] = i+1
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }

        return nums.length+1;
    }

    public static int mySolution(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        Arrays.sort(nums);
        boolean positive = false;
        for (int i=0; i<nums.length; i++) {
            int num = nums[i];
            if (num <= 0) continue;
            if (!positive) {
                if(num > 1) return 1;
                positive = true;
            }
            if (i>0 && nums[i-1]>0 && nums[i]-nums[i-1]>1) {
                return nums[i-1]+1;
            }
        }
        if (!positive) return 1;
        return nums[nums.length-1]+1;
    }

    public static void main(String[] args) {
        int[] nums = {-1};
        System.out.println(firstMissingPositive(nums));
    }
}
