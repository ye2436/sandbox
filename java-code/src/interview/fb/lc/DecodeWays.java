package interview.fb.lc;

/**
 * #91. Decode Ways
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 */
public class DecodeWays {

    // Let dp[i] be # of ways to decode first i characters in String s
    // At any index i, if s(i), by itself, can be decoded (not 0), then dp[i+1] = dp[i]       ---- (a)
    // if s(i), along with the character before it, can be decoded, then dp[i+1] = dp[i-1]    ---- (b)
    // With these 2 possibilities, we can divide the scenarios into 4:
    // for s.subString(i-2,i)
    // 1) 00 : can not be decoded. dp[i] = 0
    // 2) 01-09, 27-99: only (a) applies. dp[i] = dp[i-1]
    // 3) 10, 20: only (b) applies. dp[i] = dp[i-2]
    // 4) 11-19, 21-26: both (a) and (b) apply. dp[i] = dp[i-1] + dp[i-2]

    // Better:
    // 初始条件：dp[0] = 1, dp[1] = (s[0] == '0') ? 0 : 1
    // dp[i] = ( s[i-1] == 0 ? 0 : dp[i-1] ) + ( s[i-2,i-1]可以表示字母 ？ dp[i-2] : 0 )，
    // 其中第一个分量是把s[0...i-1]末尾一个数字当做一个字母来考虑，第二个分量是把s[0...i-1]末尾两个数字当做一个字母来考虑
    public static int practice(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i) != 0) {
                dp[i+1] += dp[i];
            }
            if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2' && s.charAt(i) <= '6') {
                dp[i+1] += dp[i-1];
            }
        }

        return dp[s.length()];
    }


    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i=2; i<=s.length(); i++) {
            dp[i] = s.charAt(i-1) == '0' ? 0 : dp[i-1];
            if (s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6')) {
                dp[i] += dp[i-2];
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("126"));
        System.out.println(practice("126"));
    }
}
