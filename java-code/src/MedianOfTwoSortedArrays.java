/**
 * #4. Median of Two Sorted Arrays
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */
public class MedianOfTwoSortedArrays {

    /**
     *记得数据结构那本书就说个把两个排序数组合并为一个排序数据的问题。
     *就是同时遍历两个数组，每次取两个数组开头小的那个放入新的数组，时间复杂度为 O(m+n)。
     *解法一，直接遍历，复杂度O(m+n)
     * Merge Sort
     */
    public static double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i = 0;
        int j = 0;
        int curr = 0;
        int prev = 0;
        double median;

        if (m+n < 2) {
            if (m==0 && n==0) {
                return 0d;
            } else if (m==1) {
                return nums1[0];
            } else {
                return nums2[0];
            }
        }

        // if m+n is even, then median is the avg of (m+n)/2 - 1 and (m+n)/2. (starting from 0)
        // if m+n is odd, then median is (m+n-1)/2. (starting from 0). or just the integer part of (m+n)/2
        while (i+j <= (m+n)/2) {
            prev = curr;
            if (i >= m) { // nums1 has run out
                curr = nums2[j];
                j++;
            } else if (j >= n) { // nums2 has run out
                curr = nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                curr = nums1[i];
                i++;
            } else {
                curr = nums2[j];
                j++;
            }
        }

        if ((m+n)%2 == 0) {
            median = (prev + curr) / 2.0;
        } else {
            median = curr;
        }

        return median;
    }

    public static double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
        double median;
        int m = nums1.length;
        int n = nums2.length;
        if ((m+n)%2 == 0) { // find kth, starting from 1
            int a = findKth(nums1,0,nums2,0,(m+n)/2);
            int b = findKth(nums1,0,nums2,0,(m+n)/2+1);
            median = (a + b) / 2.0;
        } else {
            median = findKth(nums1,0,nums2,0,(m+n)/2+1);
        }

        return median;
    }

    private static int findKth(int[] nums1, int start1,int[] nums2, int start2, int k) {

        //always assume that m is equal or smaller than n
        /*if (nums1.length > nums2.length)
            return findKth(nums2, start2, nums1, start1, k);*/

        // if nums1 runs out, find the kth number in nums2 from start point 2.
        // likewise if nums2 runs out.
        if (start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        } else if (start2 >= nums2.length) {
            return nums1[start1 + k - 1];
        }

        // if k==1 return the smaller one of nums1 and nums2
        if (k==1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int a = Math.min(start1 + k/2, nums1.length);
        int b = Math.min(start2 + k/2, nums2.length);
        // divide by half and keep finding kth/2
        if (nums1[a - 1] < nums2[b - 1]) {
            return findKth(nums1,a, nums2,start2, k-a+start1);
        } else {
            return findKth(nums1,start1,nums2,b, k-b+start2);
        }
    }

    public static void main(String[] args) {
        //int[] nums1 = new int[]{1,2};
        //int[] nums2 = new int[]{3,4};

        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2,3,4,5,6};

        System.out.println(findMedianSortedArrays_1(nums1, nums2));
        System.out.println(findMedianSortedArrays_2(nums1, nums2));
    }


}
