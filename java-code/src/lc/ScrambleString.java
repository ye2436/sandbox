package lc;

import java.util.Arrays;

/**
 * #87. Scramble String
 * Given a string s1, we may represent it as a binary lc.tree by partitioning it to two non-empty substrings recursively.
 * Below is one possible representation of s1 = "great":
 *      great
 *     /    \
 *    gr    eat
 *   / \    /  \
 *  g   r  e   at
 *            / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *      rgeat
 *     /    \
 *    rg    eat
 *   / \    /  \
 *  r   g  e   at
 *            / \
 *           a   t
 * We say that "rgeat" is a scrambled string of "great".
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *      rgtae
 *     /    \
 *    rg    tae
 *   / \    /  \
 *  r   g  ta  e
 *        / \
 *       t   a
 * We say that "rgtae" is a scrambled string of "great".
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class ScrambleString {

    // dynamic programming
    public static boolean isScramble_2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;


        int length = s1.length();
        boolean[][][] dp = new boolean[length][length][length+1];
        for (int i=0; i<length; i++) {
            for (int j=0; j<length; j++) {
                // init
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int len=2; len<=length; len++) {
            for (int i=0; i<length-len+1; i++) {
                for (int j=0; j<length-len+1; j++) {
                    for (int k=1; k<len; k++) {
                        dp[i][j][len] |= (dp[i][j][k] && dp[i+k][j+k][len-k]) || (dp[i][j+len-k][k] && dp[i+k][j][len-k]);
                    }
                }
            }
        }

        return dp[0][0][length];
    }

    // brute force, recursive
    public static boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        char[] c1 = s1.toCharArray();
        Arrays.sort(c1);
        char[] c2 = s2.toCharArray();
        Arrays.sort(c2);
        if (!Arrays.equals(c1, c2)) return false;

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) return true;
        }
        return false;
    }

    public static boolean wrongAnswer(String s1, String s2) { // 全部字符相等只是符合一部分条件
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0 || s1.length() != s2.length()) return false;
        char[] c1 = s1.toCharArray();
        Arrays.sort(c1);
        char[] c2 = s2.toCharArray();
        Arrays.sort(c2);
        String n1 = new String(c1);
        String n2 = new String(c2);

        return n1.equals(n2);
    }

    public static void main(String[] args) {
        System.out.println("abcde".substring(0,5));
        //System.out.println(isScramble("great","rgtae"));
        //System.out.println(isScramble("abcd","bdac"));
    }
}
