package interview.am;

/**
 * 186. Reverse Words in a String II

 Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

 The input string does not contain leading or trailing spaces and the words are always separated by a single space.

 For example,
 Given s = "the sky is blue",
 return "blue is sky the".

 Could you do it in-place without allocating extra space?

 Related problem: Rotate Array
 */
public class ReverseWordsInStringII {

    // First reverse the entire string, then reverse individual word
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) return;
        reverse(s, 0, s.length-1);
        int l = -1; // -1 indicates word not started
        int r = 0;
        while (r < s.length) {
            if (s[r] != ' ' && l < 0) { // initialize the l
                l = r;
                continue;
            }
            if (s[r] == ' ' && l >= 0) {
                reverse(s, l, r-1);
                l = -1;
            }
            if (r == s.length-1 && l>=0) {
                reverse(s, l, r);
            }
            r++;
        }
    }

    private void reverse(char[] s, int l, int r) {
        while (l < r) {
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }
}
