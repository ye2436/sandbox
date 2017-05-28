package lc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II
 */
public class MeetingRoomII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        }); // sort by meeting start time

        PriorityQueue<Interval> minHeap = new PriorityQueue<>(intervals.length, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        // we can take minHeap and think it as meeting rooms and sorted intervals are meetings.
        // go through the minHeap and merge the meetings

        minHeap.offer(intervals[0]); // arrange first meeting in the meeting room;
        for (int i=1; i<intervals.length; i++) { //
            Interval curr = minHeap.poll();
            Interval next = intervals[i];
            if (next.start >= curr.end) { // not overlapping meeting, won't require separate rooms. so merge them
                curr.end = next.end;
            } else {
                minHeap.offer(next);
            }

            minHeap.offer(curr);
        }

        return minHeap.size();
    }

    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
