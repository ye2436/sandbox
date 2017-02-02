/**
 * #81. Search in Rotated Sorted Array II
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Write a function to determine if a given target is in the array.
 * The array may contain duplicates.
 */
public class SearchInRotatedSortedArrayII {
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = (l+r)/2;
            if (nums[m] == target) return true;
            if (nums[l] < nums[m]) { // left side is ordered
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else if (nums[l] > nums[m]) { // right side is ordered
                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            } else { // can't decide
                l++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        //int[] nums = {4,5,6,7,0,1,2};
        int[] nums = {3,1,2,3,3,3,3};
        System.out.println(search(nums,1));
    }
}
