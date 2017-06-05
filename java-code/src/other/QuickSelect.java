package other;

import java.util.Random;

/**
 * In quicksort, there is a subprocedure called partition that can, in linear time, group a list (ranging from indices left to right) into two parts,
 * those less than a certain element, and those greater than or equal to the element. Here is pseudocode that performs a partition about
 * the element list[pivotIndex]:

    function partition(list, left, right, pivotIndex)
        pivotValue := list[pivotIndex]
        swap list[pivotIndex] and list[right]  // Move pivot to end
        storeIndex := left
        for i from left to right-1
            if list[i] < pivotValue
            swap list[storeIndex] and list[i]
            increment storeIndex
        swap list[right] and list[storeIndex]  // Move pivot to its final place
        return storeIndex

 In quicksort, we recursively sort both branches, leading to best-case O(n log n) time. However, when doing selection, we already know
 which partition our desired element lies in, since the pivot is in its final sorted position, with all those preceding it in an unsorted order
 and all those following it in an unsorted order. Therefore, a single recursive call locates the desired element in the correct partition,
 and we build upon this for quickselect:

    // Returns the k-th smallest element of list within left..right inclusive
    // (i.e. left <= k <= right).
    // The search space within the array is changing for each round - but the list
    // is still the same size. Thus, k does not need to be updated with each round.
    function select(list, left, right, k)
        if left = right         // If the list contains only one element,
            return list[left]   // return that element
        pivotIndex  := ...      // select a pivotIndex between left and right,
                                // e.g., left + floor(rand() % (right - left + 1))
        pivotIndex  := partition(list, left, right, pivotIndex)
        // The pivot is in its final sorted position
        if k = pivotIndex
            return list[k]
        else if k < pivotIndex
            return select(list, left, pivotIndex - 1, k)
        else
            return select(list, pivotIndex + 1, right, k)

 Note the resemblance to quicksort: just as the minimum-based selection algorithm is a partial selection sort,
 this is a partial quicksort, generating and partitioning only O(log n) of its O(n) partitions. This simple procedure
 has expected linear performance, and, like quicksort, has quite good performance in practice. It is also an in-place algorithm,
 requiring only constant memory overhead if tail-call optimization is available, or if eliminating the tail recursion with a loop:

    function select(list, left, right, k)
        loop
            if left = right
                return list[left]
            pivotIndex := ...     // select pivotIndex between left and right
            pivotIndex := partition(list, left, right, pivotIndex)
            if k = pivotIndex
                return list[k]
            else if k < pivotIndex
                right := pivotIndex - 1
            else
                left := pivotIndex + 1
 */
public class QuickSelect {

    // We pick a pivot (could be random), use partition to see which index it is landed.
    // if its k, then we found it. otherwise, by comparing pivot's index with k, we know which part
    // of the partition should we be looking for (k+1)th smallest.
    public int select(int[]nums, int l, int r, int k) {
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
    public int partition(int[]nums, int l, int r, int pivotIdx) {
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

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
