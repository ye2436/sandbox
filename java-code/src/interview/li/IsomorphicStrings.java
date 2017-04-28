package interview.li;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 205. Isomorphic Strings
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * Note: You may assume both s and t have the same length.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>(); // s -> t
        Map<Character, Character> map2 = new HashMap<>(); // t -> s
        for (int i=0; i<s.length(); i++) {
            if (map1.containsKey(s.charAt(i)) && t.charAt(i) != map1.get(s.charAt(i))) {
                return false;
            }
            if (map2.containsKey(t.charAt(i)) && s.charAt(i) != map2.get(t.charAt(i))) {
                return false;
            }
            map1.put(s.charAt(i), t.charAt(i));
            map2.put(t.charAt(i), s.charAt(i));
        }
        return true;
    }

    // The main idea is to store the last seen positions of current (i-th) characters in both strings.
    // If previously stored positions are different then we know that the fact they're occuring
    // in the current i-th position simultaneously is a mistake. We could use a map for storing
    // but as we deal with chars which are basically ints and can be used as indices we can do
    // the whole thing with an array.
    public boolean isIsomorphic_2(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
                return false;
            }
            map1[s.charAt(i)] = i + 1;
            map2[t.charAt(i)] = i + 1;
        }
        return true;
    }
}
