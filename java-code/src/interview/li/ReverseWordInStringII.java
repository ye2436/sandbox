package interview.li;

import java.util.Arrays;

/**
 * #186
 */
public class ReverseWordInStringII {
    public static void reverseWords(char[] s) {
        if (s == null || s.length == 0) return;
        reverse(s, 0, s.length-1);
        int l = -1;
        for (int i=0; i<s.length; i++) {
            if (s[i] != ' ' && l<0) {
                l=i;
                continue;
            }
            if (s[i] == ' ' && l>=0) {
                reverse(s, l, i-1);
                l=-1;
            }
            if (i==s.length-1 && l>=0) {
                reverse(s, l, i);
                l=-1;
            }
        }
    }

    public static void reverse(char[] s, int start, int end) {
        while (start < end){
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        char[] s = "hello world!".toCharArray();
        reverseWords(s);
        System.out.println(Arrays.toString(s));
    }
}
