package interview.li.other;

import java.util.*;

public class RandomHashmap<T> {
	public ArrayList<T> buffer;
	public HashMap<T, Integer> map;
	
	public RandomHashmap() {
		this.buffer = new ArrayList<>();
		this.map = new HashMap<>();
	}
	
	public boolean add(T data) {
		if (map.containsKey(data)) {
			return false;
		}
		buffer.add(data);
		map.put(data, buffer.size() - 1);
		return true;
	}
	
	public boolean remove(T data) {
		if (!map.containsKey(data)) {
			return false;
		}
		int index = map.get(data);
		T temp = buffer.get(buffer.size() - 1);
		Collections.swap(buffer, index, buffer.size() - 1);
		map.put(temp, index);
		buffer.remove(buffer.size() - 1);
		map.remove(data);
		return true;
	}
	
	public T removeRandom() {
		Random rd = new Random();
		int index = rd.nextInt(buffer.size());
		T element = buffer.get(index);
		remove(element);
		return element;
	}
	
	public String toString() {
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		RandomHashmap<Integer> rh = new RandomHashmap<>();
		rh.add(1);
		System.out.println(rh);
		rh.add(2);
		System.out.println(rh);
		rh.add(3);
		System.out.println(rh);
		rh.remove(1);
		System.out.println(rh);
		rh.add(11);
		System.out.println(rh);
		rh.removeRandom();
		System.out.println(rh);
		rh.removeRandom();
		System.out.println(rh);
		rh.removeRandom();
		System.out.println(rh);
	}
}
