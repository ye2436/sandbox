/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * Note:
 * You can assume that you can always reach the last index.
 */
public class JumpGameII {
    public static int jump(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        int[] steps = new int[nums.length]; // min steps to index i
        // init
        steps[0] = 0;
        for (int i=1; i<nums.length; i++) {
            steps[i] = Integer.MAX_VALUE;
        }

        for (int i=1; i<nums.length; i++) {
            for (int j=i-1; j>=0; j--) {
                if (steps[j]!=Integer.MAX_VALUE && j+nums[j] >=i) { // current j could reach i in 1 step
                    steps[i] = Math.min(steps[i], steps[j] + 1);
                }
            }
        }

        return steps[nums.length-1];
    }

    public static int jump2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int reach = 0; // current farthest index we could reach
        int lastReach = 0; // farthest index we could reach from last time/index
        int step = 0;
        for (int i=0; i<=reach && i<nums.length; i++) {
            if (i > lastReach) {
                step++;
                lastReach = reach;
            }
            reach = Math.max(reach, nums[i]+i); // nums[i]+i - farthest it could reach at position i
        }

        return step;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
