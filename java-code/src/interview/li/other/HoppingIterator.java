package interview.li.other;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HoppingIterator<T> implements Iterator<T>{
	public T next = null;
	public int hop;
	public Iterator<T> buffer;
		
	public HoppingIterator(Iterator<T> iterator, int numHops) {
		this.hop = numHops;
		this.buffer = iterator;
		if (buffer.hasNext()) {
			next = buffer.next();
		}
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public T next() {
		T rs = next;
		for (int i = 0 ; i <= hop; i++) {
			if (buffer.hasNext()) {
				next = buffer.next();
			} else {
				next = null;
				break;
			}
		}
		return rs;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Integer[] a = {1, 2, 3, 4, 5};
		List<Integer> k = Arrays.asList(a);
		HoppingIterator<Integer> hp = new HoppingIterator<>(k.iterator(), 2);
		while (hp.hasNext()) {
			System.out.println(hp.next());
		}
	}
	
}
