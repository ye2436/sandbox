package interview.li.other;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class mergeKSortedIterator {
	static class iterWrapper {
		int currentVal;
	    Iterator<Integer> iter;
	    
	    public iterWrapper(Iterator<Integer> iter) {
	    	this.iter = iter;
	    	this.currentVal = this.iter.next();
	    }
	}
	
	public static Iterable<Integer> mergeKSortedIterators(List<Iterator<Integer>> iters) {
		ArrayList<Integer> rs = new ArrayList<>();
		Comparator<iterWrapper> cmp = new Comparator<iterWrapper>() {
			public int compare(iterWrapper l1, iterWrapper l2) {
				return l1.currentVal - l2.currentVal;
			}
		};
		PriorityQueue<iterWrapper> heap = new PriorityQueue<iterWrapper>(iters.size(), cmp);
		for (Iterator<Integer> iter : iters) {
			iterWrapper iw = new iterWrapper(iter);
			heap.offer(iw);
		}
		while (heap.size() > 0) {
			iterWrapper temp = heap.poll();
			rs.add(temp.currentVal);
			if (temp.iter.hasNext()) {
				temp.currentVal = temp.iter.next();
				heap.offer(temp);
			}
		}
		return rs;
	}
	
	public static void main(String[] args) {
		List<Iterator<Integer>> iters = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			ArrayList<Integer> l = new ArrayList<>();
			for (int j = i; j < 3 * 5; j += 3) {
				l.add(j);
			}
			System.out.println(l);
			iters.add(l.iterator());
		}
		
		Iterable<Integer> rs = mergeKSortedIterators(iters);
		System.out.print(rs);
	}
}
