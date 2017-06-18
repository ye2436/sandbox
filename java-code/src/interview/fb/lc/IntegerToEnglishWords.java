package interview.fb.lc;

import java.util.Arrays;
import java.util.List;

/**
 * 273. Integer to English Words

 Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

 For example,
 123 -> "One Hundred Twenty Three"
 12345 -> "Twelve Thousand Three Hundred Forty Five"
 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class IntegerToEnglishWords {

    // process from lower bits to higher bits
    // 1. get last 2 digits
    //    a. if 0 < n < 20 (1 to 1 mapping)
    //    b. else, it's combination of tens and units
    // 2. get hundreds
    // 3. group 1 & 2 to one method, recursively call for 3 digits group and add with Thousand, Million, Billion,
    // (Integr max: 2,147,483,647)

    public static final List<String> LESS_THAN_20 = Arrays.asList("", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
            "Seventeen", "Eighteen", "Nineteen");
    public static final List<String> TENS = Arrays.asList("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty",
            "Seventy", "Eighty", "Ninety");
    public static final String HUNDRED = "Hundred";
    public static final String THOUSAND = "Thousand";
    public static final String MILLION = "Million";
    public static final String BILLION = "Billion";
    public static final List<String> UNITS = Arrays.asList("", THOUSAND, MILLION, BILLION);

    public static String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        int i=0;
        while (num > 0) {
            if (num % 1000 != 0) {
                sb.insert(0, printThreeDigits(num % 1000) + " " + UNITS.get(i) + " ");
            }
            num /= 1000;
            i++;
        }

        return sb.toString().trim();
    }

    private static String printThreeDigits(int n) { // 0 < n < 1000
        StringBuilder sb = new StringBuilder();
        if (n >= 100) {
            sb.append(LESS_THAN_20.get(n/100) + " " + HUNDRED);
        }
        if (n % 100 != 0) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            if (n % 100 < 20) {
                sb.append(LESS_THAN_20.get(n % 100));
            } else {
                sb.append(TENS.get(n % 100 /10));
                if (n % 10 != 0) {
                    sb.append(" ");
                    sb.append(LESS_THAN_20.get(n%10));
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(printThreeDigits(29));
        System.out.println(printThreeDigits(100));
        System.out.println(printThreeDigits(101));
        System.out.println(numberToWords(1_287_115_987));
    }
}
