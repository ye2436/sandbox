package lc;

/**
 * #28. Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrStr {
    public static int strStr(String haystack, String needle) {
        int index = -1;
        if (haystack.length() < needle.length()) return index;
        if (needle.length() == 0) return 0;

        int j = needle.length();
        for (int i=0; i<haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (i+j<=haystack.length() && needle.equals(haystack.substring(i, i+j))) {
                    return i;
                }
            }
        }
        return index;
    }

    public static int strStr2(String haystack, String needle) {

        for (int i=0; ; i++) {
            for (int j=0; ; j++) {
                if (j == needle.length()) return i;
                if (i+j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i+j)) break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(strStr("mississippi","issipi"));
        System.out.println(strStr("a","a"));
    }
}
