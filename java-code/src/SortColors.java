import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 */
public class SortColors {
    // counting sort
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int zero = 0;
        int one = 0;
        for (int num : nums) {
            if (num == 0) {
                zero++;
            } else if (num == 1) {
                one++;
            }
        }
        int i=0;
        for (; i<zero; i++) {
            nums[i] = 0;
        }
        for (; i<one+zero; i++) {
            nums[i] = 1;
        }
        for (; i<nums.length; i++) {
            nums[i] = 2;
        }
    }

    // two pointers
    public static void sortColors2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int idx0 = 0;
        int idx1 = 0;
        for (int i=0;i<nums.length;i++) {
            // the last element of all processed so far (nums[i]) would be 2
            // if there is no 2's in curr list, idx0 and idx1 would push to the right and override it
            // and then writes 1, and 0 in order. so non-existent 1's could be overwritten.
            if (nums[i] == 0) {
                nums[i] = 2;
                nums[idx1++] = 1;
                nums[idx0++] = 0;
            } else if (nums[i] == 1) {
                nums[i] = 2;
                nums[idx1++] = 1;
            } // do nothing if 2
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,0,0,2,1,2,0,1};
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
