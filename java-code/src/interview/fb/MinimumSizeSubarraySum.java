package interview.fb;

/**
 * 209. Minimum Size Subarray Sum Add to List

 Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

 For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.

 More practice:
 If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

 */
public class MinimumSizeSubarraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        int min = 0;
        int l = 0;
        int r = 0;
        int sum = 0;
        while (r < nums.length) {
            sum += nums[r];
            r++;

            while (sum >= s) {
                min = min == 0 ? r-l : Math.min(min, r-l);
                sum -= nums[l];
                l++;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }
}
