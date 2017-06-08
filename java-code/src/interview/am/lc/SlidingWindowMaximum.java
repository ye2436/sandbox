package interview.am.lc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 239. Sliding Window Maximum

 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Therefore, return the max sliding window as [3,3,5,5,6,7].

 Note:
 You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?
 */
public class SlidingWindowMaximum {
    // Solution 1 - naive, search all windows. O(n*k)

    // Solution 2 - Use max heap - Time: O(nlogk)
    // but how do we delete the element it is moved out of the window? we lazily delete
    // use a pair of (nums[i], i) as the heap element, when the found element has an index out of the window, delete it

    // Solution 3 - Monotonic queue (double ended) - Time: amortized O(n) - as each element is put and polled once.
    // the idea is that we want to keep a queue that is monotonically decreasing, the head element is always the max in queue.
    // The queue represents the window.
    // * But how do we achieve the monotonically decreasing order?
    // The trick is that when we are adding the new element, we add it to the tail. but before we do that, we compare the new element
    // with the tail element. if the tail element is smaller, poll it from the queue's end. (That's why we need a deque)
    // Continue to do that, until we find no more tail element is smaller than the new element. Then enqueue the new element.
    // * How do we ensure the queue represents the window size?
    // Every time when we loop through the array and get the new element, we are moving the right window.
    // At the same time, check the head element in the queue to see if its index is within the range. Poll it from the head, if it is out of range.
    // * Here, we don't need the (nums[i], i) pair to be put in the queue. We only need the index i.

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        Deque<Integer> queue = new ArrayDeque<>();
        int[] max = new int[nums.length - k + 1];
        for (int i=0; i<nums.length; i++) {
            // 1. remove elements that are out of range - current window's range should be [i-k+1, i]
            while (!queue.isEmpty() && queue.peekFirst() < i-k+1) {
                queue.pollFirst();
            }

            // 2. enqueue the new element after removing all tail elements that are smaller
            while(!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);

            // 3. add current max to return array if we passed the initial window size
            if (i >= k-1) {
                max[i-k+1] = nums[queue.peekFirst()];
            }
        }
        return max;
    }
}
