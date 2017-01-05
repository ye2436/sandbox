/**
 * Implement regular expression matching with support for '.' and '*'.

 '.' Matches any single character.
 '*' Matches zero or more of the preceding element.

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "a*") → true
 isMatch("aa", ".*") → true
 isMatch("ab", ".*") → true
 isMatch("aab", "c*a*b") → true

 */
public class RegularExpressionMatching {
    public static boolean isMatch(String s, String p) {
        if (p.contains("*")) {

        } else {
            if (s.length() != p.length()) {
                return false;
            }
            if (p.contains(".")) {
                for (int i=0; i<s.length(); i++) {
                    if (p.charAt(i) == '.') {
                        continue;
                    }
                    if (s.charAt(i) != p.charAt(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                if (s.equals(p)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aaabc","a.a.c"));
    }
}
