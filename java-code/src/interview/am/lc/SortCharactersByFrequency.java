package interview.am.lc;

import java.util.*;

/**
 * 451. Sort Characters By Frequency

 Given a string, sort it in decreasing order based on the frequency of characters.

 Example 1:

 Input:
 "tree"

 Output:
 "eert"

 Explanation:
 'e' appears twice while 'r' and 't' both appear once.
 So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 Example 2:

 Input:
 "cccaaa"

 Output:
 "cccaaa"

 Explanation:
 Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 Note that "cacaca" is incorrect, as the same characters must be together.
 Example 3:

 Input:
 "Aabb"

 Output:
 "bbAa"

 Explanation:
 "bbaA" is also a valid answer, but "Aabb" is incorrect.
 Note that 'A' and 'a' are treated as two different characters.
 */
public class SortCharactersByFrequency {

    // Solution 1 - O(n) Bucket Sort
    // First step is the same as solution 2 (heap / priority queue) ---- Get a map of characters with frequency counts
    // In sorting the frequency, we use a bucket (array) with the size of s.length+1
    // bucket[i] represents where characters appeared i times. At most, a character could appear n times (n = s.length())
    // Because it's possible that multiple characters have the same frequency, we use a list of characters as bucket type
    // After we have put all characters in the bucket, we could go through the bucket from right to left to get the most
    // frequent characters first.
    public String frequencySort_2(String s) {
        if (s == null || s.length() <= 1) return s;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char c = chars[i];
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        List<Character>[] buckets = new List[s.length()+1];
        for (Character c : map.keySet()) {
            int count = map.get(c);
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i=s.length(); i>0; i--) {
            if (buckets[i] != null) {
                for (Character c : buckets[i]) {
                    for (int j=i; j>0; j--) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }


    // Solution 2 - O(nlogn) Heap Sort
    public String frequencySort(String s) {
        if (s == null || s.length() <= 1) return s;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i=0; i<s.length(); i++) {
            char c = chars[i];
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(map.size(), new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        for (Map.Entry entry : map.entrySet()) {
            maxHeap.offer(entry);
        }

        StringBuilder sb = new StringBuilder();
        while(!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            char[] newString = new char[entry.getValue()];
            Arrays.fill(newString, entry.getKey());
            sb.append(new String(newString));
        }

        return sb.toString();
    }
}
