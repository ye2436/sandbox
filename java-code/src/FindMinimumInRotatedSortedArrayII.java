/**
 * 154. Find Minimum in Rotated Sorted Array II
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array may contain duplicates.
 */
public class FindMinimumInRotatedSortedArrayII {
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int l = 0;
        int r = nums.length-1;
        int min = nums[0];
        while (l < r-1) {
            int m = (l+r)/2;

            if (nums[l] < nums[m]) { // left half is ordered
                min = Math.min(min, nums[l]);
                l = m+1;
            } else if (nums[l] > nums[m]){ // right half is ordered
                min = Math.min(min, nums[m]);
                r = m-1;
            } else { // dup found
                l++;
            }
        }
        min = Math.min(min, Math.min(nums[l], nums[r]));
        return min;
    }


    public static void main(String[] args) {
        System.out.println(findMin(new int[]{4,5,6,7,8,9,10,1,2,3}));
    }
}
