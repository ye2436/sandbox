package interview.li;

import java.util.*;

/**
 * 170. Two Sum III - Data structure design
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * For example,
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 */

// In fact, there has to be one operation's time complexity is O(n) and the other is O(1), no matter add or find.
// So clearly there's trade off when solve this problem, prefer quick find or quick add.
public class TwoSumIII {

    // If  more find and less add or we only care time complexity in finding
    public class TwoSum {
        Set<Integer> sums;
        Set<Integer> numbers;

        /** Initialize your data structure here. */
        public TwoSum() {
            sums = new HashSet<Integer>();
            numbers = new HashSet<Integer>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            // no need to loop thru numbers and calculate sums
            // sums with number is already there
            // only need to add two sum of the same number
            if(numbers.contains(number)){
                sums.add(number * 2);
            } else {
                for (int num : numbers) {
                    sums.add(num + number);
                }
                numbers.add(number);
            }

        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            return sums.contains(value);
        }
    }

    public class TwoSum2 {
        Map<Integer,Integer> map; // key: number, value: how many times this number can be used in sum calc

        /** Initialize your data structure here. */
        public TwoSum2() {
            map = new HashMap<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            if(map.containsKey(number)){
                map.put(number, 2);
            } else {
                map.put(number, 1);
            }
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for (int num : map.keySet()) {
                int num2 = value - num;
                if (map.containsKey(num2) && (num != num2 || map.get(num2) > 1)) {
                    return true;
                }
            }
            return false;
        }
    }

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
}
