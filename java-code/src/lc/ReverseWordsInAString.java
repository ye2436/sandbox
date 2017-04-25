package lc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 151. Reverse Words in a String
 * Given an input string, reverse the string word by word.
 * For example,
 *  Given s = "the sky is blue",
 *  return "blue is sky the".
 */
public class ReverseWordsInAString {
    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        List<String> words = Arrays.asList(s.split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words);
    }

    public static String reverseWords_2(String s) {
        if (s == null || s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        int start = 0;
        s = s.trim();
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (start == 0) {
                    sb.append(s.substring(start, i));
                } else if (start > 0) {
                    sb.insert(0, s.substring(start, i) + " ");
                }
                start = -1;
                continue;
            }
            if (start < 0) {
                start = i;
            }
        }
        if (start == 0) {
            sb.append(s.substring(start, s.length()));
        } else if (start > 0) {
            sb.insert(0, s.substring(start, s.length()) + " ");
        }

        return sb.toString();
    }

    public String reverseWords_3(String s) { // recursive, top-down. this is faster than iterative.
        s = s.trim();
        return helper(s,0).toString();
    }

    private StringBuilder helper(String s, int index)  {
        if (index >= s.length()) return new StringBuilder(); // end of the string, return empty string

        StringBuilder cur = new StringBuilder();
        int start = index;
        while (index < s.length() && s.charAt(index) != ' ') {
            cur.append(s.charAt(index++));
        }
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        if (start == 0) {
            return helper(s,index).append(cur);
        }
        return helper(s,index).append(cur).append(' ');
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("   a   b "));
        System.out.println(reverseWords_2("the sky is blue"));
        System.out.println(reverseWords_2("   a   b "));
    }
}
