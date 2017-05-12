package interview.ms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of N integers, A1, A2 ,…, AN and an integer K. Return the of count of distinct numbers in all windows of size K.
 * Formally, return an array of size N-K+1 where i’th element in this array contains number of distinct elements in
 * sequence Ai, Ai+1 ,…, Ai+k-1.
 * Note:
 * - If K > N, return empty array.
 * For example,
 * A=[1, 2, 1, 3, 4, 3] and K = 3
 * All windows of size K are
 * [1, 2, 1]
 * [2, 1, 3]
 * [1, 3, 4]
 * [3, 4, 3]
 * So, we return an array [2, 3, 3, 2].
 */
public class DistinctNumbersInWindow {

    // Solution 1: Naive, brute force. For each window k, check each number in window
    // see if there is the same value in the window. Ex. for x in (0, k), check if there is a y in (0, x-1) where A(x)=A(y)
    // Do so for all windows.
    // Time: O (nk^2)
    // Or use sorting and then find distinct. Time: O(nkLogk)
    // Or use hashing in finding distinct numbers in window. Time: O(nk)


    // Solution 2: Use the count of previous window, while sliding the window.
    // The idea is to create a hash map that stores elements of current widow. When we slide the window,
    // we remove an element from hash and add an element. We also keep track of distinct elements. Below is algorithm.
    // 1. a hashmap. key: distinct number, value: count in the window
    // 2. loop through first window. fill the map, the map size is the number of distinct numbers in first window
    // 3. continue with the rest of the windows. move 1 index at a time. each time update the count. and remove from map if count=0
    public static int[] findDistinctNumbers(int[] array, int k) {
        int[] distinct = new int[array.length-k+1];
        Map<Integer, Integer> countMap = new HashMap<>();
        // initialize with the first window
        for (int i=0; i<k; i++) {
            if(countMap.containsKey(array[i])) {
                countMap.put(array[i], countMap.get(array[i])+1);
            } else  {
                countMap.put(array[i], 1);
            }
        }
        distinct[0] = countMap.size();

        // i now represents the right side of the window
        for (int i=k; i<array.length; i++) {
            // index i-k is from the last window, remove it from the map
            countMap.put(array[i-k], countMap.get(array[i-k])-1);
            if (countMap.get(array[i-k]) == 0) {
                countMap.remove(array[i-k]);
            }
            // add new element
            if(countMap.containsKey(array[i])) {
                countMap.put(array[i], countMap.get(array[i])+1);
            } else  {
                countMap.put(array[i], 1);
            }
            distinct[i-k+1] = countMap.size();
        }

        return distinct;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findDistinctNumbers(new int[]{1, 2, 1, 3, 4, 3},3)));
    }
}
