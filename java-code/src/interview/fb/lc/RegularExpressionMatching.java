package interview.fb.lc;

/**
 * #10. Regular Expression Matching
 * Implement regular expression matching with support for '.' and '*'.
 *  '.' Matches any single character.
 *  '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 *  bool isMatch(const char *s, const char *p)
 * Some examples:
 *  isMatch("aa","a") → false
 *  isMatch("aa","aa") → true
 *  isMatch("aaa","aa") → false
 *  isMatch("aa", "a*") → true
 *  isMatch("aa", ".*") → true
 *  isMatch("ab", ".*") → true
 *  isMatch("aab", "c*a*b") → true
 */
public class RegularExpressionMatching {
    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }

        if (p.length() == 1) {
            if (s.length() == 0) {
                return false;
            } else if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        }

        if (p.charAt(1) != '*') {
            if (s.length() == 0) {
                return false;
            } else if (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.') {
                return false;
            }
            return isMatch(s.substring(1), p.substring(1));
        } else {
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            /*for (int i=0; i<s.length(); i++) {
                if (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.') {
                    if (isMatch(s.substring(i+1), p.substring(2))) {
                        return true;
                    }
                } else {
                    break;
                }
            }*/
            int i = 0;
            while (i<s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s.substring(i+1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }

    /*
    * 动态规划基本思想就是把我们计算过的历史信息记录下来，等到要用到的时候就直接使用，不用重新计算。
    * 在这个题里面，假设我们维护一个布尔数组res[i][j],代表s的前i个字符和p的前j个字符是否匹配(注意这里res的维度是s.length()+1,p.length()+1)。
    * 递推公式跟上面类似，分三种种情况：
    * (1)p[j+1]不是'*'。情况比较简单，只要判断如果当前s的i和p的j上的字符一样（如果有p在j上的字符是'.',也是相同），并且res[i][j]==true，则res[i+1][j+1]也为true，res[i+1][j+1]=false;
    * (2)p[j+1]是'*'，但是p[j]!='.'。那么只要以下条件有一个满足即可对res[i+1][j+1]赋值为true：
    *   1)res[i+1][j]为真（'*'只取前面字符一次）;
    *   2)res[i+1][j-1]为真（'*'前面字符一次都不取，也就是忽略这两个字符）;
    *   3)res[i][j+1] && s[i]==s[i-1] && s[i-1]==p[j-1](这种情况是相当于i从0到s.length()扫过来，如果p[j+1]对应的字符是‘*’那就意味着接下来的串就可以依次匹配下来，
    *   如果下面的字符一直重复，并且就是‘*’前面的那个字符）。
    * (3)p[j+1]是'*'，并且p[j]=='.'。因为".*"可以匹配任意字符串，所以在前面的res[i+1][j-1]或者res[i+1][j]中只要有i+1是true，
    * 那么剩下的res[i+1][j+1],res[i+2][j+1],...,res[s.length()][j+1]就都是true了。
    * 这道题有个很重要的点，就是实现的时候外层循环应该是p,然后待匹配串s内层循环扫过来
    * */

    /*
    * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
    * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
    * 3, If p.charAt(j) == '*':
    * here are two sub conditions:
    *   1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
    *   2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
    *
    * */
    public static boolean isMatch_2(String s, String p){
        boolean [][] dp = new boolean[s.length()+1][p.length()+1];
        //init:
        dp[0][0] = true;
        for (int j=0; j<p.length(); j++) { // starting from 1???
            if (p.charAt(j) == '*' && dp[0][j-1]) {
                dp[0][j+1] = true;
            }
        }
        for (int i=0; i<s.length(); i++){
            for (int j=0; j<p.length(); j++) {
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') { // 1&2
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) == s.charAt(i) || p.charAt(j-1) == '.') { // 3.2
                        dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j] || dp[i+1][j-1];
                    } else { // 3.1
                        dp[i+1][j+1] = dp[i+1][j-1];
                    }
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {

        System.out.println(isMatch("aab","a*b"));
        System.out.println(isMatch("aaa","a*a"));
        System.out.println(isMatch("aaabc","a*.c"));

        System.out.println(isMatch_2("aa",".*"));
        System.out.println(isMatch_2("abcd","d*"));


        System.out.println(isMatch("aab","c*a*b"));

        System.out.println(isMatch_2("aa","a*"));
    }
}
