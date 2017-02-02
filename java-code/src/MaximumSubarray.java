/**
 * #53. Maximum Subarray
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {


    /*
    * Kadane算法扫描一次整个数列的所有数值，在每一个扫描点计算以该点数值为结束点的子数列的最大和（正数和）。
    * 该子数列有可能为空，或者由两部分组成：以前一个位置为结束点的最大子数列、该位置的数值。可用如下代码表示，这里用到了Python:
    *   def max_subarray(A):
    *       max_ending_here = max_so_far = 0
    *       for x in A:
    *           max_ending_here = max(0, max_ending_here + x)
    *           max_so_far = max(max_so_far, max_ending_here)
    *       return max_so_far
    * 该问题的一个变种是：如果数列中含有负数元素，不允许返回长度为零的子数列。该问题可用如下代码解决：
    *   def max_subarray(A):
    *       max_ending_here = max_so_far = A[0]
    *       for x in A[1:]:
    *           max_ending_here = max(x, max_ending_here + x)
    *           max_so_far = max(max_so_far, max_ending_here)
    *       return max_so_far
    * */
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int local = nums[0];
        int global = nums[0];
        for (int i=1; i<nums.length; i++) {
            local = Math.max(nums[i], local + nums[i]);
            global = Math.max(global, local);
        }
        return global;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
