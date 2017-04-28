package interview.li;

import java.util.*;

/**
 * 一个list存有N个point<x,y>，输入center点和K，返回离中心点距离最小的K个点
 * find k closest points to a given point in 2d plane
 */
public class NearestKPoints {

    // 可以用最小堆也可以用最大堆。
    // 1. 最小堆。把所有点放进最小堆（根据点到中心的距离比大小），然后取k个点出来。时间复杂度O(nlogn)。
    // 2. 最大堆。遍历每个点，对于当前点：
    //  1) 如果堆内元素少于k，放入当前点.
    //  2) 如果堆内元素等于k，如果当前点距离 < heap.peek()，堆删除堆顶元素，再放入当前点
    //                     如果当前点距离 >= heap.peek()，忽略当前点。
    // 最大堆时间复杂度 是 O(nlogk)。

    // We can use a maxHeap to maintain k points. The comparator is the distance to center point.
    // When adding more points, if it is closer to the center than the heap top, we pop the heap top and add the new point into the maxHeap.


    public List<Point> findKClosest(Point[] p, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>(10, new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                return (b.x * b.x + b.y * b.y) - (a.x * a.x + a.y * a.y);
            }
        });

        for (int i = 0; i < p.length; i++) {
            if (i < k)
                pq.offer(p[i]);
            else {
                Point temp = pq.peek();
                if ((p[i].x * p[i].x + p[i].y * p[i].y) - (temp.x * temp.x + temp.y * temp.y) < 0) {
                    pq.poll();
                    pq.offer(p[i]);
                }
            }
        }

        List<Point> x = new ArrayList<>();
        while (!pq.isEmpty())
            x.add(pq.poll());

        return x;
    }

    Set<Point> points = new HashSet<Point>(); //?

    public Collection<Point> findNearest(Point center, int m) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<Point>(m, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Double.compare(getDistance(p2, center), getDistance(p1, center));
            }
        });
        for (Point p: points) {
            if (center == p) {
                continue;
            }
            if (maxHeap.size() < m) {
                maxHeap.add(p);
            } else if (getDistance(p, center) < getDistance(maxHeap.peek(), center)) {
                maxHeap.remove();
                maxHeap.add(p);
            }
        }
        return maxHeap;
    }

    private double getDistance(Point p1, Point p2) {
        return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
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
