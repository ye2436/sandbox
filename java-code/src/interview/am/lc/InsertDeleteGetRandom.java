package interview.am.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 380. Insert Delete GetRandom O(1)

 Design a data structure that supports all following operations in average O(1) time.

 insert(val): Inserts an item val to the set if not already present.
 remove(val): Removes an item val from the set if present.
 getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 Example:

 // Init an empty set.
 RandomizedSet randomSet = new RandomizedSet();

 // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 randomSet.insert(1);

 // Returns false as 2 does not exist in the set.
 randomSet.remove(2);

 // Inserts 2 to the set, returns true. Set now contains [1,2].
 randomSet.insert(2);

 // getRandom should return either 1 or 2 randomly.
 randomSet.getRandom();

 // Removes 1 from the set, returns true. Set now contains [2].
 randomSet.remove(1);

 // 2 was already in the set, so return false.
 randomSet.insert(2);

 // Since 2 is the only number in the set, getRandom always return 2.
 */
public class InsertDeleteGetRandom {

    // For O(1) operation time, we can easily think of hash set. It provides O(1) time for insert and delete.
    // But it doesn't provide a way to get random.
    // To solve that, we can use a hash map, along with a list. With a list, we could get random element by index
    // To store - Save every input number in the list, and store the (input value, list index) pair in the hash map.
    // To delete - We first find it in the hash map (time O(1)), then locate it in the list by index.
    // * But how do we delete an element from a list in O(1) time? Swap the element with the last one, and then delete the last

    // Follow up: what if duplicates are allowed? --- Use map of (value, list<index>) pair, along with list

    public static class RandomizedSet {

        Map<Integer, Integer> map;
        List<Integer> list;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            int index = map.get(val);
            map.remove(val);
            if (index != list.size()-1) { // swap element with the last one if it is not already the last one
                int lastVal = list.get(list.size()-1);
                list.set(index, lastVal);
                map.put(lastVal, index);
            }
            list.remove(list.size()-1); // remove from the tail to ensure O(1) operation time
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get((int)(Math.random() * list.size()));
        }
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(2));
        System.out.println(obj.getRandom());
    }
}
