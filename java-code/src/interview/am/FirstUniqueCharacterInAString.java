package interview.am;

import java.util.HashMap;
import java.util.Map;

/**
 * 387. First Unique Character in a String
 *  Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *  Examples:
 *  s = "leetcode"
 *  return 0.
 *  s = "loveleetcode",
 *  return 2.
 *  Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInAString {
    // Solution 1: get the frequency of all characters
    // and then return the first one with frequency of 1
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        Map<Character, Integer> freq = new HashMap<>(); // depending on the char set, might be able to use int array of 256
        char[] array = s.toCharArray();
        for (char c : array) {
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c)+1);
            } else {
                freq.put(c, 1);
            }
        }
        for (int i=0; i<array.length; i++) {
            if (freq.get(array[i]) == 1) {
                return i;
            }
        }

        return -1;
    }


    public int firstUniqChar_2(String s) {
        char[] a = s.toCharArray();

        for(int i=0; i<s.length(); i++){
            if(s.indexOf(a[i])==s.lastIndexOf(a[i])){return i;}
        }
        return -1;
    }
}
