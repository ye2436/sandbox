package interview.am.other;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 一个list存有N个point<x,y>，输入center点和K，返回离中心点距离最小的K个点
 * find k closest points to a given point in 2d plane
 */
public class NearestKPoints {

    // Binary Heap
    // Algorithm 		Average 	Worst Case
    // Space 		O(n) 	        O(n)
    // Search 		O(n) 	        O(n)
    // Insert 		O(1) 	        O(log n)
    // Delete 		O(log n) 	    O(log n)
    // Peek 		O(1) 	        O(1)

    // so when using min heap, we are inserting into a heap size of n for n times
    // whereas using max heap, we are inserting into a heap size of k for n times
    // 可以用最小堆也可以用最大堆。
    // 1. 最小堆。把所有点放进最小堆（根据点到中心的距离比大小），然后取k个点出来。时间复杂度O(nlogn)。
    // 2. 最大堆。遍历每个点，对于当前点：
    //  1) 如果堆内元素少于k，放入当前点.
    //  2) 如果堆内元素等于k，如果当前点距离 < heap.peek()，堆删除堆顶元素，再放入当前点
    //                     如果当前点距离 >= heap.peek()，忽略当前点。
    // 最大堆时间复杂度 是 O(nlogk)。

    // We can use a maxHeap to maintain k points. The comparator is the distance to center point.
    // When adding more points, if it is closer to the center than the heap top, we pop the heap top and add the new point into the maxHeap.
    public List<Point> findKClosestMy(List<Point> list, final Point center, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(k, new Comparator<Point>() {
            @Override
            // return positive if distance(p2, center) is greater than distance(p1, center)
            public int compare(Point p1, Point p2) {
                return getDistance(p2, center) - getDistance(p1, center);
            }
        });

        for (Point point : list) {
            if (maxHeap.size() < k) {
                maxHeap.offer(point);
            } else if (getDistance(point, center) < getDistance(maxHeap.peek(), center)) {
                maxHeap.poll();
                maxHeap.offer(point);
            }
        }
        return null;
    }

    private int getDistance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public class Point {
        public int x;
        public int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
