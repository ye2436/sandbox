package interview.am.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 267. Palindrome Permutation II

 Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

 For example:

 Given s = "aabb", return ["abba", "baab"].

 Given s = "abc", return [].

 */
public class PalindromePermutationII {

    // 1. First find out if the string could be palindrome by checking character counts - even numbers + (optional 1 odd)
    // 2. Use character counts to form palindrome
    // - odd numbers always in the center
    // - all counts are divided by 2 (including odd) and add half count of characters to a list - now it's similar to permutation II
    public static List<String> generatePalindromes(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        String center = "";
        for (char c : map.keySet()) {
            int count = map.get(c);
            if (count % 2 == 1) {
                if (center.length() != 0) {
                    return new ArrayList<>();
                }
                center += c;
            }
            for (int i=0; i<count/2; i++) {
                list.add(c);
            }
        }

        List<String> results = new ArrayList<>();
        permutation(list, new boolean[list.size()], center, new StringBuilder(), results);

        return results;
    }

    // when a character has the same value with its previous, we can use this character only if its previous was used
    private static void permutation(List<Character> list, boolean[] used, String center, StringBuilder sb, List<String> results) {
        if (sb.length() == list.size()) {
            results.add(sb.toString() + center + sb.reverse().toString());
            sb.reverse();
            return;
        }
        for (int i=0; i<list.size(); i++) {
            if (i>0 && list.get(i) == list.get(i-1) && !used[i-1]) {
                continue;
            }
            if (!used[i]) {
                sb.append(list.get(i));
                used[i] = true;
                permutation(list, used, center, sb, results);
                used[i] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(generatePalindromes("aabb"));
    }
}
