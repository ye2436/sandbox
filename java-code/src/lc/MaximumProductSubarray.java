package lc;

/**
 * #152. Maximum Product Subarray
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int max_ending_here = nums[0];
        int min_ending_here = nums[0];
        int max_so_far = nums[0];
        for(int i=1; i<nums.length; i++) {
            int num = nums[i];
            int max_temp = max_ending_here;
            max_ending_here = Math.max(num, Math.max(max_temp * num, min_ending_here * num));
            min_ending_here = Math.min(num, Math.min(max_temp * num, min_ending_here * num));
            max_so_far = Math.max(max_so_far, max_ending_here);
        }

        return max_so_far;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,3,-2,4}));
    }
}
