package interview.am.oa;

import java.util.*;

/**
 * 438. Find All Anagrams in a String
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * Example 1:
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * Input:
 * s: "abab" p: "ab"
 * Output:
 * [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInAString {

    public static List<Integer> practice (String s, String p) { // sliding window
        if (s == null || s.length() < p.length()) return new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }
        }
        int count = p.length();
        int l = 0;
        int r = 0;

        while(r < s.length()) {
            char c = s.charAt(r);
            if (freqMap.containsKey(c)) { // if c is in p
                if (freqMap.get(c) > 0) {
                    count--; // only count when remaining frequency is still > 0
                }
                freqMap.put(c, freqMap.get(c)-1);
            }

            if (count == 0) {
                indices.add(l);
            }

            if (r - l + 1 == p.length()) {
                char o = s.charAt(l);
                if (freqMap.containsKey(o)) {
                    freqMap.put(o, freqMap.get(o)+1);
                    if (freqMap.get(o) > 0) {
                        count++;
                    }
                }
                l++;
            }

            r++;
        }

        return indices;
    }

    // Solution 1: sort the anagram, find all subString and sort, compare the two
    //             takes too long (TLE)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> indices = new ArrayList<>();
        int len = p.length();
        char[] chars = p.toCharArray();
        Arrays.sort(chars);
        String anagram = new String(chars);
        for (int i=0; i<s.length()-len+1; i++) {
            char[] chars2 = s.substring(i, i+len).toCharArray();
            Arrays.sort(chars2);
            String subStr = new String(chars2);
            if (subStr.equals(anagram)) {
                indices.add(i);
            }
        }
        return indices;
    }

    // Solution 2: Sliding window (See similar algorithm in #3 & #76)
    // https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> indices = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<p.length(); i++) {
            Character c = p.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }

        int start = 0;
        int end = 0;
        int count = p.length();
        while (end < s.length()) {
            // if the current char is not in map OR the count is 0,
            // update start, count, & map

            Character c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) >= 0) { // only decrease count when #of char is not negative
                    count--;
                }
            }

            if (count == 0) {
                indices.add(start);
            }

            if (end - start + 1 == p.length()) { // current window size equals p's length
                // move start to the right, but before that, update map and count
                if (map.containsKey(s.charAt(start))) {
                    map.put(s.charAt(start), map.get(s.charAt(start))+1);
                    if (map.get(s.charAt(start)) >= 1) {
                        count++;
                    }
                }
                start++;
            }

            end++;
        }

        return indices;
    }

    public static void main(String[] args) {
        System.out.println(practice("cbaebabacd","abc"));
    }
}
