package interview.am.lc;

/**
 * 242. Valid Anagram

 Given two strings s and t, write a function to determine if t is an anagram of s.

 For example,
 s = "anagram", t = "nagaram", return true.
 s = "rat", t = "car", return false.

 Note:
 You may assume the string contains only lowercase alphabets.

 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?


 */
public class ValidAnagram {

    // If the string contains only lowercase alpha, we could use an array of size 26 to keep track of the frequency of each characters.
    // Although this will not work in unicode characters. In that case we could use either a map to store. Or just sort the strings
    public boolean isAnagram(String s, String t) {
        if (s.length()==0 && t.length()==0) return true;


        return true;
    }
}
