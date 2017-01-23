import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * [
 *  ["ate", "eat","tea"],
 *  ["nat","tan"],
 *  ["bat"]
 * ]
 * Note: All inputs will be in lower-case.Created by yechen on 1/22/17.
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newStr = new String(chars); // faster than String.valueOf(chars)
            if (map.containsKey(newStr)) {
                map.get(newStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(newStr, list);
            }
        }
        /*for (String key : map.keySet()) {
            res.add(map.get(key));
        }*/

        return new ArrayList<>(map.values()); // faster than for loop
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        for (String str : strs) {
            boolean found = false;
            for (List<String> list : res) {
                if (isAnagrams(str, list.get(0))) {
                    list.add(str);
                    found = true;
                    break;
                }
            }

            if (!found) {
                List<String> list = new ArrayList<>();
                list.add(str);
                res.add(list);
            }
        }

        return res;
    }

    private static boolean isAnagrams(String s, String t) {
        if (s.length()==0 && t.length()==0) return true;
        if (s.length() != t.length()) {
            return false;
        }

        int[] alphabets = new int[26];
        for (int i=0; i<s.length(); i++) {
            alphabets[s.charAt(i) - 'a']++;
        }

        for (int i=0; i<t.length(); i++) {
            alphabets[t.charAt(i) - 'a']--;
        }

        for (int i: alphabets) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean isAnagrams_slow(String s, String t) {
        if (s.length()==0 && t.length()==0) return true;
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }

        for (int i=0; i<t.length(); i++) {
            char c = t.charAt(i);
            if (map.containsKey(c) && map.get(c)>0) {
                map.put(c, map.get(c)-1);
            } else {
                return false;
            }
        }

        for (Integer i: map.values()) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagrams("eat","tea"));


        String[] nums = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(nums));
    }
}
