package interview.li.other;

import java.io.FileNotFoundException;
import java.util.*;

public class AddCombination {
	public static List<List<Integer>> get(int x) {
		List<List<Integer>> rs = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		helper(rs, temp, -1, x);
		return rs;
	}
	
	public static void helper(List<List<Integer>> rs, List<Integer> temp, int pos, int x) {
		if (pos != -1) {
			temp.add(x);
			rs.add(new ArrayList<>(temp));
			temp.remove(temp.size() - 1);
		}

		
		for (int i = Math.max(pos, 1); i <= x / 2; i++) {
			temp.add(i);
			System.out.println("temp: " + temp);
			helper(rs, temp, i, x - i);
			temp.remove(temp.size() - 1);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(get(4));
	}
}
