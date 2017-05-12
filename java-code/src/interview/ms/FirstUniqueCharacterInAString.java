package interview.ms;

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
        // f[0]: frequency of letter a
        // f[1]: frequency of letter b
        int[] frequency = new int[26]; // could expand to 256 for special characters

        for (int i = 0; i < s.length(); i++) { // get frequency for all letters
            frequency[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (frequency[s.charAt(i) - 'a'] == 1) { // go through characters in string, return the first frequency = 1
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
