import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {


    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        int min_diff = Integer.MAX_VALUE;
        int closest = 0;
        Arrays.sort(nums);

        for (int i=0; i<nums.length-2; i++) {
            int j=i+1;
            int k=nums.length-1;

            while (j<k) {
                int sum = nums[i]+nums[j]+nums[k];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    if(target-sum < min_diff) {
                        min_diff = target-sum;
                        closest = sum;
                    }

                    j++;
                } else {
                    if(sum-target < min_diff) {
                        min_diff = sum-target;
                        closest = sum;
                    }
                    k--;
                }
            }
        }

        return closest;
    }

    public static void main(String[] args) {

        System.out.println(threeSumClosest(new int[]{-1,2,1,-4},1));

        System.out.println(threeSumClosest(new int[]{-3,-2,-5,3,-4},-1));
    }
}
