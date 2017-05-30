package interview.li;

import java.util.HashMap;
import java.util.Map;

/**
 * #13. Roman to Integer
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class RomanToInteger {

    public static int romanToInt(String s) {
        // validate if the string is a valid roman numeral
        boolean valid = s.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        for (int i=0; i<s.length(); i++) {
            int curr = map.get(s.charAt(i));
            if (i<s.length()-1 && curr < map.get(s.charAt(i+1))) { // if the next number is larger
                res -= curr;
            } else {
                res += curr;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MMMDLXXXVI"));
    }
}
