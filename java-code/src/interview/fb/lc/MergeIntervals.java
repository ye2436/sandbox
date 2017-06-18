package interview.fb.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * #56. Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
public class MergeIntervals {

    // Sort by start, and then compare end with next start
    // add one interval to result list, each time find previous interval by looking at the end of the result list
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) return new ArrayList<>();

        // sort by starting point? and then binary search?
        List<Interval> res = new ArrayList<>();
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
        //Collections.sort(intervals, Comparator.comparing(Interval::getStart));
        res.add(intervals.get(0));
        int len = intervals.size();
        for (int i=1; i<len; i++) {
            //Interval curr = intervals.get(i);
            //Interval prev = res.get(res.size()-1);
            if (res.get(res.size()-1).end >= intervals.get(i).start) {
                res.get(res.size()-1).end = Math.max(res.get(res.size()-1).end, intervals.get(i).end);
                //res.set(res.size()-1, new Interval(prev.start, Math.max(prev.end, curr.end)));
            } else {
                res.add(intervals.get(i));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(8,10));
        list.add(new Interval(2,6));
        list.add(new Interval(1,3));
        list.add(new Interval(15,18));
        System.out.println(printList(merge(list)));
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
