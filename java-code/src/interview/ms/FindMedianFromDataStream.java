package interview.ms;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 295. Find Median from Data Stream
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Design a data structure that supports the following two operations:
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 */
public class FindMedianFromDataStream {


    // Keep two heaps (or priority queues):
    // Max-heap: has the smaller half of the numbers.
    // Min-heap large has the larger half of the numbers.
    // This gives me direct access to the one or two middle values (they're the tops of the heaps), so getting the median takes O(1) time.
    // And adding a number takes O(log n) time.
    // Supporting both min- and max-heap is more or less cumbersome, depending on the language, so I simply negate the numbers
    // in the heap in which I want the reverse of the default order. To prevent this from causing a bug with -2^31
    // (which negated is itself, when using 32-bit ints), I use integer types larger than 32 bits.
    // Using larger integer types also prevents an overflow error when taking the mean of the two middle numbers.
    // I think almost all solutions posted previously have that bug.

    public class MedianFinder {

        // max heap is larger or equals to min Heap
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        /** initialize your data structure here. */
        public MedianFinder() {
            maxHeap = new PriorityQueue<>(100, Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
            return maxHeap.peek();
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}
