package interview.am.other;

import java.util.Arrays;

/**
 * Given a list of integers and a window size, return a new list of integers where each integer is the sum of all integers
 * in the kth window of the input list. The kth window of the input list is
 * the integers from index k to index k + window size - 1(inclusive).
 */
public class SlidingWindowSum {

    public static int[] getWindowSums(int[] nums, int k) { // k is the window size
        if (nums == null || nums.length < k) return new int[]{};
        // define return array
        int[] sums = new int[nums.length - k + 1];

        // initiate with 1st window sum
        int sum = 0;
        int i=0;
        while (i<k) {
            sum += nums[i];
            i++;
        }
        sums[0] = sum;

        // keep a running sum. remove the left most element out of the window and add in the right most element.
        // we only need to keep current i as window end -- window start is i - k + 1
        // So sum = sum - nums[i-k] + nums[i]
        while (i < nums.length) {
            sums[i-k+1] = sums[i-k] - nums[i-k] + nums[i];
            i++;
        }
        return sums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getWindowSums(new int[]{1, 2, 3, 4, 5, 6}, 3)));
    }
}
