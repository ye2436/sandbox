package interview.am;

/**
 * 215. Kth Largest Element in an Array

 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray {

    // Solution 1 - Arrays.sort() and get index (n-k)
    // Time: O(nlogn). Space: O(1)

    // Solution 2 - min heap of size k
    // Time: O(nlogk), Space: O(k)


    // Solution 3 - quick select - avg O(n)
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        return select(nums, 0, nums.length-1, nums.length-k); // (n-k+1)th smallest = kth largest
    }

    // We pick a pivot (could be random), use partition to see which index it is landed.
    // if its k, then we found it. otherwise, by comparing pivot's index with k, we know which part
    // of the partition should we be looking for (k+1)th smallest.
    public static int select(int[]nums, int l, int r, int k) { // here k is the index, representing (k+1)th smallest
        if (l == r) return nums[l];
        int pivotIndex = l + (int)(Math.random() * (r-l+1));
        pivotIndex = partition(nums, l, r, pivotIndex);
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return select(nums, l, pivotIndex-1, k);
        } else {
            return select(nums, pivotIndex+1, r, k);
        }
    }

    // 1. store pivot value and swap pivot value with the right most element
    // 2. keep a storeIndex, initiate with left index.
    // 3. from left to right-1, compare value with pivot value. if smaller, swap it with storeIndex, and increment storeIndex
    // 4. swap the stored pivot value at right most index with storeIndex value.
    // 5. return storeIndex
    // Now we have an array from l to r, the pivot is at its right position. Every element left of it are smaller, every right larger
    public static int partition(int[]nums, int l, int r, int pivotIdx) {
        int pivotVal = nums[pivotIdx];
        swap(nums, pivotIdx, r);
        int storeIdx = l;
        for (int i=l; i<r; i++) {
            if (nums[i] < pivotVal) {
                swap(nums, storeIdx, i);
                storeIdx++;
            }
        }
        swap(nums, storeIdx, r);
        return storeIdx;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[] {2, 1}, 1));
    }
}
