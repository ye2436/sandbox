import java.util.Arrays;

/**
 *  Follow up for "Remove Duplicates":
 *  What if duplicates are allowed at most twice?
 *  For example,
 *  Given sorted array nums = [1,1,1,2,2,3],
 *  Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 *  It doesn't matter what you leave beyond the new length.
 */
public class RemoveDuplicatesFromSortedArrayII {
    public static int removeDuplicates(int[] nums) {
        int p=0;
        int count=2;
        for (int i=0; i<nums.length; i++) {
            if (i>0 && nums[i] == nums[i-1]) {
                if (count > 0) {
                    nums[p++] = nums[i];
                    count--;
                }
            } else {
                nums[p++] = nums[i];
                count = 1;
            }
        }
        return p;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }
}
