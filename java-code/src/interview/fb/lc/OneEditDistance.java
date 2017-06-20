package interview.fb.lc;

/**
 * 161. One Edit Distance

 Given two strings S and T, determine if they are both one edit distance apart.
 */
public class OneEditDistance {

    // Insert, Delete, Replace
    // Insert and Delete can be grouped together and considered the same. (abc, abcd)
    // So there are 3 possibilities
    // 1) One Replace        --- a B c, a D c
    // 2) One Delete in s    --- a b c D, a b c
    // 3) One Delete in t    --- a b c, a b c D
    // * The approach is to loop through the characters (to the length of the short string), when a mismatch in characters are found,
    // based on the 2 strings length:
    // a) if same, compare if the rest of the strings are the same  --- one replace
    // b) if s longer, compare if the rest of s (from next index) is same as the rest of t (from this index)  --- one delete in s
    // c) if t longer, ...... --- one delete in t
    public boolean isOneEditDistance(String s, String t) {
        int len = Math.min(s.length(), t.length());
        for (int i=0; i<len; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    return s.substring(i+1).equals(t.substring(i+1));
                } else if (s.length() > t.length()) {
                    return s.substring(i+1).equals(t.substring(i));
                } else {
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }
        // otherwise, all previous characters matched. Check if the rest of the characters is length of 1
        return Math.abs(s.length() - t.length()) == 1;
    }
}
