import java.util.Arrays;

/**
 *  Given an unsorted integer array, find the first missing positive integer.
 *  For example,
 *  Given [1,2,0] return 3,
 *  and [3,4,-1,1] return 2.
 *  Your algorithm should run in O(n) time and uses constant space.
 */
public class FirstMissingPositive {
    public static int firstMissingPositive(int[] nums) {
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
