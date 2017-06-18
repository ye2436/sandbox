package interview.fb.lc;

/**
 * 33. Search in Rotated Sorted Array
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */
public class SearchInRotatedSortedArray {
    // brute force
    public static int search(int[] nums, int target) {
        if (nums==null || nums.length==0) return -1;

        int first = nums[0];
        if (target == first) {
            return 0;
        } else if (target > first) {
            // search from start
            int i = 0;
            while (i<nums.length) {
                if (target == nums[i]) return i;
                else if (target < nums[i]) return -1;
                i++;
            }
            return -1;
        } else {
            // search from end
            int i=nums.length-1;
            while(i>=0) {
                if (target == nums[i]) return i;
                else if (target > nums[i]) return -1;
                i--;
            }
            return -1;
        }
    }

    // binary search, iterative
    // determine which part is ordered first
    public static int search2(int[] nums, int target) {
        if (nums==null || nums.length==0) return -1;

        int l = 0;
        int r = nums.length-1;
        while (l <= r) {
            int m = (l+r)/2;

            if (nums[m] == target) return m;
            if (nums[l] <= nums [m]) { // left half is ordered
                if (nums[l] <= target && target < nums[m]) {
                    r = m-1;
                } else {
                    l = m+1;
                }
            } else { // right half is ordered
                if (nums[m] < target && target <= nums[r]) {
                    l = m+1;
                } else {
                    r = m-1;
                }
            }
        }

        return -1;
    }

    // binary search, recursive
    public static int search3(int[] nums, int target) {
        if (nums==null || nums.length==0) return -1;

        return binarySearch(nums, target,0,nums.length-1);
    }

    public static int binarySearch(int[] nums, int target, int l, int r) {
        int m = (l+r)/2;
        if (nums[m] == target) return m;
        if (nums[l] <= nums[m]) { // left half is ordered
            if (nums[l] <= target && target < nums[m]) {
                return binarySearch(nums, target, l, m-1);
            } else {
                return binarySearch(nums, target, m+1, r);
            }
        } else { // right half is ordered
            if (nums[m] < target && target <= nums[r]) {
                return binarySearch(nums, target, m+1, r);
            } else {
                return binarySearch(nums, target, l, m-1);
            }
        }
    }

    public static void main(String[] args) {

        //int[] nums = new int[] {4,5,6,7,0,1,2};
        int[] nums = new int[] {1,2};
        System.out.println(search3(nums,2));
    }
}
