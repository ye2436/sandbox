import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    public static List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.isEmpty()) return Arrays.asList(newInterval);

        List<Interval> res = new ArrayList<>();
        int i=0;
        // add all intervals to res before newInterval is inserted
        while (i<intervals.size() && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i++));
        }
        // merge all overlapping ones
        while (i<intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }
        res.add(newInterval); // even when all intervals end are greater than newInterval, newInterval will be inserted here.

        // add rest
        while (i<intervals.size()) {
            res.add(intervals.get(i++));
        }
        return res;
    }


    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.isEmpty()) return Arrays.asList(newInterval);

        List<Interval> res = new ArrayList<>();
        // search start index
        int insertIndex = binarySearch(intervals, newInterval.start, 0, intervals.size()-1);
        intervals.add(insertIndex, newInterval);
        // merge
        res.add(intervals.get(0));
        int len = intervals.size();
        for (int i=1; i<len; i++) {
            if (res.get(res.size()-1).end >= intervals.get(i).start) {
                res.get(res.size()-1).end = Math.max(res.get(res.size()-1).end, intervals.get(i).end);
            } else {
                res.add(intervals.get(i));
            }
        }

        return res;
    }

    private static int binarySearch (List<Interval> intervals, int start, int l, int r) {
        if (l >= r) {
            if (start < intervals.get(l).start) return l;
            return l+1;
        }
        int m = (l+r)/2;
        if (intervals.get(m).start == start) return m;
        if (start < intervals.get(m).start) {
            return binarySearch(intervals, start, l, m-1);
        } else {
            return binarySearch(intervals, start, m+1, r);
        }
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1,5));
        list.add(new Interval(6,8));
        //list.add(new Interval(2,6));
        //list.add(new Interval(8,10));
        //list.add(new Interval(15,18));
        System.out.println(binarySearch(list, 0, 0, list.size()-1));
        System.out.println(printList(insert(list, new Interval(6,8))));
    }


    //////////////////////////
    public static class Interval {
        int start;
        int end;
        Interval() {
            start = 0;
            end = 0;
        }
        Interval(int s, int e) {
            start = s;
            end = e;
        }

        /*public int getStart() {
            return start;
        }*/
    }

    // helper
    public static String printList(List<Interval> list) {
        StringBuilder sb = new StringBuilder();
        for (Interval interval : list) {
            sb.append("[");
            sb.append(interval.start);
            sb.append(",");
            sb.append(interval.end);
            sb.append("],");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
