package interview.fb.lc;

import java.util.Arrays;

/**
 * #88. Merge Sorted Array
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // merge in reverse order
        int last1 = m-1;
        int last2 = n-1;
        for (int i=m+n-1; i>=0; i--) {
            if (last2 < 0) break;
            if (last1 < 0) {
                while (last2 >= 0) {
                    nums1[last2] = nums2[last2];
                    last2--;
                }
                break;
            }

            if (nums1[last1] > nums2[last2]) {
                nums1[i] = nums1[last1];
                last1--;
            } else {
                nums1[i] = nums2[last2];
                last2--;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums1 = {7,9,15,20,21,26,37,0,0,0,0,0};
        int[] nums2 = {3,4,50};
        merge(nums1, 7, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
