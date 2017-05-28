package interview.li;

import java.util.*;

/**
 * 1.
 * 要实现一个接口，实现addInterval和getTotalCoverage两个函数。 基本算是leetcode的两个interval题目的变种，
 * 第一遍bug free写了一个add O(n)和get O(1)的方法， 然后交流了一下之后做了些小优化。
 * followup是问有没有让add方法更efficient的写法, 然后我说了下add O(1), get O(nlogn)的方法， 然后又讨论了一些优化的问题。
 *
 * 2.
 * 要求不是以前的那种根据call frequency写而是假定两个method的频率是随机的。要求达到时间复杂度最佳。。
 * 最先用了直接add 到list 然后call get 的时候sort 的方法。然后讨论了很久最后说用treeSet 在add 里面，这样总时间复杂度更小。
 */
public class InsertInterval {
    public interface IntervalLinkedIn {

        /**
         * Adds an interval [from, to] into internal structure.
         */
        void addInterval(int from, int to);


        /**
         * Returns a total length covered by intervals.
         * If several intervals intersect, intersection should be counted only once.
         * Example:
         *
         * addInterval(3, 6)
         * addInterval(8, 9)
         * addInterval(1, 5).
         *
         * getTotalCoveredLength() -> 6
         * i.e. [1,5] and [3,6] intersect and give a total covered interval [1,6]
         * [1,6] and [8,9] don't intersect so total covered length is a sum for both intervals, that is 6.
         *
         *                   _________
         *                                               ___
         *     ____________
         *
         * 0  1    2    3    4    5   6   7    8    9    10
         *
         */
        int getTotalCoveredLength();
    }

    // Solution 1:
    // add: O(1). getLength: O(nlogn + n)
    public class MyInterval implements IntervalLinkedIn {
        List<int[]> list = new ArrayList<>();

        @Override
        public void addInterval(int from, int to) {
            list.add(new int[] {from, to});
        }

        @Override
        public int getTotalCoveredLength() {
            int totalLength = 0;
            if (list.size() == 0) return totalLength;
            list.sort(new Comparator<int[]>() { // sort by from (start point)
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            int[] prev = list.get(0);
            totalLength = prev[1] - prev[0];
            for (int i=1; i<list.size(); i++) {
                int[] curr = list.get(i);
                if(curr[0] < prev[1]) { // curr start smaller than prev end, overlapping
                    totalLength += curr[1] - prev[1];
                } else {
                    totalLength += curr[1] - curr[0];
                }
                prev = curr;
            }
            return totalLength;
        }
    }

    // Solution 2: use TreeSet for sorted intervals
    // add: O(logn). getLength: O(n)
    public class MyInterval2 implements IntervalLinkedIn {
        TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // sort by start point in ascending order
            }
        });

        @Override
        public void addInterval(int from, int to) {
            set.add(new int[]{from, to});
        }

        @Override
        public int getTotalCoveredLength() {
            int totalLength = 0;
            int[] prev = null;
            Iterator<int[]> iterator = set.iterator();
            while (iterator.hasNext()) {
                int[] curr = iterator.next();
                if (prev != null && curr[0] < prev[1]) { // curr start smaller than prev end, overlapping
                    totalLength += curr[1] - prev[1];
                } else {
                    totalLength += curr[1] - curr[0]; // not overlapping with previous or previous is null
                }
                prev = curr;
            }
            return totalLength;
        }
    }
}
