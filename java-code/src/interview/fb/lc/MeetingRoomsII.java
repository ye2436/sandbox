package interview.fb.lc;

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

    public int minMeetingRooms2_p(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);

        int maxCount = 0;
        int endIdx = 0;
        for (int i=0; i<starts.length; i++) {
            // check if a prev meeting has ended, then this meeting can take the prev's room. maxCount remains the same
            // also we are not using while here, why? we will check in the next round with next meeting to see if it can take a prev's room
            if (ends[endIdx] <= starts[i]) {
                endIdx++;
            } else {
                maxCount++;
            }
        }
        return maxCount;
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

    public int minMeetingRooms_p(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        // sort intervals by start time
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        // minHeap sort by end time
        PriorityQueue<Interval> minHeap = new PriorityQueue<>(1, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });

        // minHeap is like the meeting rooms, and the array of intervals are the meetings
        for (Interval curr : intervals) {
            if (minHeap.isEmpty()) {
                minHeap.offer(curr);
            } else {
                Interval prev = minHeap.poll();
                if (prev.end <= curr.start) { // not overlapping, can be merged
                    prev.end = curr.end;
                    minHeap.offer(prev);
                } else {
                    minHeap.offer(prev);
                    minHeap.offer(curr);
                }
            }
        }
        return minHeap.size();
    }

    private class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
