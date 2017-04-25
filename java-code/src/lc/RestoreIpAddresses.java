package lc;

import java.util.ArrayList;
import java.util.List;

/**
 * #93. Restore IP Addresses
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class RestoreIpAddresses {
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) return res;
        helper(s, 0, 1, new ArrayList<>(), res);
        return res;
    }

    private static void helper(String s, int index, int segment, List<String> address, List<String> res) {
        if (index > s.length()) {
            return;
        }
        if (segment == 4) {
            if (isValid(s.substring(index))) {
                address.add(s.substring(index));
                res.add(String.join(".", address));
                address.remove(address.size()-1); // needs to be removed here as well
            }
            return;
        }


        for (int i=1; i<=3 && index+i <= s.length(); i++) {
            String substring = s.substring(index, index+i);
            if (isValid(substring)) {
                address.add(substring);
                helper(s, index+i, segment+1, address, res);
                address.remove(address.size()-1);
            }
        }
    }
    private static boolean isValid(String s) {
        if(s.length() == 0 || s.length() > 3) return false;
        if(s.charAt(0)=='0' && s.length()>1) return false;
        int num = Integer.parseInt(s);
        if(num >= 0 && num <= 255) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("010010"));
    }
}
