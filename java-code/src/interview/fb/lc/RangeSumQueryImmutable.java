package interview.fb.lc;

/**
 * 303. Range Sum Query - Immutable

 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3

 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.
 */
public class RangeSumQueryImmutable {

    // Similar to 325. Maximum Size Subarray Sum Equals k
    // keep an array sums with accumulated sums, where sums[i] indicates accumulated sum from index 0 to index i.
    // For each range [i, j], the range sum is sums[j] - sums[i-1] or sums[j] if i=0
    public class NumArray {

        int[] sums;

        public NumArray(int[] nums) {
            if (nums == null || nums.length == 0) return;
            sums = new int[nums.length];
            sums[0] = nums[0];
            for (int i=1; i<nums.length; i++) {
                sums[i] = sums[i-1] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            if (i==0) return sums[j];
            return sums[j] - sums[i-1];
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
}
