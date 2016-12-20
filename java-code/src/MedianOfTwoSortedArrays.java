/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */
public class MedianOfTwoSortedArrays {

    /**
     *记得数据结构那本书就说个把两个排序数组合并为一个排序数据的问题。
     *就是同时遍历两个数组，每次取两个数组开头小的那个放入新的数组，时间复杂度为 O(m+n)。
     *解法一，直接遍历，复杂度O(m+n)
     */
    public double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        return 0d;
    }


}
