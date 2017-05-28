package interview.li.other;

import java.util.*;

public class IntervalCoverage {
	
	static class Interval {
		int start;
		int end;
		
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public ArrayList<Interval> buffer;
	public int totalLength;
	
	public IntervalCoverage() {
		this.buffer = new ArrayList<>();
		this.totalLength = 0;
	}
	
	public void addInterval(int start, int end) {
		Interval interval = new Interval(start, end);
		mergeInterval(interval);
	}
	
	public void mergeInterval(Interval newInterval) {
		int cover = 0;
		ArrayList<Interval> rs = new ArrayList<>();
        boolean inserted = false;
        for (int i = 0; i < buffer.size(); i++) {
            Interval temp = buffer.get(i);
            if (temp.end < newInterval.start) {
                rs.add(temp);
                cover += temp.end - temp.start;
            } else if (temp.start > newInterval.end) {
                if (!inserted) {
                    rs.add(newInterval);
                    cover += newInterval.end - newInterval.start;
                    inserted = true;
                }
                rs.add(temp);
                cover += temp.end - temp.start;
            } else {
                newInterval = merge(newInterval, temp);                
            }
        }
        if (!inserted) {
            rs.add(newInterval);
        }
		this.totalLength = cover;
	}
	
	public int getTotalCover() {
		return this.totalLength;
	}
	
    private Interval merge(Interval i1, Interval i2) {
        int start = Math.min(i1.start, i2.start);
        int end = Math.max(i1.end, i2.end);
        return new Interval(start, end);
    }
    
	
	public int getTotalCoverage() {
		return this.totalLength;
	}
	
	public static int maxOverlappingInterval(List<Interval> intervals) {
		Comparator<Interval> cmp = new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				return i1.start - i2.start;
			}
		};
		Comparator<Interval> cmp2 = new Comparator<Interval>() {
			public int compare(Interval i1, Interval i2) {
				return i1.end - i2.end;
			}
		};
		Collections.sort(intervals, cmp);
		int max = 0;
		PriorityQueue<Interval> heap = new PriorityQueue<>(10, cmp2);
		for (Interval i : intervals) {
			Interval temp = heap.peek();
			while (temp != null && temp.end < i.start) {
				heap.poll();
				temp = heap.peek();
			}
			heap.offer(i);
			max = Math.max(max, heap.size());
		}
		return max;
	}
	
	public static void main(String[] args) {
		List<Interval> k = new ArrayList<>();
		/*
		k.add(new Interval(0, 2));
		k.add(new Interval(3, 7));
		k.add(new Interval(4, 6));
		k.add(new Interval(7, 8));
		k.add(new Interval(1, 5));
		k.add(new Interval(4, 5));
		*/
		k.add(new Interval(0, 2));
		k.add(new Interval(3, 7));
		k.add(new Interval(8, 9));
		k.add(new Interval(11, 12));
		System.out.println(maxOverlappingInterval(k));
	}
}
