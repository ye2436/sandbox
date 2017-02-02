/**
 * #55. Jump Game
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
public class JumpGame {
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int max = 0; // max so far
        for (int i=0; i<=max && i<nums.length; i++) {
            max = Math.max(max, nums[i]+i); // nums[i]+i - farthest it could reach at position i
        }
        if (max < nums.length-1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //int[] nums = {2,3,1,1,4};
        int[] nums = {0,1};
        System.out.println(canJump(nums));
    }
}
