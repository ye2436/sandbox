package interview.fb.lc;

import java.util.HashMap;
import java.util.Map;

/**
 * #38. Count and Say
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 */
public class CountAndSay {
    public static String countAndSay2(int n) {
        if (n==0) return null;
        String currRes = "1";
        for (int i=2; i<=n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1; // skip the first one, move directly to the second one
            for (int j=1; j<currRes.length(); j++) {
                if (currRes.charAt(j) == currRes.charAt(j-1)) {
                    count++;
                } else {
                    sb.append(count).append(currRes.charAt(j-1));
                    count = 1;
                }
            }
            sb.append(count).append(currRes.charAt(currRes.length()-1));
            currRes = sb.toString();
        }

        return currRes;
    }

    public static String countAndSay(int n) {
        if (n==0) return null;
        return helper(n);
    }

    private static String helper(int n) {
        if (n==1) return "1";

        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> count = new HashMap<>();
        String s = helper(n-1);
        for (int i=0; i<s.length(); i++) {
            if (count.containsKey(s.charAt(i))) {
                count.put(s.charAt(i), count.get(s.charAt(i))+1);
            } else {
                if (!count.isEmpty()) {
                    for (char key : count.keySet()) {
                        sb.append(count.get(key)).append(key);
                    }
                    count.clear();
                }
                count.put(s.charAt(i), 1);
            }
        }
        if (!count.isEmpty()) {
            for (char key : count.keySet()) {
                sb.append(count.get(key)).append(key);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(countAndSay(5));
        System.out.println(countAndSay2(5));
    }
}
