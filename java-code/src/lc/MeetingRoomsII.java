package lc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II
 */
public class MeetingRoomsII {

    // O(nlogn) + O(n) - Consider start and end times separately, use 2 arrays to store them, and sort in ascending order
    // The point is when we see an end time, we know a meeting has ended and a room becomes available, but we don't care which room it is.
    // Loop through start array, use another pointer to point to the next ending time
    public int minMeetingRooms2(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int rooms = 0;
        int j = 0; // end index
        for (int i=0; i<starts.length; i++) {
            if (starts[i] < ends[j]) { // if meeting starts time is less than the next end time, no meeting has ended yet, need a new room
                rooms++;
            } else { // one meeting has ended before this meeting starts, no need to add a new room. move to next end time
                j++;
            }
        }
        return rooms;
    }


    // O(nlogn) - merge non overlapping ones
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
