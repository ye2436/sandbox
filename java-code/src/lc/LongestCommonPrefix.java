package lc;

/**
 * #14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int min = Integer.MAX_VALUE; // shortest string length
        for (int i=0; i<strs.length; i++) {
            min = Math.min(strs[i].length(), min);
        }
        for (int i=0; i<min; i++) {
            char curr = strs[0].charAt(i);
            for (int j=0; j<strs.length; j++) {
                if (strs[j].charAt(i) != curr) {
                    return sb.toString();
                }
            }
            sb.append(curr);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"geek","geese","gee"}));
    }
}
