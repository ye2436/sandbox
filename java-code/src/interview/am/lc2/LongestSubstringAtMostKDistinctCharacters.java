package interview.am.lc2;

import java.util.HashMap;
import java.util.Map;

/**
 * 340. Longest Substring with At Most K Distinct Characters

 Given a string, find the length of the longest substring T that contains at most k distinct characters.

 For example, Given s = “eceba” and k = 2,

 T is "ece" which its length is 3.
 */
public class LongestSubstringAtMostKDistinctCharacters {

    // sliding window
    // As we go through the characters, we keep track of the counts of each character.
    // And use another variable (num) to indicate number of unqiue characters inside the window.
    // whenever num becomes larger than k, we will move the left window, decrease the count for this character.
    // num goes down by 1 if the count becomes 0.
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        int max = 0; // max length
        int num = 0; // # of unique characters in window
        Map<Character, Integer> counts = new HashMap<>();

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            // can't use containsKey here, because after a few rounds, a character count could goes back to 0 after window sliding
             if (counts.getOrDefault(s.charAt(r), 0) == 0) {
                 counts.put(s.charAt(r), 1);
                 num++; // increment when new character is added
             } else {
                 counts.put(s.charAt(r), counts.get(s.charAt(r))+1);
             }

             if (num > k) {
                 // while left window count not 0, decrease count and move left window, until we found a count could be 0
                 while (counts.get(s.charAt(l)) > 0) {
                     counts.put(s.charAt(l), counts.get(s.charAt(l))-1);
                     if (counts.get(s.charAt(l)) == 0) {
                         l++;
                         break;
                     }
                     l++;
                 }
                 num--; // decrease num
             }

             max = Math.max(max, r-l+1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("aba", 1));
    }
}
