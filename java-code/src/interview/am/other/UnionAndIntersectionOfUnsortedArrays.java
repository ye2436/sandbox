package interview.am.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/find-union-and-intersection-of-two-unsorted-arrays/
 * Given two unsorted arrays that represent two sets (elements in every array are distinct), find union and intersection of two arrays.

 For example, if the input arrays are:
 arr1[] = {7, 1, 5, 2, 3, 6}
 arr2[] = {3, 8, 6, 20, 7}
 Then your program should print Union as {1, 2, 3, 5, 6, 7, 8, 20} and Intersection as {3, 6, 7}.
 Note that the elements of union and intersection can be printed in any order.
 */
public class UnionAndIntersectionOfUnsortedArrays {

    // Use Sorting and Searching
    // Union:
    // 1) Initialize union U as empty.
    // 2) Find smaller of m and n and sort the smaller array.
    // 3) Copy the smaller array to U.
    // 4) For every element x of larger array, do following
    // …….b) Binary Search x in smaller array. If x is not present, then copy it to U.
    // 5) Return U.
    //
    // Intersection:
    // 1) Initialize intersection I as empty.
    // 2) Find smaller of m and n and sort the smaller array.
    // 3) For every element x of larger array, do following
    // …….b) Binary Search x in smaller array. If x is present, then copy it to I.
    // 4) Return I.
    //
    // Time complexity of this method is min(mLogm + nLogm, mLogn + nLogn) which can also be written as O((m+n)Logm, (m+n)Logn).
    // This approach works much better than the previous approach when difference between sizes of two arrays is significant.



    public static List<Integer> intersection(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();

        // make nums1 always the smaller set
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        // sort the smaller set
        Arrays.sort(nums1);

        // loop through large set, for each num, binary search in smaller, if exists, add to result
        for (int num : nums2) {
            if (binarySearch(nums1, num, 0, nums1.length-1)) {
                res.add(num);
            }
        }

        return res;
    }

    public static List<Integer> union(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        // make sure nums1 is the smaller one
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        // sort the smaller and add to result
        Arrays.sort(nums1);
        for (int num : nums1) {
            res.add(num);
        }

        // find num that are in nums2 but not in nums1
        for (int num : nums2) {
            if (!binarySearch(nums1, num, 0, nums1.length-1)) {
                res.add(num);
            }
        }

        return res;
    }

    private static boolean binarySearch(int[] nums, int target, int l, int r) {
        while (l <= r) {
            int m = (l+r)/2;
            if (nums[m] == target) {
                return true;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(intersection(new int[]{7, 1, 5, 2, 3, 6}, new int[]{3, 8, 6, 20, 7}));
        System.out.println(union(new int[]{7, 1, 5, 2, 3, 6}, new int[]{3, 8, 6, 20, 7}));
    }

}
