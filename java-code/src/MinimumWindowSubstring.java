import java.util.HashMap;
import java.util.Map;

/**
 * #76 Minimum Window Substring
 *  Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *  For example,
 *  S = "ADOBECODEBANC"
 *  T = "ABC"
 *  Minimum window is "BANC".
 *  Note:
 *  If there is no such window in S that covers all characters in T, return the empty string "".
 *  If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";
        Map<Character, Integer> chars = new HashMap<>();
        for (int i=0; i<t.length(); i++) { // frequency of characters
            char c = t.charAt(i);
            if (chars.containsKey(c)) {
                chars.put(c, chars.get(c)+1);
            } else {
                chars.put(c, 1);
            }
        }
        //String minWindow = "";
        int minStart = 0;
        int minLen = 0;
        int count = 0;
        int l = 0;
        for (int r=0; r<s.length(); r++) { // right side window
            char c = s.charAt(r);
            if (chars.containsKey(c)) {
                chars.put(c, chars.get(c)-1);
                if (chars.get(c) >= 0) {
                    // avoid the case such as s="BBBAC", t="ABC"
                    // where count for a char drops below 0 -- extra B's are not needed, should not be counted towards total word count
                    count++;
                }

                while (count == t.length()) { // left side window keeps moving right until hit a char exists in t
                    if (minLen == 0 || r-l+1 < minLen) {
                        minStart = l;
                        minLen = r-l+1;
                        //minWindow = s.substring(l,r+1);
                    }
                    if (chars.containsKey(s.charAt(l))) {
                        chars.put(s.charAt(l), chars.get(s.charAt(l))+1);
                        if (chars.get(s.charAt(l)) > 0) {
                            count--;
                        }
                    }

                    l++;
                }
            }

        }

        return s.substring(minStart, minStart+minLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("XADOBECODEBANC","ABC"));
    }
}
