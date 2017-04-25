package lc;

import java.util.*;

/**
 * #3. Longest Substring
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstring {
    public int solution_1(String text) { // brutal force
        int longestLength =0;
        for (int i = 0; i < text.length()-1; i++) {
            for (int j = i+1; j < text.length(); j++) {
                if (allUnique(text.substring(i,j))) {
                    longestLength = Math.max(j-i, longestLength);
                }
            }
        }

        return longestLength;
    }

    private boolean allUnique(String text) {
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i<text.length(); i++) {
            if (chars.contains(text.charAt(i))) {
                return true;
            } else {
                chars.add(text.charAt(i));
            }
        }
        return false;
    }

    public int solution_2(String text) { // sliding window
        int longestLength = 0;
        int i = 0;
        int j = 0;
        Set<Character> chars = new HashSet<>();
        while (i < text.length() && j < text.length()) {
            if (!chars.contains(text.charAt(j))) {
                chars.add(text.charAt(j));
                longestLength = Math.max(j-i, longestLength);
                j++;
            } else {
                chars.remove(text.charAt(i));
                i++;
            }
        }
        return longestLength;
    }

    public int solution_3(String text) { // sliding window optimized
        int longestLength = 0;
        int i = 0;
        int j = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (j < text.length()) {
            if (map.containsKey(text.charAt(j))) { // find duplicate character's index and point i there directly instead of moving i 1 by 1.
                i = Math.max(map.get(text.charAt(j)), i); // char either exists before or after current i
            }
            longestLength = Math.max(longestLength, j-i+1);
            map.put(text.charAt(j), j+1); // only has the highest index of a given char; use j+1 because
            j++;
        }
        return longestLength;
    }

    public int my_solution(String s) {
        int max = 0;
        String[] array = s.split("(?!^)");
        List<String> list = Arrays.asList(array);
        StringBuilder sb = new StringBuilder();
        for (String c : list) {
            int index = sb.indexOf(c);
            if (index >= 0) {
                max = Math.max(max, sb.length());
                sb.delete(0, index + 1);
            }
            sb.append(c);
        }
        max = Math.max(max, sb.length());
        return max;
    }

    public int my_solution_optimized(String s) {
        int max = 0;
        StringBuilder sb = new StringBuilder();
        for (int i =0; i<s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (sb.indexOf(c) >= 0) { // if cached stringbuilder contains the character; sb.indexOf would return -1 if not exist
                max = Math.max(max, sb.length());
                sb.delete(0, sb.indexOf(c) + 1);
            }
            sb.append(c);
        }
        max = Math.max(max, sb.length());
        return max;
    }
}

